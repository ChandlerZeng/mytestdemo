package com.example.mytestdemo.javatest.javaprinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengcq
 * @date 2016年11月23日
 * @version 1.0 
 * 设计模式6大原则：迪米特法则
 * 迪米特法则（Law ofemeter） 定义：一个对象应该对其他对象了解最少 迪米特法则的核心观念就是类间解耦，弱耦合，只有弱耦合了以后，类的复用性才可以提高。
 * 形象一点的比喻类似于：监狱内的犯人是不应该跟外面的人接触的，当然或许会有探亲的。这里的监狱就是类，里面的犯人就是类内部的信息，
 * 而监狱里的狱警就相当于迪米特法则的执行者
 */
public class DemeterPrinciple {
	public static void main(String[] args) {

		TeacherManage tm = new TeacherManage();

		tm.printAllTeacher();

		StudentManage sm = new StudentManage();

		sm.printAllStudent();
		
		Family family = new Family();  
        family.visitPrisoner(new Prisoners()); 

	}
}

class Teacher {

	private String id;

	public void setId(String id)

	{

		this.id = id;

	}

	public String getId()

	{

		return id;

	}

}

class Student {

	private String id;

	public void setId(String id)

	{

		this.id = id;

	}

	public String getId()

	{

		return id;

	}

}

class StudentManage {

	public List<Student> getAllStudent()

	{
		List<Student> list = new ArrayList<Student>();

		for (int i = 0; i < 100; i++)

		{
			Student student = new Student();

			student.setId("学生学号是" + i);

			list.add(student);

		}

		return list;

	}

	public void printAllStudent()

	{

		List<Student> list1 = this.getAllStudent();

		for (Student s : list1)

		{

			System.out.println(s.getId());

		}

	}

}

class TeacherManage

{
	public List<Teacher> getAllTeacher()

	{

		List<Teacher> list = new ArrayList<Teacher>();

		for (int i = 0; i < 100; i++)

		{
			Teacher teacher = new Teacher();

			teacher.setId("老师编号" + i);

			list.add(teacher);

		}

		return list;

	}

	public void printAllTeacher()

	{

		List<Teacher> list2 = this.getAllTeacher();

		for (Teacher t : list2)

		{

			System.out.println(t.getId());

		}

	}

}

class Family {  
    //家人探望犯人  
    public void visitPrisoner(Prisoners prisoners)  
    {  
        System.out.print("家人说：");  
        prisoners.helpEachOther();  
    }  
} 

class Prisoners {  
    private Inmates inmates = new Inmates();  
    public Inmates helpEachOther()  
    {  
        System.out.println("犯人和狱友之间应该互相帮助...");  
        System.out.print("犯人说：");  
        inmates.weAreFriend();  
        return inmates;  
    }  
       
} 

class Inmates {  
    public void weAreFriend()  
    {  
        System.out.println("我们是狱友...");  
    }  
} 

//网上还有如下一些关于应用迪米特法则的注意事项：
//① 在类的划分上，应该创建有弱耦合的类；
//② 在类的结构设计上，每一个类都应当尽量降低成员的访问权限；
//③ 在类的设计上，只要有可能，一个类应当设计成不变类；
//④ 在对其他类的引用上，一个对象对其它对象的引用应当降到最低；
//⑤ 尽量降低类的访问权限；
//⑥ 谨慎使用序列化功能；
//⑦ 不要暴露类成员，而应该提供相应的访问器(属性)。

