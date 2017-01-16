package com.example.mytestdemo.javatest;


/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ29ÈÕ
 * @version 1.0
 * @description
 */
public class InterfaceTest implements ViewClickListener{
public static void main(String[] args){
    Student student1 = new Student("Joy", 0001);
    Student student2 = new Student("Chandler", 0002);
    student1.goToSchool("8 am");
    student1.goHome("6 pm");
    student2.goToSchool("8 am");
    student2.goHome("6 pm");
    
    Teacher teacher1 = new Teacher1("Jack");
    teacher1.goToSchool("9 am");
    teacher1.goHome("5 pm");
    ClickInterfaceTest clickInterfaceTest = new ClickInterfaceTest();
    InterfaceTest interfaceTest = new InterfaceTest();
    ClickInterfaceClicked clicked = new ClickInterfaceClicked(clickInterfaceTest);
    ClickInterfaceClicked clicked2 = new ClickInterfaceClicked(interfaceTest);
    clicked.executeClick();
    clicked2.executeClick();
    
}

@Override
public void onClick() {
    // TODO Auto-generated method stub
    System.out.println("clicked by InterfaceTest!");
}
}

interface testInterface1{
    String getName();
    int getId();
    void goToSchool(String time);
    void goHome(String time);
}

abstract class Teacher implements testInterface1{
    private String name;
    public Teacher(String name){
        this.name = name;
        System.out.println("my name is "+name);
    }
    public void goToSchool(String time) {
        System.out.println("now is "+time+","+"it is time to goToSchool!");
    }
    
    public void goHome(String time) {
        System.out.println("now is "+time+","+"it is time to goHome!");
    }
}

class Teacher1 extends Teacher{

    public Teacher1(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

class Student implements testInterface1{

    private String nameString;
    private int Id;
    
    public Student(String name, int id){
        this.nameString = name;
        this.Id = id;
    }
    
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return nameString;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return Id;
    }

    @Override
    public void goToSchool(String time) {
        // TODO Auto-generated method stub
        System.out.println("my name is "+getName()+", my Id is "+getId());
        System.out.println("now is "+time+", it is time to go to school!");
    }

    @Override
    public void goHome(String time) {
        // TODO Auto-generated method stub
        System.out.println("now is "+time+", it is time to go home!");
    }
    
}

interface ViewClickListener{
    void onClick();
}

class ClickInterfaceClicked {
    ViewClickListener clicked;
    public ClickInterfaceClicked(ViewClickListener clickInterfaceTest){
       this.clicked = clickInterfaceTest; 
    }
    
    public void executeClick(){
        clicked.onClick();
    }
}
class ClickInterfaceTest implements ViewClickListener{

    @Override
    public void onClick() {
        // TODO Auto-generated method stub
        System.out.println("clicked by ClickInterfaceTest!");
    }
    
}
