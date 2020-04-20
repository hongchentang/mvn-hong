package com.hcis.hcrp.dimension.entity;

import java.io.Serializable;
import java.util.List;

/*@Author hct
 */
public class DimensionList implements Serializable {


    private List<Temp> dimensionList;

    public List<Temp> getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(List<Temp> dimensionList) {
        this.dimensionList = dimensionList;
    }
}
