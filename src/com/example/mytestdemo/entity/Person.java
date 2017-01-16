package com.example.mytestdemo.entity;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ12ÈÕ
 * @version 1.0
 * @description
 */
public class Person {
	private String name;
	private String number;

	public Person(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
