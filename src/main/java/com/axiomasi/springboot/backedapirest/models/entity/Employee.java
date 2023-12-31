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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "employees")

public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private Long dni;

	@Column(nullable = false, unique = true)
	private Long cuit;

	@NotEmpty
	private String name;

	@NotEmpty
	private String lastname;

	@Temporal(TemporalType.DATE)
	private Date birth;

	@Temporal(TemporalType.DATE)
	private Date registration;

	@NotEmpty
	private String address;

	@NotEmpty
	private String job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id") // anotación para especificar la columna de clave externa
	@JsonBackReference
	private Branch branch;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Sale> sales;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Buy> buys;

	@Transient // para mostrar el nombre de la sucursal en el json
	private String branch_name;

	public Employee() {
		sales = new ArrayList<>();
		buys = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public List<Buy> getBuys() {
		return buys;
	}

	public void setBuys(List<Buy> buys) {
		this.buys = buys;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	
	//Muentra en el json el id de la sucursal
	public Long getBranchId() {

		return branch != null ? branch.getId() : null;
	}
	
	// Muestra las ventas por empleado en el Json
	@JsonProperty("sales_ids")
	public List<Long> getSalesIds() {
		List<Long> salesIds = new ArrayList<>();
		for (Sale sale : sales) {
			salesIds.add(sale.getId());

		}
		return salesIds;
	}

	// Muestra las compras por empleado en el Json
	@JsonProperty("buys_ids")
	public List<Long> getBuysIds() {
		List<Long> buysIds = new ArrayList<>();
		for (Buy buy : buys) {
			buysIds.add(buy.getId());

		}
		return buysIds;
	}

	// para mostrar el nombre de la sucursal en el json
	public String getBranch_name() {
		return branch != null ? branch.getName() : null;
	}
	
	// para mostrar el nombre de la sucursal en el json
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	private static final long serialVersionUID = 1L;

}
