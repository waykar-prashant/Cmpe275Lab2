package edu.sjsu.lab2.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.lab2.dao.PersonDAO;
import edu.sjsu.lab2.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private SessionFactory sessionFactory;

	public PersonDAOImpl() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	public void create(Person person) {
		// session.getCurrentSession().save(person);
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			/*
			 * if (person.getOrganization() != null) { Organization org =
			 * session.get(Organization.class,
			 * person.getOrganization().getId()); if (org != null) {
			 * person.setOrganization(org); } else { throw new
			 * RuntimeException(); } }
			 */
			session.save(person);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Person person = session.get(Person.class, id);
			/*
			 * List<Person> friends = person.getFriends(); for (Person friend :
			 * friends) { friends.remove(friend);
			 * friend.getFriends().remove(person); session.update(friend); }
			 */
			session.update(person);
			session.delete(person);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public Person update(Person personModified) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Person person = session.get(Person.class, personModified.getId());
			person.setEmail(personModified.getEmail());
			if (personModified.getFirstname() != null) {
				person.setFirstname(personModified.getFirstname());
			}
			if (personModified.getLastname() != null) {
				person.setLastname(personModified.getLastname());
			}
			if (personModified.getDescription() != null) {
				person.setDescription(personModified.getDescription());
			}
			if (personModified.getAddress() != null) {
				person.setAddress(personModified.getAddress());
			}
			/*
			 * if (personModified.getOrganization() != null) { Organization org
			 * = session.get(Organization.class,
			 * personModified.getOrganization().getId()); if (org != null) {
			 * person.setOrganization(org); } else { throw new
			 * RuntimeException(); } }
			 */
			session.update(person);
			tx.commit();
			return person;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Person read(long id) {
		Session session = sessionFactory.openSession();
		try {
			Person person = session.get(Person.class, id);
			if (person != null) {
				return person;
			} else {
				throw new RuntimeException();
			}
		} finally {
			session.close();
		}
	}

}
