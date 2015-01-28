package com.catalog.domain;

import java.io.Serializable;

public class WebUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String uid;
	private String pwd;
	private int authLevel;
	
	public WebUser()
	{
		//do nothing
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int value)
	{
		id = value;
	}
	
	public String getUserId()
	{
		return uid;
	}
	public void setUserId(String value)
	{
		uid = value;
	}
	
	public String getPassword()
	{
		return pwd;
	}
	public void setPassword(String value)
	{
		pwd = value;
	}
	
	public int getAuthLevel()
	{
		return authLevel;
	}
	public void setAuthLevel(int value)
	{
		authLevel = value;
	}
	
	public String toString()
	{
		return String.format("ID: %d\tUID: %s\tPWD: %s\tAuthLevel: %d\n"
					, getId()
					, getUserId()
					, getPassword()
					, getAuthLevel());
	}
}
