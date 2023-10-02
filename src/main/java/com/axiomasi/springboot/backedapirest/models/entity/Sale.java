package com.axiomasi.springboot.backedapirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sales")
public class Sale implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_alta")
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sale")
	@JsonBackReference
	private List<ItemSale> itemsSale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Branch branch;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public Sale() {
	 itemsSale = new ArrayList<>();
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<ItemSale> getItemsSale() {
		return itemsSale;
	}

	public void setItemsSale(List<ItemSale> itemsSale) {
		this.itemsSale = itemsSale;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getTotal() {

		Double total = 0.0;

		int size = itemsSale.size();

		for (int i = 0; i < size; i++) {
			total += itemsSale.get(i).calculatePay();

		}
		return total;

	}
	
	   @JsonProperty("itemSale_ids")
	    public List<Long> getItemSaleIds() {
	        List<Long> itemSalesIds = new ArrayList<>();
	        for (ItemSale itemSale : itemsSale) {
	        	itemSalesIds.add(itemSale.getId());
	           
	        }
	        return itemSalesIds;
	    }
	   


	private static final long serialVersionUID = 1L;
}