package com.axiomasi.springboot.backedapirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Branchs")
public class Branch implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String address;

	@NotEmpty
	private String city;

	@NotEmpty
	private String state;

	@NotEmpty
	private String phone;

	@OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> products;

	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Provider> providers;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Customer> customers;

	@OneToMany(mappedBy = "buy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Buy> buys;

	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Sale> sales;

	public Branch() {
		employees = new ArrayList<>();
		products = new ArrayList<>();
		providers = new ArrayList<>();
		customers = new ArrayList<>();
		buys = new ArrayList<>();
		sales = new ArrayList<>();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Buy> getBuys() {
		return buys;
	}

	public void setBuys(List<Buy> buys) {
		this.buys = buys;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	private static final long serialVersionUID = 1L;

}
