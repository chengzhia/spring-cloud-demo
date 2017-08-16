package com.chengzhi.utils;

import java.util.StringTokenizer;

/**
 * Created by chengzhi on 2017/7/28.
 */
public class StringUtils {

    /**
     * 分割字符串方法  效率 > StringTokenizer >split
     */
    public String[] splitStirng(String value,String splitName) {
        String temp = value;
        for (int i = 0; i < value.length(); i++) {
            int j = temp.indexOf(splitName);
            if (j < 0)break;
            temp.substring(0,j);
            temp = temp.substring(j+1);
        }


        return null;
    }

    /**
     * 使用StringTokenizer分割字符串
     * @param str
     * @param splitName
     * @param flag
     * @return String[]
     */
    public String[] splitStirng(String value,String splitName,boolean flag) {
        String[] args;
        int count = 0;
        StringTokenizer tokenizer = new StringTokenizer(value,splitName,flag);
        args = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens()) {
            args[count] = tokenizer.nextToken();
            count++;
        }
        return args;
    }
    public static void main(String[] args){

    }
}
