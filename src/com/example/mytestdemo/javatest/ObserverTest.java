package com.example.mytestdemo.javatest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ29ÈÕ
 * @version 1.0
 * @description
 */
public class ObserverTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Observers observers1 = new Observer1();
        Observers observers2 = new Observer2();
        MySubject mySubject = new MySubject();
        mySubject.add(observers1);
        mySubject.add(observers2);
        mySubject.operation();
    }

}

interface Observers{
    void update();
}

class Observer1 implements Observers{

    @Override
    public void update() {
        // TODO Auto-generated method stub
        System.out.println("observer1 received update!");
    }
    
}

class Observer2 implements Observers{

    @Override
    public void update() {
        // TODO Auto-generated method stub
        System.out.println("observer2 received update!");
    }
    
}

interface Subjects{
    void add(Observers observers);
    void delete(Observers observers);
    void notifyObserver();
    void operation();
}

abstract class AbstractSubject implements Subjects{

    Vector<Observers> vector = new Vector<Observers>();
    List<Observers> list = new ArrayList<Observers>();
    @Override
    public void add(Observers observers) {
        // TODO Auto-generated method stub
        vector.add(observers);
        list.add(observers);
    }

    @Override
    public void delete(Observers observers) {
        // TODO Auto-generated method stub
        vector.remove(observers);
        list.remove(observers);
    }

    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        Enumeration<Observers> enumeration = vector.elements();
        while(enumeration.hasMoreElements()){
            enumeration.nextElement().update();
        }
        
        for(int i = 0;i<list.size();i++){
            list.get(i).update();
            System.out.println("update from list!");
        }
    }
    
}

class MySubject extends AbstractSubject{

    @Override
    public void operation() {
        // TODO Auto-generated method stub
        System.out.println("update self!");
        notifyObserver();
    }
    
}
