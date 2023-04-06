package in.org.SpringBoot.springbootmongodb.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import in.org.SpringBoot.springbootmongodb.exception.UserCollectionException;
import in.org.SpringBoot.springbootmongodb.model.User;

public interface UserService  {

	public void createUser(User user) throws ConstraintViolationException, UserCollectionException;

	public List<User> getAllUsers();

	public User getUserById(Long id)throws UserCollectionException;

	public void updateUser(Long id, User user) throws UserCollectionException;

	public void deleteUserById(Long id) throws UserCollectionException;
}
