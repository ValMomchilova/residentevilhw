package residentevil.service;

import residentevil.domain.entities.Capital;
import residentevil.domain.models.service.CapitalServiceModel;
import residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface CapitalService {
    List<CapitalServiceModel> findAllCapitalsOrderedByName();

    List<CapitalServiceModel> findAllCapitals();
}
