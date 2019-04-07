package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.domain.entities.Magnitude;
import residentevil.domain.entities.Mutation;
import residentevil.domain.entities.UserRole;
import residentevil.domain.models.binding.CapitalsBindingModel;
import residentevil.domain.models.binding.VirusAddBindingModel;
import residentevil.domain.models.service.VirusServiceModel;
import residentevil.domain.models.view.CapitalsVirusAddViewModel;
import residentevil.domain.models.view.VirusListViewModel;
import residentevil.service.CapitalService;
import residentevil.service.VirusService;
import residentevil.web.validators.ValidationMessages;
import javax.validation.Valid;
import javax.xml.stream.events.EndDocument;
import java.net.Authenticator;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VirusController extends BaseController {
    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/viruses/add")
    public ModelAndView addVirus(@ModelAttribute(name = "virusModel") VirusAddBindingModel virusModel, ModelAndView modelAndView){
        virusModel.setCapitals(new ArrayList<CapitalsBindingModel>());
        modelAndView.addObject("virusModel", virusModel);
        this.addVirusModels(modelAndView);
        return this.view("add-virus", modelAndView);
    }

    @PostMapping("/viruses/add")
    public ModelAndView postVirus(@Valid @ModelAttribute(name = "virusModel") VirusAddBindingModel virusModel,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            this.addVirusModels(modelAndView);
            return this.view("add-virus", modelAndView);
        }

        virusModel.setCapitalsByCapitalsIds();
        this.virusService.saveVirus(this.modelMapper.map(virusModel, VirusServiceModel.class));

        return this.redirect("/viruses/show");
    }

    @GetMapping("/viruses/show")
    public ModelAndView showViruses(ModelAndView modelAndView){
        List<VirusListViewModel> viruses = this.virusService.findAllViruses()
                .stream().map(v -> this.modelMapper.map(v, VirusListViewModel.class))
                .collect(Collectors.toList());
        return this.view("virus-list", modelAndView);
    }

    @GetMapping(value = "virus/fetch", produces = "application/json")
    @ResponseBody
    public Object fetchData(Principal principal) {
         List<VirusServiceModel> viruses =  this.virusService.findAllViruses();
         List<VirusListViewModel> viewViruses = viruses.stream()
                 .map(v -> this.modelMapper.map(v, VirusListViewModel.class)).collect(Collectors.toList());

         boolean editable = false;
         if (principal != null) {
             for (GrantedAuthority authority : ((UsernamePasswordAuthenticationToken) principal).getAuthorities()){
                 String role = ((UserRole) authority).getName();
                 if (role.equals("ADMIN") || role.equals("MODERATOR")){
                     editable = true;
                     break;
                 }
             }
         }
        for (VirusListViewModel viewVirus : viewViruses) {
            viewVirus.setEditable(editable);
        }
         return viewViruses.toArray();
    }

    @GetMapping("/virus/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id,
                              ModelAndView modelAndView) {
        this.prepareVirusModelAndView(id, modelAndView);
        modelAndView.addObject("mode", "edit");
        return modelAndView;
    }

    @PostMapping("/virus/edit/{id}")
    public ModelAndView editConfirm(@PathVariable(name = "id") Long id,
                                     @Valid @ModelAttribute(name = "virusModel") VirusAddBindingModel virusModel,
                                     BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            this.addVirusModels(modelAndView);
            return this.view("add-virus", modelAndView);
        }

        virusModel.setCapitalsByCapitalsIds();
        this.virusService.saveVirus(this.modelMapper.map(virusModel, VirusServiceModel.class));

        return this.redirect("/viruses/show");
    }

    @GetMapping("/virus/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id,
                               ModelAndView modelAndView) {
        this.prepareVirusModelAndView(id, modelAndView);
        modelAndView.addObject("mode", "delete");
        return modelAndView;
    }

    @PostMapping("/virus/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable(name = "id") Long id){
        this.virusService.deleteVirus(id);

        return this.redirect("/viruses/show");
    }

    private void addVirusModels(ModelAndView modelAndView){
        modelAndView.addObject("mutations", Mutation.values());
        modelAndView.addObject("magnitudes", Magnitude.values());
        List<CapitalsVirusAddViewModel> capitals = this.capitalService.findAllCapitalsOrderedByName()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalsVirusAddViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("capitals", capitals);
        ValidationMessages validationMessages = new ValidationMessages();
        modelAndView.addObject("validationMessages", validationMessages);
    }

    private ModelAndView prepareVirusModelAndView(Long id, ModelAndView modelAndView){
        VirusServiceModel virusServiceModel = this.virusService.findVirusById(id);
        VirusAddBindingModel virusModel = this.modelMapper.map(virusServiceModel, VirusAddBindingModel.class);
        virusModel.setCapitalsIdsByCapitals();
        this.addVirusModels(modelAndView);
        modelAndView.addObject("virusModel", virusModel);
        return this.view("add-virus", modelAndView);
    }
}
