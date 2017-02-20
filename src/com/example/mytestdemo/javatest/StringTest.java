package com.example.mytestdemo.javatest;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author zengcq
 * @date 2016年11月16日
 * @version 1.0
 */
public class StringTest {
    static String s3 = "helloworld";
    static String s2 = "hello";
    static String strTest = "/mnt/sdcard/Music/喜欢你.aac";

    private StringTest() {

    }

    private static String getSubStr(String str, int num) {
        String result = "";
        int i = 0;
        while (i < num) {
            int lastFirst = str.lastIndexOf('/');
            result = str.substring(lastFirst) + result;
            System.out.println(result);
            str = str.substring(0, lastFirst);
            i++;
        }
        int pointIndex = result.indexOf('.');
        return result.substring(1,pointIndex);
    }
    public void judge(){
        Scanner input;
        int score = 0;
        char result;
        System.out.println("请输入成绩：");
        try{
        input = new Scanner(System.in);
        score = Integer.parseInt(input.next());

        }catch(Exception e){
        e.printStackTrace();
        System.out.println("输入有误！");
        }
        result = score>=90?'A':score>=80?'B':score>=60?'C':'D';
        System.out.println(score+"分对应的等级是："+result);
        judge();
        }

    public class fuck {

    }

    public static void main(String[] args) {  
        StringTest test = new StringTest();
//        test.judge();
        String string = new String("哈哈");
        String string1 = new String("你妹");
//        String string2 = new String({0xB5,0xB1},"GB2312");
        byte[] b0,b1,b2,b3,b4,b5;
        try {
            b0 = string.getBytes();
            b1 = string.getBytes("UTF-8");
            b2 = string.getBytes("GB2312");
            b3 = string1.getBytes();
            b4 = string1.getBytes("UTF-8");
            b5 = string1.getBytes("BIG5");
            System.out.println(b0);
            System.out.println(b1);
            System.out.println(b2);
            System.out.println(b3);
            System.out.println(b4);
            System.out.println(b5);
            String s0 = new String(b0);
            String s1 = new String(b1,"UTF-8");
            String s2 = new String(b2,"UTF-8");
            String s3 = new String(b3);
            String s4 = new String(b4);
            String s5 = new String(b5);
            System.out.println(s0);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            System.out.println(s4);
            System.out.println(s5);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
       /* System.out.println(getSubStr(strTest, 2));
        String s1 = s3.substring(0, 5);
        if (s1 == s2) {
            System.out.println("s1==s2");
        } else {
            System.out.println("s1!=s2");
        }

        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");
        } else {
            System.out.println("s1 not equals s2");
        }

        double d1 = 113.323618 * (3600 * 2560);
        double d2 = 23.106375 * (3600 * 2560);
        long l1 = (long) d1;
        long l2 = (long) d2;
        long l3 = (long) (113.323618 * (3600 * 2560));
        String lstr1 = String.valueOf(l1);
        String lstr2 = String.valueOf(l2);
        double d3 = (double) l1 / (3600 * 2560);
        double d4 = (double) l2 / (3600 * 2560);

        long lon = (Long.parseLong(lstr1) * 100000) / (3600 * 2560);
        long lat = (Long.parseLong(lstr2) * 100000) / (3600 * 2560);

        // long lng = Long.parseLong(String.valueOf(113.323618*(3600 * 2560)));
        // long lat = Long.parseLong(String.valueOf(23.106375*(3600 * 2560)));
        // String dstInfo = lng+","+lat+",";

        System.out.println(l1 + "");
        System.out.println(l2 + "");
        System.out.println(d3 + "");
        System.out.println(d4 + "");
        System.out.println(lon + "");
        System.out.println(lat + "");
        System.out.println(l3);*/
    }
}

class fucks {

}
