package in.org.SpringBoot.springbootmongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.org.SpringBoot.springbootmongodb.exception.UserCollectionException;
import in.org.SpringBoot.springbootmongodb.model.User;
import in.org.SpringBoot.springbootmongodb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;   

	@Override
	public void createUser(User user) throws ConstraintViolationException, UserCollectionException {
		Optional<User> userOptional= userRepository.findByLaststName(user.getLaststName());
		if (userOptional.isPresent()) {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExists());
		}else {
			user.setCreateDate(new Date(System.currentTimeMillis()));
			userRepository.save(user);
		}


	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		if(users.size()>0) {
			return users;
		}else {
			return new ArrayList<User>();
		}
	}

	@Override
	public User getUserById(Long id) throws UserCollectionException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
		}else {
			return optionalUser.get();
		}

	}

	@Override
	public void updateUser(Long id, User user) throws UserCollectionException {
		Optional<User> userWithId = userRepository.findById(id);
		Optional<User> userWithSameName = userRepository.findByLaststName(user.getFirstName());


		if (userWithId.isPresent()) {
			if (userWithSameName.isPresent() && userWithSameName.get().getId ().equals(id)){
				throw new UserCollectionException(UserCollectionException.UserAlreadyExists());
			}
			User userToUpdate  = userWithId.get();
			userToUpdate.setFirstName(user.getFirstName());
			userToUpdate.setLaststName(user.getLaststName());
			userToUpdate.setBirthDate(user.getBirthDate());
			userToUpdate.setCreateDate(user.getCreateDate());
			userToUpdate.setUpdateDate(new Date(System.currentTimeMillis()));
			userRepository.save(userToUpdate);


		}else {
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
		}


	}

	@Override
	public void deleteUserById(Long id) throws UserCollectionException {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
		}else {
			userRepository.deleteById(id);
		}
	}

}
