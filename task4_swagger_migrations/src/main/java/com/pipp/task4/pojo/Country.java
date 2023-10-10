package com.pipp.task4.pojo;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
  @Id
  private Long id;

	private String name;
	private String side;

	public Country() {}
	public Country(String name, String side) {
		this.name = name;
		this.side = side;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Country other = (Country) otherObject;
		return Objects.equals(name, other.name)
			&& Objects.equals(side, other.side);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, side);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name
			+ ", side=" + side + "]";
	}

}
