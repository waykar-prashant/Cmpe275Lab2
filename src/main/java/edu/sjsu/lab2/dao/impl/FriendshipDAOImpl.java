package edu.sjsu.lab2.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.sjsu.lab2.dao.FriendshipDAO;
import edu.sjsu.lab2.model.Person;

public class FriendshipDAOImpl implements FriendshipDAO {

	private SessionFactory sessionFactory;
	
	public FriendshipDAOImpl() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	@Override
	public void create(long personId1, long personId2) {
		Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();

        try {
            tx.begin();
            Person person1 = session.get(Person.class, personId1);
            Person person2 = session.get(Person.class, personId2);

            /*List<Person> friends = person1.getFriends();
            if (!friends.contains(person2)) {
                friends.add(person2);
                session.update(person1);
            }

            friends = person2.getFriends();
            if (!friends.contains(person1)) {
                friends.add(person1);
                session.update(person2);
            }*/
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
	}

	@Override
	public void delete(long personId1, long personId2) {
		Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();

        try {
            tx.begin();
            Person person1 = session.get(Person.class, personId1);
            Person person2 = session.get(Person.class, personId2);

            /*List<Person> friends = person1.getFriends();
            if (friends.contains(person2)) {
                friends.remove(person2);
                session.update(person1);
            } else {
                throw new RuntimeException();
            }

            friends = person2.getFriends();
            if (friends.contains(person1)) {
                friends.remove(person1);
                session.update(person2);
            } else {
                throw new RuntimeException();
            }*/
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
	}

	
}
