package com.lemonthe.seabattles.pojo;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BattleMember {

	private Long id;

	@NotNull
	private Battle battle;

	@NotNull
	private Warship member;

	@NotBlank
	private String result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public Warship getMember() {
		return member;
	}

	public void setMember(Warship member) {
		this.member = member;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		BattleMember other = (BattleMember) otherObject;
		return Objects.equals(id, other.id)
			&& Objects.equals(battle, other.battle)
			&& Objects.equals(member, other.member)
			&& Objects.equals(result, other.result);

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, battle, member, result);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[id=" + id
			+ ", battle=" + battle
			+ ", member=" + member
			+ ", result=" + result + "]";
	}


}
