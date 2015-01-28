package com.catalog.domain;


public class Address {
	private int idAddress;
	private String country;
	private String city;
	private String street;
	private String house;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public  Address(Address address) {
		this.idAddress = address.idAddress;
		this.country = address.country;
		this.city = address.city;
		this.street = address.street;
		this.house = address.house;
		
	}
	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
	
	
}
