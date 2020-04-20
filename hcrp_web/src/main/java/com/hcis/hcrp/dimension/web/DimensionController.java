package com.hcis.hcrp.dimension.web;

import com.hcis.hcrp.dimension.entity.Dimension;
import com.hcis.hcrp.dimension.entity.DimensionList;
import com.hcis.hcrp.dimension.entity.Temp;
import com.hcis.hcrp.dimension.service.DimensionService;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*@Author hct
 */
@Controller
@RequestMapping("/dimension")
public class DimensionController {

    @Autowired
    private DimensionService dimensionService;

    @RequestMapping(value = "/getTableData",method= RequestMethod.POST)
    @ResponseBody
    public BaseApi getTable(@RequestBody DimensionList dimensionList) throws UnsupportedEncodingException {

        String reportID="123456";
        SearchParam sewarchParam =new SearchParam();

        List<Dimension> dimensionlist=new ArrayList<Dimension>();

       int rust=dimensionService.deleteByReportID(reportID);
        for (Temp temp:dimensionList.getDimensionList()){
            Dimension dimension = new Dimension();
            dimension.setId(Identities.uuid2());
            dimension.setDimensionStair(temp.getDimensionStair());
            dimension.setDimensionSecond(temp.getDimensionSecond());
            dimension.setTemplateId(reportID);
            dimensionlist.add(dimension);

        }
       dimensionService.batchInsert(dimensionlist);
        BaseApi api = new BaseApi();
        api.setMsg("ok");
        return api;
    }


}
