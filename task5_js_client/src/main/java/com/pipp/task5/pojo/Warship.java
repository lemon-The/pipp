package com.pipp.task5.pojo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Warship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String shipClass;

  @ManyToMany(mappedBy = "participants")
  private List<Battle> battles;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate commissionDate;

	//@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate decommissionDate;

  @ManyToOne
	private Country country;

	public Warship() {}

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

	public String getShipClass() {
		return shipClass;
	}

	public void setShipClass(String shipClass) {
		this.shipClass = shipClass;
	}

	public LocalDate getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(LocalDate commissionDate) {
		this.commissionDate = commissionDate;
	}

	public LocalDate getDecommissionDate() {
		return decommissionDate;
	}

	public void setDecommissionDate(LocalDate deCommissionDate) {
		this.decommissionDate = deCommissionDate;
	}

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Warship other = (Warship) otherObject;
		return Objects.equals(name, other.name)
			&& Objects.equals(shipClass, other.shipClass)
			&& Objects.equals(commissionDate, other.commissionDate)
			&& Objects.equals(decommissionDate, other.decommissionDate)
			&& Objects.equals(country, other.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, shipClass, commissionDate,
				decommissionDate, country);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name
			+ ", shipClass=" + shipClass
			+ ", commissionDate=" + commissionDate
			+ ", deCommissionDate=" + decommissionDate 
			+ ", country=" + country + "]";
	}

  public List<Battle> getBattles() {
    return battles;
  }

  public void setBattles(List<Battle> battles) {
    this.battles = battles;
  }

}
