package residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.UserRole;
import residentevil.domain.models.service.UserRoleSerivceModel;
import residentevil.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRole getUserRoleByName(String name) {
       UserRole role = this.userRoleRepository.findUserRoleByName(name);
       return role;
    }

    @Override
    public List<UserRoleSerivceModel> findAll() {
        return this.userRoleRepository.findAll()
                .stream().map(r -> this.modelMapper.map(r, UserRoleSerivceModel.class))
                .collect(Collectors.toList());
    }


}
