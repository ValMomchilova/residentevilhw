package residentevil.service;

import residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {
    VirusServiceModel saveVirus(VirusServiceModel virusServiceModel);
    List<VirusServiceModel> findAllViruses();
    VirusServiceModel findVirusById(Long id);
    void deleteVirus(Long id);
}
