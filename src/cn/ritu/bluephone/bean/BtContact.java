package cn.ritu.bluephone.bean;

import java.io.Serializable;

public class BtContact implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
	private String number;
	private String nameSpell;
	private String nameInital;

	public BtContact(String name, String number) 
	{
		this.name = name;
		this.number = number;
	}

	public BtContact() 
	{
	}

	public String getName() 
	{
		return name;
	}

	public String getNameSpell() 
	{
		return nameSpell;
	}

	public void setNameSpell(String name) 
	{
		this.nameSpell = name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getNumber() 
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getNameInital()
	{
		return nameInital;
	}
	
	public void setNameInital(String nameInital)
	{
		this.nameInital = nameInital;
	}
	
	@Override
	public String toString() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		sb.append(number);
		return sb.toString();
	}
}
