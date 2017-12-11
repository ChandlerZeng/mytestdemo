package com.example.mytestdemo.javatest.javamode.behaviortype;


/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 顾名思义，迭代器模式就是顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，
 * 理解本模式会十分轻松。
 * 这句话包含两层意思：一是需要遍历的对象，即聚集对象，二是迭代器对象，用于对聚集对象进行遍历访问。
 */
public class IteratorMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection collection = new MyCollection();
		Iterators iterators = collection.iterator();
		
		while(iterators.hasNext()){
			System.out.println(iterators.next());
		}
		while(iterators.hasPrevious()){
			System.out.println(iterators.previous());	
		}
		while(iterators.hasNext()){
			System.out.println(iterators.next());
		}
		while(iterators.hasPrevious()){
			System.out.println(iterators.previous());	
		}
		
		System.out.println(collection.size());
		System.out.println(collection.get(2));
		System.out.println(iterators.first());
		System.out.println(iterators.last());

	}

}

interface Collection {  
    
    public Iterators iterator();  
      
    /*取得集合元素*/  
    public Object get(int i);  
      
    /*取得集合大小*/  
    public int size();  
}  

interface Iterators {  
    //前移  
    public Object previous();  
      
    //后移  
    public Object next();  
    public boolean hasNext();  
    public boolean hasPrevious();  
      
    //取得第一个元素  
    public Object first();  
    
  //取得最后一个元素  
    public Object last();  
}  

class MyCollection implements Collection {  
	  
    public String string[] = {"A","B","C","D","E"};  
    @Override  
    public Iterators iterator() {  
        return new MyIterator(this);  
    }  
  
    @Override  
    public Object get(int i) {  
        return string[i];  
    }  
  
    @Override  
    public int size() {  
        return string.length;  
    }  
}  

class MyIterator implements Iterators {  
	  
    private Collection collection;  
    private int pos = -1;  
      
    public MyIterator(Collection collection){  
        this.collection = collection;  
    }  
      
    @Override  
    public Object previous() {  
        if(pos > 0){  
            pos--;  
        }  
        return collection.get(pos);  
    }  
  
    @Override  
    public Object next() {  
        if(pos<collection.size()-1){  
            pos++;  
        }  
        return collection.get(pos);  
    }  
  
    @Override  
    public boolean hasNext() {  
        if(pos<collection.size()-1){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    
    @Override  
    public boolean hasPrevious() {  
        if(pos>0){  
            return true;  
        }else{  
            return false;  
        }  
    }
  
    @Override  
    public Object first() {  
        pos = 0;  
        return collection.get(pos);  
    }

	@Override
	public Object last() {
		// TODO Auto-generated method stub
		return collection.get(collection.size()-1);
	}  
  
}  

/*
 * 此处我们貌似模拟了一个集合类的过程，感觉是不是很爽？其实JDK中各个类也都是这些基本的东西，加一些设计模式，
 * 再加一些优化放到一起的，只要我们把这些东西学会了，
 * 掌握好了，我们也可以写出自己的集合类，甚至框架！
 */

