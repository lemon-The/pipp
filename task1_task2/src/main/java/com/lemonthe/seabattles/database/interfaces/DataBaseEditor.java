package com.lemonthe.seabattles.database.interfaces;

public interface DataBaseEditor<T, U> {

	void delete(U id);
	void update(T object);
	void save(T object);
	
}
