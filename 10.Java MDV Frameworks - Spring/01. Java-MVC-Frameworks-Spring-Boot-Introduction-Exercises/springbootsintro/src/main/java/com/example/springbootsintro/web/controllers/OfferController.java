package com.example.springbootsintro.web.controllers;

import com.example.springbootsintro.domain.models.binding.OfferFindBindingModel;
import com.example.springbootsintro.domain.models.binding.OfferRegisterBindingModel;
import com.example.springbootsintro.domain.models.service.FamilyOfferServiceModel;
import com.example.springbootsintro.domain.models.service.OfferServiceModel;
import com.example.springbootsintro.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reg")
    public String register(){
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel model){

        try {
            offerService.registerOffer(modelMapper.map(model, OfferServiceModel.class));
        }catch (Exception e){
            e.printStackTrace();

            return "redirect:/reg";
        }

        return "redirect:/";
    }

    @GetMapping("/find")
    public String find(){
        return "find.html";
    }

    @PostMapping("/find")
    public String findConfirm(@ModelAttribute(name = "model") OfferFindBindingModel model){

        try {
            OfferServiceModel offerServiceModel = offerService.findApartment(modelMapper.map(model, FamilyOfferServiceModel.class));

            if(offerServiceModel == null){
                return "redirect:/";
            }else {
                offerService.removeOffer(offerServiceModel);
            }
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/find";
        }

        return "redirect:/";
    }

}
