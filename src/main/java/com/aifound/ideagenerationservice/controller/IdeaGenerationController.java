package com.aifound.ideagenerationservice.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aifound.ideagenerationservice.entity.RedBookEntity;
import com.aifound.ideagenerationservice.model.RPARedBookDto;
import com.aifound.ideagenerationservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aifound.ideagenerationservice.annotation.DefaultErrorController;
import com.aifound.ideagenerationservice.controller.request.GetIdeasRequest;
import com.aifound.ideagenerationservice.model.IdeaDto;
import com.aifound.ideagenerationservice.service.IdeaService;

@RestController
@ResponseBody
@DefaultErrorController
public class IdeaGenerationController {
    
	private final IdeaService ideaService;
    private final ArticleService articleService;

	@Autowired
    public IdeaGenerationController(IdeaService ideaService, ArticleService articleService) {
        this.ideaService = ideaService;
        this.articleService = articleService;
    }

    @GetMapping("test")
	public void test() throws Exception {
	}
    
//    @GetMapping("api/v1/ideas")
//	public List<IdeaDto> getIdeas(@RequestBody GetIdeasRequest request) throws Exception {
//        List<IdeaDto> dtos = this.ideaService.getIdeasByDomain(request.getDomains(), request.getIdeaCount());
//		return dtos;
//	}
//
//    @GetMapping("api/v1/cook/articles")
//    public void cookArticles() throws Exception {
//        this.articleService.preCookArticles();
//    }
//
//    @GetMapping("api/v1/cook/redbook")
//    public void cookRedBooks(@RequestParam int count) throws Exception {
//        if (count < 1 || count > 100) {
//            throw new Exception("Invalid input: [count].");
//        }
//        this.articleService.preCookRedBookContent(count);
//    }

    @GetMapping("api/v1/redbook/mock")
    public List<RPARedBookDto> rpaRedBook(@RequestParam int count) throws Exception {
        List<String> images = new ArrayList<>();
        images.add("https://posters.blob.core.windows.net/posters/b6a558cc-3cc4-4768-b057-6c85af6478f1_1.png?sp=r&st=2024-08-04T12:50:14Z&se=2025-04-30T20:50:14Z&sv=2022-11-02&sr=c&sig=ECAXSu%2BW32rDJMHaOrh7rzY6dUvsISy2wp1iC52mTi8%3D");
        images.add("https://posters.blob.core.windows.net/posters/b6a558cc-3cc4-4768-b057-6c85af6478f1_2.png?sp=r&st=2024-08-04T12:50:14Z&se=2025-04-30T20:50:14Z&sv=2022-11-02&sr=c&sig=ECAXSu%2BW32rDJMHaOrh7rzY6dUvsISy2wp1iC52mTi8%3D");
        images.add("https://posters.blob.core.windows.net/posters/b6a558cc-3cc4-4768-b057-6c85af6478f1_3.png?sp=r&st=2024-08-04T12:50:14Z&se=2025-04-30T20:50:14Z&sv=2022-11-02&sr=c&sig=ECAXSu%2BW32rDJMHaOrh7rzY6dUvsISy2wp1iC52mTi8%3D");
        images.add("https://posters.blob.core.windows.net/posters/b6a558cc-3cc4-4768-b057-6c85af6478f1_4.png?sp=r&st=2024-08-04T12:50:14Z&se=2025-04-30T20:50:14Z&sv=2022-11-02&sr=c&sig=ECAXSu%2BW32rDJMHaOrh7rzY6dUvsISy2wp1iC52mTi8%3D");
        String pi = "我开发了几个产品，其中最成功的是TalkNotes，一款AI语音笔记应用。它通过月订阅的方式获利。其他产品包括Sales Popup，这是一款显示销售通知以提高转化率的工具，以及Maker Ads Guide，这是一本关于Facebook广告的指南。";
        String hi = "我是Nicolas Jeanne，曾经从事银行工作，但三天后就辞职了，因为我无法忍受被人指挥。我转向电子商务，然后接触到了广告，最后成为了一名独立开发者。";
        String sl = "1. 从非技术背景转向独立开发 \n2. 锁在酒店房间里学习编程 \n3. 通过广告经验推动产品成长";
        String gtm = "1. 利用在线目录进行早期验证 \n2. 通过SEO获取长期流量 \n3. 在Product Hunt上进行产品发布，积累初始用户";
        String ln = "1. 找到了有效的广告转化方式\n2. 学会了如何快速验证产品创意\n3. 深入研究了文案写作技巧并应用于广告";
        RPARedBookDto dto = new RPARedBookDto(
                "北美独立开发者11个月赚20万美金\uD83D\uDCB8",
                String.format("Founder\n%s\n\nIdea\n%s\n\nThe story\n%s\n\nGo to market\n%s\n\nLearnning\n%s",
                        pi, hi, sl, gtm, ln),
                "广告优化\nAI语音笔记\n独立开发",
                images
        );

        List<RPARedBookDto> dtos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dtos.add(dto);
        }

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
