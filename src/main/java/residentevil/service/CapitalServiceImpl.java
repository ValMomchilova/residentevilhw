package residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.models.service.CapitalServiceModel;
import residentevil.repository.CapitalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {
    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalServiceModel> findAllCapitalsOrderedByName() {
        return this.capitalRepository.findAllByOrderByNameAsc()
                .stream().map(x -> this.modelMapper.map(x, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CapitalServiceModel> findAllCapitals() {
        return this.capitalRepository.findAll()
                .stream().map(x -> this.modelMapper.map(x, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }
}
