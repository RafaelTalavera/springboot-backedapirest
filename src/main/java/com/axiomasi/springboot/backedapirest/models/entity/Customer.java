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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotNull
	private Long dni;

	@NotEmpty
	private String name;

	@NotEmpty
	private String lastname;

	private String phone;

	private String payment;

	private String address;

	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	private Date registration;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Sale> sales;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	@JsonBackReference
	private Branch branch;

	@Transient // para mostrar el nombre de la sucursal en el json
	private String branch_name;

	@PrePersist
	public void prePersist() {
	 registration = new Date();
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer() {
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setTelefono(String phone) {
		this.phone = phone;
	}

	public String getPayment() {
		return payment;
	}

	public void setMetodoPago(String payment) {
		this.payment = payment;
	}

	public String getAddress() {
		return address;
	}

	public void setDireccion(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	// Usamos @JsonProperty para indicar que queremos mostrar solo el ID de la
	// relación branch
	@JsonProperty("branch_id")
	public Long getBranchId() {
		return branch != null ? branch.getId() : null;
	}

	// Usamos @JsonProperty para indicar que queremos mostrar solo los IDs de las
	// sales
	@JsonProperty("sales_ids")
	public List<Long> getSalesIds() {
		List<Long> salesIds = new ArrayList<>();
		for (Sale sale : sales) {
			salesIds.add(sale.getId());

		}
		return salesIds;
	}

	// para mostrar el nombre de la sucursal en el json
	public String getBranch_name() {
		return branch != null ? branch.getName() : null;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	private static final long serialVersionUID = 1L;

}
