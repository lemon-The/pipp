package com.pipp.task5.dto;

import java.util.Objects;

public class WarshipDTO {
	private Long id;

	private String name;

	private String shipClass;

	private String commissionDate;

	private String decommissionDate;

	private String country;

	public WarshipDTO() {}

	public WarshipDTO(String name, String shipClass, String commissionDate, String decommissionDate, String country) {
    this.name = name;
    this.shipClass = shipClass;
    this.commissionDate = commissionDate;
    this.decommissionDate = decommissionDate;
    this.country = country;
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

	public String getShipClass() {
		return shipClass;
	}

	public void setShipClass(String shipClass) {
		this.shipClass = shipClass;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getDecommissionDate() {
		return decommissionDate;
	}

	public void setDecommissionDate(String deCommissionDate) {
		this.decommissionDate = deCommissionDate;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
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
		WarshipDTO other = (WarshipDTO) otherObject;
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

  //public List<Battle> getBattles() {
  //  return battles;
  //}

  //public void setBattles(List<Battle> battles) {
  //  this.battles = battles;
  //}


}
