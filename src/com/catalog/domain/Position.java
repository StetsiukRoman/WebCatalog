package com.catalog.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPosition;
	private String describePos;
	
	public Position()  {
		// TODO Auto-generated constructor stub
	}

	public int getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

	public String getDescribePos() {
		return describePos;
	}

	public void setDescribePos(String describePos) {
		this.describePos = describePos;
	}
	
	@Override
	public String toString() {
	   return "DataObject [describePos=" + describePos + "]";
	}
}
