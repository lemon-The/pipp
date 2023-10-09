package com.lemonthe.seabattles.database.interfaces;

import java.util.List;

public interface DataBaseReader<T, U> {

	List<T> findAll();
	T find(U id);
	
}
