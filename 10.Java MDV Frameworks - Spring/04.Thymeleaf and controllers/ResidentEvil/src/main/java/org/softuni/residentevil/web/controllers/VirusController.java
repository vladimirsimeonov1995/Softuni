package org.softuni.residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.models.binding.VirusAddBindingModel;
import org.softuni.residentevil.domain.models.binding.VirusEditBindingModel;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.domain.models.view.CapitalListViewModel;
import org.softuni.residentevil.domain.models.view.VirusShowViewModel;
import org.softuni.residentevil.service.capital.CapitalService;
import org.softuni.residentevil.service.virus.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView,
                            @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel) {
        return getAddView(bindingModel, modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return getAddView(bindingModel, modelAndView);
        }

        if (!virusService.addVirus(modelMapper.map(bindingModel, VirusServiceModel.class))) {
            return getAddView(bindingModel, modelAndView);
        }

        return super.redirect("/");

    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {

        List<VirusShowViewModel> viewModels = virusService.getAll()
                .stream()
                .map(v -> modelMapper.map(v, VirusShowViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("viewModels", viewModels);
        return super.view("show", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelAndView modelAndView){

        Integer intId = Integer.parseInt(id);

        VirusEditBindingModel bindingModel = modelMapper
                .map(virusService.getVirusById(intId), VirusEditBindingModel.class);

        modelAndView.addObject("bindingModel", bindingModel);
        return super.view("edit-virus", modelAndView);

    }

    @PostMapping("/show/{id}")
    public ModelAndView deleteConfirm(@PathVariable("id") String id, ModelAndView modelAndView){
        Integer intId = Integer.parseInt(id);

        if(!virusService.deleteVirus(intId)){
            return super.redirect("/viruses/show");
        }

        return super.redirect("/");
    }


    private ModelAndView getAddView(@ModelAttribute(name = "bindingModel") @Valid VirusAddBindingModel bindingModel, ModelAndView modelAndView) {
        modelAndView.addObject("bindingModel", bindingModel);
        modelAndView.addObject("capitals", capitalService.findAllCapitals()
                .stream()
                .map(c -> modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList())
        );

        return super.view("add-virus", modelAndView);
    }

}
