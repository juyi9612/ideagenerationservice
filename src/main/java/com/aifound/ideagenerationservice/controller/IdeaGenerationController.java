package com.aifound.ideagenerationservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdeaGenerationController {
    
    @GetMapping("api/v1/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello, " + name;
	}

	@GetMapping("admin/api/v1/greeting")
	public String greetingh(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello, " + name;
	}

	@GetMapping("api/v1/user")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }
}
