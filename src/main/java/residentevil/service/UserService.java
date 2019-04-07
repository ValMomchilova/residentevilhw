package residentevil.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import residentevil.domain.entities.User;
import residentevil.domain.models.service.UserServiceModel;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    boolean register(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    List<UserServiceModel> findAllUsersNotUsername(String username);

    UserServiceModel findUserByID(String id);

    boolean saveUser(UserServiceModel userServiceModel);

    boolean saveRoles(UserServiceModel userServiceModel);

    boolean isAdmin(String id);
}
