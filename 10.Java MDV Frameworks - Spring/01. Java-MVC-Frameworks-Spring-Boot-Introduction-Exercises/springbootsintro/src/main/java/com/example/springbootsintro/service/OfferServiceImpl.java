package com.example.springbootsintro.service;

import com.example.springbootsintro.domain.entities.Offer;
import com.example.springbootsintro.domain.models.binding.OfferFindBindingModel;
import com.example.springbootsintro.domain.models.service.FamilyOfferServiceModel;
import com.example.springbootsintro.domain.models.service.OfferServiceModel;
import com.example.springbootsintro.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {

        if(validator.validate(offerServiceModel).size() != 0){
            throw new IllegalArgumentException("Something went wrong!");
        }

        offerRepository.saveAndFlush(modelMapper.map(offerServiceModel, Offer.class));
    }


    @Override
    public List<OfferServiceModel> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfferServiceModel findApartment(FamilyOfferServiceModel familyOfferServiceModel) {

        if(validator.validate(familyOfferServiceModel).size() != 0){
            throw new IllegalArgumentException("Something went wrong!");
        }

        return offerRepository
                .findAll()
                .stream()
                .map(o -> modelMapper.map(o, OfferServiceModel.class))
                .filter(serviceModel -> {
                    BigDecimal price = (serviceModel.getAgencyCommission()
                            .multiply(serviceModel.getApartmentRent()))
                            .divide(new BigDecimal("100"))
                            .add(serviceModel.getApartmentRent());

                    return familyOfferServiceModel.getFamilyBudget().compareTo(price) >=0 &&
                            familyOfferServiceModel.getFamilyApartmentType().equals(serviceModel.getApartmentType());
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public void removeOffer(OfferServiceModel offerServiceModel) {
        offerRepository.delete(modelMapper.map(offerServiceModel,Offer.class));
    }
}
















