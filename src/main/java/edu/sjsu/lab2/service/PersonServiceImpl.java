package edu.sjsu.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.sjsu.lab2.dao.PersonDAO;
import edu.sjsu.lab2.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO personDao;
	

	@Override
	public void create(Person person) {
		personDao.create(person);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person update(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
