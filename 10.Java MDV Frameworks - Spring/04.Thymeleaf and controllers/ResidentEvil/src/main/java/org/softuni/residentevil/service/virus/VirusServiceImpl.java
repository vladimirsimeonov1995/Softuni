package org.softuni.residentevil.service.virus;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Virus;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addVirus(VirusServiceModel virusServiceModel) {

        try {
            virusRepository.saveAndFlush(modelMapper.map(virusServiceModel, Virus.class));

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<VirusServiceModel> getAll() {
        List<VirusServiceModel> virusServiceModels = new ArrayList<>();


        virusServiceModels = virusRepository.findAll()
                .stream()
                .map(v -> modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());

        return virusServiceModels;

    }

    @Override
    public VirusServiceModel getVirusById(Integer id) {

        try {
            Virus virus = virusRepository.getOne(id);

            VirusServiceModel model = modelMapper
                    .map(virus, VirusServiceModel.class);

            return model;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean editVirus(VirusServiceModel virusServiceModel, Integer id) {
        try {
            virusRepository.save(modelMapper.map(virusServiceModel, Virus.class));

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteVirus(Integer id) {
        try {
            virusRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
