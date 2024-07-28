package com.aifound.ideagenerationservice.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.aifound.ideagenerationservice.service.ArticleService;
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
    private final ArticleService articleService;

	@Autowired
    public IdeaGenerationController(IdeaService ideaService, ArticleService articleService) {
        this.ideaService = ideaService;
        this.articleService = articleService;
    }

    @GetMapping("test")
	public void test() throws Exception {
        // JSON data
        // 二进制数据数组
        int[] imageData = {
                137, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 200, 0, 0, 0, 200, 8, 6, 0, 0, 0, 173, 88, 174, 158, 0, 0, 0, 6, 98, 75, 71, 68, 0, 255, 0, 255, 0, 255, 160, 189, 167, 147, 0, 0, 1, 119, 73, 68, 65, 84, 120, 156, 237, 211, 177, 13, 128, 48, 0, 3, 193, 132, 253, 119, 14, 45, 213, 23, 8, 20, 132, 238, 38, 112, 225, 31, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 111, 238, 30, 240, 152, 181, 214, 238, 9, 92, 204, 249, 139, 111, 29, 187, 7, 192, 151, 9, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 32, 8, 4, 130, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120, 201, 9, 68, 146, 3, 200, 32, 60, 75, 82, 0, 0, 0, 0, 73, 69, 78, 68, 174, 66, 96, 130
        };

        // 将 int 数组转换为 byte 数组
        byte[] byteArray = new byte[imageData.length];
        for (int i = 0; i < imageData.length; i++) {
            byteArray[i] = (byte) imageData[i];
        }

        // 写入图像文件
        try (FileOutputStream fos = new FileOutputStream("output_image.png")) {
            fos.write(byteArray);
            System.out.println("图像文件已成功生成");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    @GetMapping("api/v1/ideas")
	public List<IdeaDto> getIdeas(@RequestBody GetIdeasRequest request) throws Exception {
        List<IdeaDto> dtos = this.ideaService.getIdeasByDomain(request.getDomains(), request.getIdeaCount());
		return dtos;
	}

    @GetMapping("api/v1/articles")
    public void cookArticles() throws Exception {
        this.articleService.PreCookArticles();
    }

	// @GetMapping("api/v1/user")
    // public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
    //     if (principal != null) {
    //         model.addAttribute("profile", principal.getClaims());
    //     }
    //     return "index";
    // }
}
