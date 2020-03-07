package cn.net.topnet.utils;

import cn.net.topnet.controller.FreemarkerController;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
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
public class HtmlUtils {
    public static void testHtml(HttpServletRequest request) throws IOException, TemplateException {
        //创建一个模板文件
        //创建一个Configuration对象
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        String file = request.getServletContext().getRealPath("/template");
        //创建配置实例
        Configuration configuration = new Configuration();
        //设置编码
        configuration.setDefaultEncoding("UTF-8");
        //设置模板文件保存的目录
        configuration.setClassForTemplateLoading(HtmlUtils.class, "/template");
        //设置模板文件的编码格式
        configuration.setDefaultEncoding("UTF-8");

        //加载一个模板文件，创建一个模板对象
        String html = request.getServletContext().getRealPath("/template");
        File htmlFile = new File(html, "test.html");
        System.out.println(htmlFile);
        Template template = configuration.getTemplate("test.ftl");
        //创建一个数据集可以是pojo，也可以是map，一般使用map
        Map<String, Object> maps = new HashMap<String, Object>();
        //将项目名称存进map中
        maps.put("title", request.getContextPath());
//        List<CommodityList> list = null;
        //获取id
//        int commodityStockOrderID = Integer.parseInt(request.getParameter("commodityStockOrderID"));       
//        StockCommodityBean StockCommodity = new StockCommodityBean();
        // 调用servic层的方法查询出需要的数据
//        StockCommodity = dao.selectDetails(commodityStockOrderID);
        //获取到list集合
//        list = StockCommodity.getCommList();
        //将list保存进去
        maps.put("TSnum", "2020list");
        maps.put("TSsum", "2020info");
        //生成静态页面
        template.process(maps, new FileWriter(htmlFile));
        //关闭流
        //out.close();
    }
}
