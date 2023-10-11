package com.pipp.task4.pojo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Battle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

  @ManyToMany
  private List<Warship> participants;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	//private List<Warship> participants;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	//public List<Warship> getParticipants() {
	//	return participants;
	//}

	//public void setParticipants(List<Warship> participants) {
	//	this.participants = participants;
	//}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Battle other = (Battle) otherObject;
		return Objects.equals(name, other.name)
			&& Objects.equals(date, other.date);
			//&& Objects.equals(participants, other.participants);

	}

	@Override
	public int hashCode() {
		return Objects.hash(name, date/*, participants*/);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name
			+ ", date=" + date + "]";
			//+ ", participants=" + participants + "]";
	}

  public List<Warship> getParticipants() {
    return participants;
  }

  public void setParticipants(List<Warship> participants) {
    this.participants = participants;
  }
}
