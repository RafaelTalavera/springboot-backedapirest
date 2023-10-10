package com.axiomasi.springboot.backedapirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "buys")
public class Buy implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_alta")
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "buy")
	private List<ItemBuy> itemsBuy;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	private Supplier supplier;

	@ManyToOne(fetch = FetchType.LAZY)
	private Branch branch;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void setProvider(Supplier supplier) {
		this.supplier = supplier;
	}

	public Buy() {
		itemsBuy = new ArrayList<>();
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Supplier getProvider() {
		return supplier;
	}

	public void setCustumer(Supplier supplier) {
		this.supplier = supplier;
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

	public List<ItemBuy> getItemsBuy() {
		return itemsBuy;
	}

	public void setItemsBuy(List<ItemBuy> itemsBuy) {
		this.itemsBuy = itemsBuy;
	}

	public Double getTotal() {

		Double total = 0.0;

		int size = itemsBuy.size();

		for (int i = 0; i < size; i++) {
			total += itemsBuy.get(i).calculatePayment();

		}
		return total;

	}

	private static final long serialVersionUID = 1L;
}
