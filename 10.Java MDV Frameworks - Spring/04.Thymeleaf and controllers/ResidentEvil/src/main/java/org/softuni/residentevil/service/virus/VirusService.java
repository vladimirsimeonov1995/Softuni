package org.softuni.residentevil.service.virus;

import org.softuni.residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {

    public boolean addVirus(VirusServiceModel virusServiceModel);

    public List<VirusServiceModel> getAll();

    public VirusServiceModel getVirusById(Integer id);

    public boolean editVirus(VirusServiceModel virusServiceModel, Integer id);

    public boolean deleteVirus(Integer id);

}
