package com.zz.util;

import java.io.*;

public class StringUtil {

    public static String streamToString(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }
        return buf.toString();
    }

    /**
     * @param num1
     * @param num2
     * @return  返回结果
     * 1.计算小的那个左边需要补几个0
     * 2.从右边开始一个个的开始相加
     */
    public static String doNumberAdd(String num1, String num2) {
        String str = "";

        int lena = num1.length();
        int lenb = num2.length();

        int maxlength = lena > lenb ? lena : lenb;
        int minlength = lena < lenb ? lena : lenb;

        String strtemp = "";
        for (int i = (maxlength - minlength); i > 0; i--) { // 计算左边需要补几个0
            strtemp += 0;
        }
        if (maxlength == lena) { // 左边补零
            num2 = strtemp + num2;
        } else {
            num1 = strtemp + num1;
        }

        int jw = 0; // 进位
        for (int i = (maxlength - 1); i >= 0; i--) {
            int temp = 0;
            int number1 = Integer.valueOf(String.valueOf(num1.charAt(i)));
            int number2 = Integer.valueOf(String.valueOf(num2.charAt(i)));

            if (number1 + number2 + jw > 10 && i != 0) {
                temp = number1 + number2 + jw - 10;
            } else {
                temp = number1 + number2 + jw;
            }
            str = String.valueOf(temp) + str;
        }

        return str;
    }

}
