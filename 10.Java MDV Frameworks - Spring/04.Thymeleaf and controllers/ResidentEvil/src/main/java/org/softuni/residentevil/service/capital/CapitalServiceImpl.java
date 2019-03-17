package org.softuni.residentevil.service.capital;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.models.service.CapitalServiceModel;
import org.softuni.residentevil.repository.CapitalRepository;
import org.softuni.residentevil.service.capital.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CapitalServiceModel> findAllCapitals() {
        return capitalRepository.findAllOrOrderByName()
                .stream()
                .map(c -> modelMapper.map(c, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }
}
