package com.example.mytestdemo.javatest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** * 
 @author  zengcq
 @date 创建时间：2017年6月19日
 @version 1.0 
 */
public class LinkedArrayList {

	static List<Integer> array=new ArrayList<Integer>();  
    static List<Integer> linked=new LinkedList<Integer>();  
  
    public static void main(String[] args) {  
  
  
        for(int i=0;i<10000;i++){  
            array.add(i);  
            linked.add(i);  
        }  
        System.out.println("array time:"+getTime(array));  
        System.out.println("linked time:"+getTime(linked));  
        System.out.println("array insert time:"+insertTime(array));  
        System.out.println("linked insert time:"+insertTime(linked));  
  
    }  
    public static long getTime(List list){  
        long time=System.currentTimeMillis();  
        for(int i=0;i<10000;i++){  
            int index=Collections.binarySearch(list, list.get(i));  
            if(index!=i){  
                System.out.println("ERROR!");  
            }  
        }  
        return System.currentTimeMillis()-time;  
    }  
    public static long insertTime(List list){  
        long time=System.currentTimeMillis();  
        for(int i=100;i<10000;i++){  
            list.add(1000,i);     
        }  
        return System.currentTimeMillis()-time;  
          
    }   

}
