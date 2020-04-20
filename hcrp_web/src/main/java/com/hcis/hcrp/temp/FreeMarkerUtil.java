package com.hcis.hcrp.temp;

import com.hcis.hcrp.report.template.dto.ReportResultDto;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


/**
 * @author z
 */
@Component
public class FreeMarkerUtil {

    private static Logger log = Logger.getLogger(FreeMarkerUtil.class);
    private static final String ENCODING = "UTF-8";
    private static Configuration cfg = new Configuration();

    @Resource(name="mailFreeMarkerConfigurer")
    private  FreeMarkerConfigurer mailFreeMarkerConfigurer;

    //初始化cfg
    static {
        //设置模板所在文件夹
        cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/templates/word");
        // setEncoding这个方法一定要设置国家及其编码，不然在ftl中的中文在生成html后会变成乱码
        cfg.setEncoding(Locale.getDefault(), ENCODING);
        // 设置对象的包装器
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        // 设置异常处理器,这样的话就可以${a.b.c.d}即使没有属性也不会出错
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

    }

    //获取模板对象
    public Template getTemplate(String templateFileName) throws IOException {
        return mailFreeMarkerConfigurer.getConfiguration().getTemplate(templateFileName);
    }

    /**
     * 据数据及模板生成文件
     * @param data             Map的数据结果集
     * @param templateFileName ftl模版文件名
     * @param outFilePath      生成文件名称(可带路径)
     * @return f
     */
    public File crateFile(ReportResultDto data, String templateFileName, String outFilePath) {
        Writer out = null;
        File outFile = new File(outFilePath);
        try {
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            Template template = getTemplate(templateFileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new OutputStreamWriter(new FileOutputStream(outFile), ENCODING);
            // 处理模版

            template.process(data, out);
            out.flush();
            log.info("由模板文件" + templateFileName + "生成" + outFilePath + "成功.");
        } catch (Exception e) {
            log.error("由模板文件" + templateFileName + "生成" + outFilePath + "出错");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("关闭Write对象出错", e);
                e.printStackTrace();
            }
        }
        return outFile;
    }

    /**
     * 获得图片的base64码
     * @param src x
     * @return x
     * @throws Exception x
     */
    public String getImageBase(String src) throws Exception {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}