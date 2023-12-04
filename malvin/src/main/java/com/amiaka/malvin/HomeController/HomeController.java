package com.amiaka.malvin.HomeController;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.amiaka.malvin.UserDto.UserDto;
import com.amiaka.malvin.UserService.UserService;

@Controller
public class HomeController {

    private final UserService userService;
    

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String LoginPage(Model model, UserDto userDto ){
      model.addAttribute( "user",  userDto);
        return "LoginForm";
    }
    @GetMapping("/register")
    public String RegistrationForm(Model model, UserDto userDto ){
        return "registrationPage";
    }
    
      @GetMapping("/admin/home")
       public String Adminpage(Model model, UserDto userDto, Principal principal ){
        String name =  principal.getName();
        model.addAttribute("name", name);
        return "admin";
      }

     @PostMapping("/register")
     public String RegistrationProcess(Model model, UserDto userDto ){
      model.addAttribute("user",  userDto);
      userService.saveUser(userDto);
      return "redirect:/login?success";

    }

}
