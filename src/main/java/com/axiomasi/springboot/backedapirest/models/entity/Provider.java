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
@Table(name = "providers")
public class Provider implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String dni;

	@NotNull
	private String phone;

	@NotEmpty
	private String payment;

	@NotEmpty
	private String address;

	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_at")
	private Date dateAt;

	@ManyToOne
	@JoinColumn(name = "branch_id") // Agregar una columna branch_id para la clave externa
	@JsonBackReference
	private Branch branch;

	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Buy> buys;
	
    @Transient // para mostrar el nombre de la sucursal en el json
    private String branch_name;
    
	@Temporal(TemporalType.DATE)
	private Date registration;

	public Provider() {
		buys = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		dateAt = new Date();
	}

	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAt() {
		return dateAt;
	}

	public void setDateAt(Date dateAt) {
		this.dateAt = dateAt;
	}
	
	// Usamos @JsonProperty para indicar que queremos mostrar solo el ID de la relación branch
    @JsonProperty("branch_id")
    public Long getBranchId() {
        return branch != null ? branch.getId() : null;
    }

    // Usamos @JsonProperty para indicar que queremos mostrar solo los IDs de las sales
    @JsonProperty("sales_ids")
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

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	private static final long serialVersionUID = 1L;

}
