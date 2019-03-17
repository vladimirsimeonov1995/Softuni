package org.softuni.residentevil.service.capital;

import org.softuni.residentevil.domain.models.service.CapitalServiceModel;

import java.util.List;

public interface CapitalService {

    List<CapitalServiceModel> findAllCapitals();

}
