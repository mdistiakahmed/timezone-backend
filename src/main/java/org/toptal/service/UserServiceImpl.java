package org.toptal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.toptal.dao.UserDao;
import org.toptal.model.Role;
import org.toptal.model.User;
import org.toptal.model.UserDto;
import org.toptal.model.UserUpdate;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        return authorities;
    }

    public List<UserDto> findAll() {
        List<User> list = new ArrayList<>();
        List<UserDto> dtoList = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        for(int i=0;i<list.size();i++) {
            User user = list.get(i);
            if(user.getEmail().equals("admin@gmail.com")) {
                continue;
            }
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            Role role = user.getRole();
            if(role.getName().equalsIgnoreCase("ADMIN")) {
                userDto.setHasAdminRole(true);
            }
            dtoList.add(userDto);
        }
        return dtoList;
    }

    @Override
    public User findOne(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User save(UserDto userDto) {

        User nUser = getUserFromDto(userDto);
        nUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));


        if(userDto.getHasAdminRole()){
            Role role = roleService.findByName("ADMIN");
            nUser.setRole(role);
        } else {
            Role role = roleService.findByName("USER");
            nUser.setRole(role);
        }
        return userDao.save(nUser);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        boolean isExitsAccount = userDao.existsByEmail(userDto.getEmail());
        if(isExitsAccount) {
            return null;
        }

        User nUser = getUserFromDto(userDto);
        nUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        if(userDto.getHasAdminRole()){
            Role role = roleService.findByName("ADMIN");
            nUser.setRole(role);
        } else {
            Role role = roleService.findByName("USER");
            nUser.setRole(role);
        }

        return getUserDtoFromUser(userDao.save(nUser));
    }

    @Override
    @Transactional
    public void bulkDelete(List<String> emailIds) {
        for(String email : emailIds) {
            userDao.deleteByEmail(email);
        }
    }

    @Override
    @Transactional
    public User updateUser(UserUpdate userUpdate) {
        User user = userDao.findByEmail(userUpdate.getOldEmail());
        user.setEmail(userUpdate.getNewEmail());
        if(userUpdate.isNewRole()){
            Role role = roleService.findByName("ADMIN");
            user.setRole(role);
        } else {
            Role role = roleService.findByName("USER");
            user.setRole(role);
        }

        return userDao.save(user);

    }

    private User getUserFromDto(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    private UserDto getUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        boolean isAdmin = user.getRole().getName().equals("ADMIN");
        userDto.setHasAdminRole(isAdmin);
        return userDto;
    }
}