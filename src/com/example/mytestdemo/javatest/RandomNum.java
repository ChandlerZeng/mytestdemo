package com.example.mytestdemo.javatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.R.integer;

/**
 * @author Zengcq
 * @date 2017年1月4日
 * @version 1.0
 * @description
 */
public class RandomNum {
    long randomNum;
    public static void main(String[] args) {
//      for(int i=0;i<100;i++){
//          System.out.println(getRandomNumber());
//      }
    }
    
    public static String GetRandomString(int Len) {
        
        String[] baseString={"0","1","2","3",
                "4","5","6","7","8","9",
                "a","b","c","d","e",
                "f","g","h","i","j",
                "k","l","m","n","o",
                "p","q","r","s","t",
                "u","v","w","x","y",
                "z","A","B","C","D",
                "E","F","G","H","I",
                "J","K","L","M","N",
                "O","P","Q","R","S",
                "T","U","V","W","X","Y","Z"};
        String[] baseString2={"0","1","2","3",
                "4","5","6","7","8","9","A"};
        Random random = new Random();
        int length=baseString2.length;
        String randomString="";
        for(int i=0;i<length;i++){
            randomString+=baseString2[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr="";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length()-1));
        }
        return resultStr;
    }
    
    public static List<String> getRandomNumber(){
        List<String> list = new ArrayList<String>();
        for(int i =0;i<10;i++){
            double rand = Math.random();
            if(rand<0.1){
                rand+=0.1;
            }
            long random = (long) ( 10000000000L*rand); 
            list.add(String.valueOf(random));
        }   
        return list;
    }
    
    public static List<String> getRandomNums1(){
        return getRandomNums(1, 10);
    }
    
    public static List<String> getRandomNums10(){
        return getRandomNums(10, 10);
    }
    
    public static List<String> getRandomNums(int nums,int lens){
        System.out.println("生成"+nums+"个"+lens+"位的随机字符串");
        List<String> list = new ArrayList<String>();
        for(int i=0;i<nums;i++){
            String resultString = GetRandomString(lens);
            System.out.println(resultString);
            list.add(resultString);
        }
        return list;
    }
}
