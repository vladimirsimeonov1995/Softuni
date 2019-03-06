package com.example.springbootsintro.service;

import com.example.springbootsintro.domain.models.binding.OfferFindBindingModel;
import com.example.springbootsintro.domain.models.service.FamilyOfferServiceModel;
import com.example.springbootsintro.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void registerOffer(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> getAllOffers();

    OfferServiceModel findApartment(FamilyOfferServiceModel familyOfferServiceModel);

    void removeOffer(OfferServiceModel offerServiceModel);

}
