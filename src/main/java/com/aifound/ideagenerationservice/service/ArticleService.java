package com.aifound.ideagenerationservice.service;

import com.aifound.ideagenerationservice.repository.ArticleRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void PreCookArticles() throws IOException {
        FileInputStream file = new FileInputStream(new File("output.xls"));

        // Workbook workbook = new XSSFWorkbook(file);
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(file);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        for (Row row : hssfSheet) {
            for (Cell cell : row) {
                switch(cell.getColumnIndex()) {
                    case 0:
                        System.out.print("0--->");
                        System.out.println(cell);
                        break;
                    case 1:
                        System.out.print("1--->");
                        System.out.println(cell);
                        break;
                    case 2:
                        System.out.print("2--->");
                        System.out.println(cell);
                        break;
                    case 3:
                        System.out.print("3--->");
                        System.out.println(cell);
                        break;
                    default:
                        System.out.print("other--->");
                        System.out.println(cell);
                }
            }
        }
    }
}
