package edu.sjsu.lab2.service;

import edu.sjsu.lab2.model.Person;

public interface PersonService {
	public void create(Person person);
	public void delete(long id);
	public Person update(Person person);
	public Person read(long id);
}
