package residentevil.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, Principal principal){
        if (principal != null) {
            modelAndView.addObject("user", principal.getName());
        }
        return this.view("index");
    }

    //@PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, Principal principal){
        if (principal != null) {
            modelAndView.addObject("user", principal.getName());
        }
        return this.view("home");
    }
}
