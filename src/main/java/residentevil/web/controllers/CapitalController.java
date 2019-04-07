package residentevil.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import residentevil.domain.models.service.CapitalServiceModel;
import residentevil.service.CapitalService;

import java.util.List;

@Controller
public class CapitalController extends BaseController {
    private final CapitalService capitalService;

    public CapitalController(CapitalService capitalService) {
        this.capitalService = capitalService;
    }

    @GetMapping(value = "capital/fetch", produces = "application/json")
    @ResponseBody
    public Object fetchData() {
         List<CapitalServiceModel> capitals =  this.capitalService.findAllCapitals();
         return capitals.toArray();
    }
}
