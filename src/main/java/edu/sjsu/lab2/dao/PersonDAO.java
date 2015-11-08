package edu.sjsu.lab2.dao;

import edu.sjsu.lab2.model.Person;

public interface PersonDAO {

	public void create(Person person);
	public void delete(long id);
	public Person update(Person person);
	public Person read(long id);
	
}
