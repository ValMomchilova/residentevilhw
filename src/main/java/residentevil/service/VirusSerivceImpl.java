package residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.Virus;
import residentevil.domain.models.service.VirusServiceModel;
import residentevil.repository.VirusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusSerivceImpl implements VirusService {
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusSerivceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusServiceModel saveVirus(VirusServiceModel virusServiceModel) {
        Virus virus = this.virusRepository.save(this.modelMapper.map(virusServiceModel, Virus.class));
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        List<Virus> viruses = this.virusRepository.findAll();
        return viruses.stream().map(v -> this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VirusServiceModel findVirusById(Long id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        if (virus == null){
            return null;
        }

        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public void deleteVirus(Long id) {
        this.virusRepository.deleteById(id);
    }
}
