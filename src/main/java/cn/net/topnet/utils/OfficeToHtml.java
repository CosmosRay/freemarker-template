package cn.net.topnet.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.util.ResourceUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeToHtml {

    /**
     * 在springboot项目的resources目录下新建static文件夹，
     * 将需要转换的word文件temp.docx粘贴进去，
     * 由于static是springboot的默认资源文件，
     * 所以不需要在配置文件里面另行配置了，
     * 如果改成其他名字，需要在application.yml进行相应配置。
     * ————————————————
     * doc格式转换为html
     * @return
     * @throws Exception
     */
    public static String docToHtml() throws Exception {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String imagePathStr = path.getAbsolutePath() + "\\static\\image\\";
        String sourceFileName = path.getAbsolutePath() + "\\static\\test1.doc";
        String targetFileName = path.getAbsolutePath() + "\\static\\test2.html";
        File file = new File(imagePathStr);
        if(!file.exists()) {
            file.mkdirs();
        }
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        //保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        return targetFileName;
    }

    /**
     * docx格式转换为html
     * @return
     * @throws Exception
     */
    public static String docxToHtml() throws Exception {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String imagePath = path.getAbsolutePath() + "\\static\\image";
        String sourceFileName = path.getAbsolutePath() + "\\static\\temp.docx";
        String targetFileName = path.getAbsolutePath() + "\\static\\test.html";

        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return targetFileName;
    }

    /**
     * 将转换的HTML输出到页面，，ckeditor
     * 转换成功后会生成对应的html文件，如果想在前端展示，直接读取文件转换为String返回给前端即可
     * @param filePath
     * @return
     */
    public static String readfile(String filePath) {
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for (int n; (n = input.read(bytes)) != -1;) {
                buffer.append(new String(bytes, 0, n, "utf8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
