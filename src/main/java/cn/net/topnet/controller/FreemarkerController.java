package cn.net.topnet.controller;

import cn.net.topnet.service.FreemarkerService;
import cn.net.topnet.utils.HtmlUtils;
import cn.net.topnet.utils.OfficeToHtml;
import cn.net.topnet.utils.WordUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with CosmosRay
 *
 * @author CosmosRay
 * @date 2020/03/06
 * Funciton:
 */
@RestController
@RequestMapping("/")
public class FreemarkerController {
    @Autowired
    private FreemarkerService freemarkerService;

    @GetMapping("/text")
    @ResponseBody
    public void text(HttpServletResponse response) {
        try {
            Map<String, Object> dataMap = freemarkerService.exportWordFile(response);
            WordUtils.exportMillCertificateWord(response, dataMap, "E:/test/", "test.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/chart")
    @ResponseBody
    public void chart(HttpServletResponse response) {
        try {
            Map<String, Object> dataMap = freemarkerService.exportWordFile(response);
            WordUtils.exportMillCertificateWord(response, dataMap, "E:/test/", "chart4.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/html")
    @ResponseBody
    public void html(HttpServletRequest request) throws IOException, TemplateException {
        HtmlUtils.testHtml(request);
    }
    @GetMapping("/docToHtml")
    @ResponseBody
    public void docToHtml(HttpServletRequest request) throws Exception {
        OfficeToHtml.docToHtml();
    }
    @GetMapping("/docxToHtml")
    @ResponseBody
    public void docxToHtml(HttpServletRequest request) throws Exception {
        OfficeToHtml.docxToHtml();
    }
}
