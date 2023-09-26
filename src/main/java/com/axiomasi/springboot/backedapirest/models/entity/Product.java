package com.axiomasi.springboot.backedapirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private Long code;

	@NotEmpty
	private String name;

	@NotEmpty
	private String brand;

	@NotNull
	private Double priceSale;

	@NotNull
	private int stock;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date expiration;
	
	private Double priceBuy;
	
    @ManyToOne 
    @JoinColumn(name = "branch_id")
    private Branch branch;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(Double priceSale) {
		this.priceSale = priceSale;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Double getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(Double priceBuy) {
		this.priceBuy = priceBuy;
	}

	private static final long serialVersionUID = 1L;

}
