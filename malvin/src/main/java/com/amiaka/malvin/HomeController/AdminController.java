package com.amiaka.malvin.HomeController;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class AdminController {
    @GetMapping("/home")
    public String home(Model model, Principal principal){
            model.addAttribute("name", principal.getName());
            return "Home";
    }


}
