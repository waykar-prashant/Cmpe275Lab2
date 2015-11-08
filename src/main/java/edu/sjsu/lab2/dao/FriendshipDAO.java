package edu.sjsu.lab2.dao;

public interface FriendshipDAO {

	public void create(long personId1, long personId2);
	public void delete(long personId1, long personId2);
	
}
