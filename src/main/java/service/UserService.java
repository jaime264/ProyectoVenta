package service;

import java.util.List;

import model.User;

public interface UserService {

	public List<User> getUsers();
	public User getUser(int id);
	public int addUser(User u);
	public int updateUser(User u);
	public int deleteUser(int id);
	
}
