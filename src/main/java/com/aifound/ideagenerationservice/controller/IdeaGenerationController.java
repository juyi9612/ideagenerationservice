package com.aifound.ideagenerationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aifound.ideagenerationservice.annotation.DefaultErrorController;
import com.aifound.ideagenerationservice.controller.request.GetIdeasRequest;
import com.aifound.ideagenerationservice.model.IdeaDto;
import com.aifound.ideagenerationservice.service.IdeaService;

@RestController
@ResponseBody
@DefaultErrorController
public class IdeaGenerationController {
    
	private final IdeaService ideaService;

	@Autowired
    public IdeaGenerationController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("test")
	public String test() throws Exception {
        return "hello";
	}
    
    @GetMapping("api/v1/ideas")
	public List<IdeaDto> getIdeas(@RequestBody GetIdeasRequest request) throws Exception {
        List<IdeaDto> dtos = this.ideaService.getIdeasByDomain(request.getDomains(), request.getIdeaCount());
		return dtos;
	}

	// @GetMapping("api/v1/user")
    // public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
    //     if (principal != null) {
    //         model.addAttribute("profile", principal.getClaims());
    //     }
    //     return "index";
    // }
}
