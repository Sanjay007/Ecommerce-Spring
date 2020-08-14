package com.ecom.app.payload;

import javax.validation.constraints.NotEmpty;

import com.ecom.app.model.Colors;
import com.ecom.app.model.Gender;

public class ProductInput {

    @NotEmpty(message = "Please provide a name")
	private String productName;
    
    @NotEmpty(message = "Please provide a Description")
	private String productDescription;
    
	private String SKU;
	
    @NotEmpty(message = "Please provide a Price")
	private Integer price;
	private Colors color;
	private Gender gender;
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Colors getColor() {
		return color;
	}
	public void setColor(Colors color) {
		this.color = color;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	
}
