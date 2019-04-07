package residentevil.service;

import residentevil.domain.entities.UserRole;
import residentevil.domain.models.service.UserRoleSerivceModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserRoleService {
    UserRole getUserRoleByName(String name);

    List<UserRoleSerivceModel> findAll();
}
