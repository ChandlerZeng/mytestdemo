package com.example.mytestdemo.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016年12月15日
 * @version 1.0
 * @description
 * 主要目的是保存一个对象的某个状态，以便在适当的时候恢复对象，个人觉得叫备份模式更形象些，
 * 通俗的讲下：假设有原始类A，A中有各种属性，A可以决定需要备份的属性，
 * 备忘录类B是用来存储A的一些内部状态，
 * 类C呢，就是一个用来存储备忘录的，且只能存储，不能修改等操作。
 */
public class MementoMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Original original = new Original("before","before");
		String restore = original.getValue();
		Storage storage = new Storage(original.createMemento());
		

        System.out.println("初始化状态为：" + original.getValue());  
        
		// 修改原始类的状态  
        original.setValue("after");
        System.out.println("修改后的状态为：" + original.getValue());  
        
        // 回复原始类的状态  
        original.restoreMemento(storage.getMemento());
        System.out.println("恢复后的状态为：" + original.getValue());  
        System.out.println("恢复后的状态为：" + restore);  
        
        Memento memento = new Memento("before", "before");
        Storage storage2 = new Storage(memento);
        System.out.println("初始化状态为：" + memento.getValue()+memento.getKey());
        memento.setKey("after");
        memento.setValue("after");
        System.out.println("修改后的状态为：" + memento.getValue()+memento.getKey());
        System.out.println("恢复后的状态为：" + storage2.getMemento().getKey()+storage2.getMemento().getValue());  
        
	}

}
class Original {  
    
    private String value;  
    private String key;  
      
    public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    }  
  
    public Original(String value,String key) {  
        this.value = value;  
        this.key = key;
    }  
  
    public Memento createMemento(){  
        return new Memento(value,key);  
    }  
      
    public void restoreMemento(Memento memento){  
        this.value = memento.getValue();  
    }  
}  

class Memento{
	private String value;
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Memento(String value,String key){
		this.value = value;
		this.key = key;
	}
	
	public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    } 
}

class Storage{
	private Memento memento;

	public Storage(Memento memento){
		this.memento = memento;
	}
	
	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
	
}