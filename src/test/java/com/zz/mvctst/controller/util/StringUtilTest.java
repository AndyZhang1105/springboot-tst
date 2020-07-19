package com.zz.mvctst.controller.util;

import com.zz.util.StringUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Test
    void doNumberAddTest() {
        String r = StringUtil.doNumberAdd("99999999999999999", "33333333333333333333333333333333333");
        Assert.assertEquals(r, "33333333333333333322222222222222222");
    }

}
