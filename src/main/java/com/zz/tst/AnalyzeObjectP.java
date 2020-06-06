package com.zz.tst;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnalyzeObjectP implements Cloneable {

    private String name;
    private float weight;

    public AnalyzeObjectP(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(! (obj instanceof AnalyzeObjectP) ) {
            return false;
        }

        AnalyzeObjectP aObj = (AnalyzeObjectP) obj;
        return aObj.name.equals(this.name) && aObj.weight == this.weight;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + Float.floatToIntBits(weight);
        return result;
    }

    @Override
    protected AnalyzeObjectP clone() throws CloneNotSupportedException {
        AnalyzeObjectP c = (AnalyzeObjectP) super.clone(); // 如果在没有实现 Cloneable接口的实例上调用Object的clone()方法，则会导致抛出CloneNotSupportedException异常
        return c;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Here is finalize. Sometime it will be called.");
    }

}