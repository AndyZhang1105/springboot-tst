package com.zz.tst.objecttst;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnalyzeObjectS extends AnalyzeObjectP {

    private String type;

    public AnalyzeObjectS(String name, float weight, String type) {
        super(name, weight);
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(! (obj instanceof AnalyzeObjectS) ) {
            return false;
        }

        AnalyzeObjectS aObj = (AnalyzeObjectS) obj;
        return super.equals(aObj) && aObj.type.equals(this.type);
    }

    @Override
    protected AnalyzeObjectS clone() throws CloneNotSupportedException {
        AnalyzeObjectS c = (AnalyzeObjectS) super.clone(); // 如果在没有实现 Cloneable接口的实例上调用Object的clone()方法，则会导致抛出CloneNotSupportedException异常
        return c;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
