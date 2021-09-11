package model;

import java.io.Serializable;

public class Billboard implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Attributes
	private double width;
	private double height;
	private boolean inUse;
	private String brand;
	//private double area;
	//Builder
	
	public Billboard(double width, double height, boolean inUse, String brand ) {
		
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}


	//Methods
	public double calculateArea(double width, double height)
	{
		double area = width * height;
		return area;
	
	}
	
	
	
	//Getters and Setters
	public double getWidth() {
		return width;
	}


	public double getHeight() {
		return height;
	}


	public boolean isInUse() {
		return inUse;
	}


	public String getBrand() {
		return brand;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}
