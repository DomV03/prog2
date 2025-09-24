package cinema1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import cinema1.model.Credentials;
import cinema1.model.User;
import cinema1.service.CredentialsService;
import cinema1.service.UserService;

@Controller
public class AuthenticationController {
	
    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;
	
    @GetMapping(value = "/register") 
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "formRegisterUser";
    }
	
    @GetMapping(value = "/login") 
    public String showLoginForm(Model model) {
        return "login";
    }

		
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult, 
                               @Valid @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               RedirectAttributes redirectAttributes) {

        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(user);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);

            redirectAttributes.addFlashAttribute("successMessage", "Registrazione completata! Ora puoi accedere.");
            return "redirect:/login";
        }
        return "formRegisterUser";
    }
    

}

