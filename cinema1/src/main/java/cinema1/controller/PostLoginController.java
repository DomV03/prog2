package cinema1.controller;

import cinema1.model.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostLoginController {

    @GetMapping("/post-login")
    public String redirectBasedOnRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // Controlla se l'utente ha il ruolo ADMIN
            if (principal instanceof Credentials && ((Credentials) principal).getRole().equals("ADMIN")) {
                return "redirect:/admin";
            }
        }
        // Per tutti gli altri utenti, reindirizza alla home
        return "redirect:/";
    }
}