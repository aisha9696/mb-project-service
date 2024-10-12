package kz.mb.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller("/home")
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal
    OidcUser principal) {
        return "index";
    }
}