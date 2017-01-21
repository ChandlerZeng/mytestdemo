package com.example.mytestdemo.javatest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

/**
 * @author Zengcq
 * @date 2017年1月21日
 * @version 1.0
 * @description
 */
public class IOStream {
    public static void main(String[] args){
//        try {
//            System.out.print("输入字符: ");
//            System.out.println("输入字符十进制表示: " +
//                                    System.in.read());
            fileIOStream();
            bufferedIOStream("D:/自媒体/企鹅媒体/20170120/1.jpeg","D:/testjava/abc4.txt");
            bufferedIOStream("D:/自媒体/企鹅媒体/20170120/1.jpeg","D:/testjava/1.jpeg");
            bufferedIOStream("C:\\Users\\Administrator\\Desktop\\contacts2.db","D:/testjava/contacts2.db");
            bufferedIOStream("C:\\Users\\Administrator\\Desktop\\contacts2.db","D:/testjava/contacts2.txt");
            InOutStream("hahhahah哈哈哈哈2B哈");
//            bufferReaderWriterTest();
            bufferedReaderWriterStream("你大爷的45545f54454547777啊傻逼");
            arrayCopyTest();
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }                       
    }
    
    private static void InOutStream(String content){
       try {
        OutputStream outputStream = new FileOutputStream("D:/testjava/abc3.txt");
        outputStream.write(content.getBytes());
        System.out.println("复制文件content：" +content.length() + "字节");
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    
    private static void fileIOStream(){
        try {
            byte[] buffer = new byte[1024];
            // 来源文件
            FileInputStream fileInputStream =
                new FileInputStream(new File("D:/testjava/abc.txt"));
            // 目的文件
            FileOutputStream fileOutputStream =
                new FileOutputStream(new File("D:/testjava/abc1.txt"));
            // available()可取得未读取的数据长度
            System.out.println("复制文件：" +
                    fileInputStream.available() + "字节");
          
            while(true) {
                if(fileInputStream.available() < 1024) {
                    // 剩余的数据比1024字节少
                    // 一位一位读出再写入目的文件
                    System.out.println("fileInputStream.available1()"+fileInputStream.available());
                    int remain = -1;
                    while((remain = fileInputStream.read())
                                           != -1) {
                        fileOutputStream.write(remain);
                    }
                    System.out.println("fileInputStream.available2()"+fileInputStream.available());
                    break;
                }
                else {
                    // 从来源文件读取数据至缓冲区
                    fileInputStream.read(buffer);
                    // 将数组数据写入目的文件
                    fileOutputStream.write(buffer);
                    System.out.println("fileInputStream.available3()"+fileInputStream.available());
                }
            }
            // 关闭流
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("复制完成");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(
                      "using: java FileStreamDemo src des");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    
    }
    
    private static void bufferedIOStream(String copyFrom, String copyTo){
        try {
            byte[] data = new byte[1024];
            File srcFile = new File(copyFrom);
            File desFile = new File(copyTo);
            BufferedInputStream bufferedInputStream =
                new BufferedInputStream(
                         new FileInputStream(srcFile));
            BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(
                         new FileOutputStream(desFile));
            System.out.println("复制文件：" +
                             srcFile.length() + "字节");
            while(bufferedInputStream.read(data) != -1) {
                bufferedOutputStream.write(data);
                System.out.println("bufferedInputStream.available()"+bufferedInputStream.available());
            }
          
            // 将缓冲区中的数据全部写出
            bufferedOutputStream.flush();
            // 关闭流
            bufferedInputStream.close();
            bufferedOutputStream.close();
            System.out.println("复制完成");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "using: java UseFileStream src des");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void bufferedReaderWriterStream(String content){
        char[] buffer = new char[1024];        
        BufferedReader reader = null;
        BufferedWriter writer = null;
        int len = -1;
        try {
            reader = new BufferedReader(new StringReader(content));
            writer = new BufferedWriter(new FileWriter("D:/testjava/abc6.txt",true));
//            String r = reader.readLine();
//            writer.write(r);
            while((len = reader.read(buffer,0,10))!=-1){
                writer.write(buffer,0,len);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void bufferReaderWriterTest(){
        try  
        {  
            //缓冲System.in输入流  
            //System.in是位流，可以通过InputStreamReader将其转换为字符流  
            System.out.println("请输入字符进行存储(输入“quit”退出存储)："); 
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));  
            //缓冲FileWriter  
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("D:/testjava/abc5.txt"));  
  
            String input = null;  
            int count = 0;
  
            //每读一行进行一次写入动作  
            while(!(input = bufReader.readLine()).equals("quit"))  
            {  
                bufWriter.write(input);  
                //newLine()方法写入与操作系统相依的换行字符，依执行环境当时的OS来决定该输出那种换行字符  
                bufWriter.newLine(); 
                count += input.length();
            }  
            bufReader.close();  
            bufWriter.close();  
            System.out.println("已存储："+count+"字节"); 
        }  
        catch(ArrayIndexOutOfBoundsException e)  
        {  
            System.out.println("没有指定文件");  
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    private static void arrayCopyTest(){
        byte[] data1="hello world".getBytes();
        byte[] data2 = "2000".getBytes();
        
        byte[] data3 = new byte[data1.length+data2.length];
        
        System.arraycopy(data1,0,data3,0,data1.length);
        System.out.println(new String(data3));
        //1.要拷贝复制的原始数据
        //2.原始数据的读取位置(从原始数据哪个位置开始拷贝)
        //3.存放要拷贝的原始数据的目的地
        //4.开始存放的位置()
        //5.要读取的原始数据长度(拷贝多长)
        System.arraycopy(data2,0,data3,data1.length,data2.length);
        System.out.println(new String(data3));
        
        System.out.println(data2.length);
        byte[] head = new byte[data2.length];
        //拷贝data3的后4位到head中
        System.arraycopy(data3,data3.length-4,head,0,head.length);
        System.out.println(new String(head));
    }
}
