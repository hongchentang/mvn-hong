package com.hcis.hcrp.report.template.dto;

import com.hcis.ipanther.core.utils.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhw
 * @date 2020/4/1
 **/
public class ReportResultDimensionDto {

    private String id;

    private String name;

    private List<ReportResultDimensionDto> secDimensions;

    private String text;

    private String pic;

    private String picId;

    private String chartData;

    private String picConvertFlag;

    public String getPicConvertFlag() {
        return picConvertFlag;
    }

    public void setPicConvertFlag(String picConvertFlag) {
        this.picConvertFlag = picConvertFlag;
    }

    public String getChartData() {
        return chartData;
    }

    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportResultDimensionDto> getSecDimensions() {
        return secDimensions;
    }

    public void setSecDimensions(List<ReportResultDimensionDto> secDimensions) {
        this.secDimensions = secDimensions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPic() {
        if(StringUtils.isNotBlank(pic) && StringUtils.isBlank(picConvertFlag)) {
            pic = pic.replaceAll(" ", "+");
            pic = pic.split("base64,")[1];
            picConvertFlag = "YES";
        }
        return pic;
        //return getImageBase("F:\\temImg\\1c289e2cd7c948c5abe6c9d3edc70910.png");
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    /**
     * 获得图片的base64码
     * @param src x
     * @return x
     * @throws Exception x
     */
    private String getImageBase(String src) throws Exception {
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
}
