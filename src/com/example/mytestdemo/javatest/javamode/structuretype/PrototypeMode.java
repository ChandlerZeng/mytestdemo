package com.example.mytestdemo.javatest.javamode.structuretype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description 原型模式
 */
public class PrototypeMode implements Cloneable, Serializable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 private static final long serialVersionUID = 1L;  
	    private String string;  
	  
	    private SerializableObject obj;  
	  
	    /* 浅复制 */  
	    public Object clone() throws CloneNotSupportedException {  
	    	PrototypeMode proto = (PrototypeMode) super.clone();  
	        return proto;  
	    }  
	  
	    /* 深复制 */  
	    public Object deepClone() throws IOException, ClassNotFoundException {  
	  
	        /* 写入当前对象的二进制流 */  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	        ObjectOutputStream oos = new ObjectOutputStream(bos);  
	        oos.writeObject(this);  
	  
	        /* 读出二进制流产生的新对象 */  
	        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
	        ObjectInputStream ois = new ObjectInputStream(bis);  
	        return ois.readObject();  
	    }  
	  
	    public String getString() {  
	        return string;  
	    }  
	  
	    public void setString(String string) {  
	        this.string = string;  
	    }  
	  
	    public SerializableObject getObj() {  
	        return obj;  
	    }  
	  
	    public void setObj(SerializableObject obj) {  
	        this.obj = obj;  
	    }  
	  
	}  
	  
	class SerializableObject implements Serializable {  
	    private static final long serialVersionUID = 1L;  
	}  



