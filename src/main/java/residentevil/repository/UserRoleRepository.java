package residentevil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import residentevil.domain.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole findUserRoleByName(String name);
}
