package org.toptal.service;

import org.toptal.model.Role;

public interface RoleService {
    Role findByName(String name);
}