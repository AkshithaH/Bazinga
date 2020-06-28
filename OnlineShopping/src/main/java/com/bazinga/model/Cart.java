package com.bazinga.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Akshitha H
 *
 */
@Entity
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int productId;
	@Column
	private String productName;
	@Column
	private double productPrice;
	@Column
	private String productDescription;
	@Column
	private Integer quantity;
	
	public Cart() {
		
	}
	/**
	 * @param productId
	 * @param productName
	 * @param productPrice
	 * @param productDescription
	 * @param quantity
	 */
	public Cart(Integer productId, String productName, double productPrice, String productDescription, Integer quantity) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.quantity = quantity;
	}
	/**
	 * @param productName
	 * @param productPrice
	 * @param productDescription
	 * @param quantity
	 */
	public Cart(String productName, double productPrice, String productDescription, Integer quantity) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.quantity = quantity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return
	 */
	public boolean ifexists(Cart cart) {
		// TODO Auto-generated method stub
		return (cart.getProductId() == null)?false:true;
	}
}
