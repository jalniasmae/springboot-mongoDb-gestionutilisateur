package in.org.SpringBoot.springbootmongodb.controller;

import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.org.SpringBoot.springbootmongodb.exception.UserCollectionException;
import in.org.SpringBoot.springbootmongodb.model.User;
import in.org.SpringBoot.springbootmongodb.repository.UserRepository;
import in.org.SpringBoot.springbootmongodb.service.UserService;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")

public class UserController {

	

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> users= userService.getAllUsers();
		return new ResponseEntity<>(users , users.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}


	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user){
		try {
			userService.createUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}	 



	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id)
	{
		try {
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}	



	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id,@RequestBody User user )
	{
		try {
			userService.updateUser(id, user);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		} catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id )
	{   try {
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}catch(UserCollectionException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}	
	}

	// version1 

	//	@GetMapping("/users")
	//	public ResponseEntity<?> getAllUsers()
	//	{
	//		List<User> users= userRepository.findAll();
	//		if (users.size() > 0) {
	//			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	//		}
	//		else {
	//			return new ResponseEntity<>("No user available",HttpStatus.NOT_FOUND);
	//		}
	//		
	//	}

	//	@PostMapping("/users")
	//	public ResponseEntity<?> createUser(@RequestBody User user){
	//		try {
	//			user.setCreateDate(new Date(System.currentTimeMillis()));
	//			userRepository.save(user);
	//			return new ResponseEntity<User>(user, HttpStatus.OK);
	//		} catch (Exception e) {
	//			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	//		}
	//	}



	//	@GetMapping("/users/{id}")
	//	public ResponseEntity<?> getUserById(@PathVariable("id") Long id)
	//	{
	//		Optional<User> userOptional= userRepository.findById(id);
	//		if(userOptional.isPresent()) {
	//			return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
	//		}
	//		else {
	//			return new ResponseEntity<>("User not found with the Id"+id, HttpStatus.OK);
	//		}
	//		
	//	}


	//	
	//	@PutMapping("/users/{id}")
	//	public ResponseEntity<?> updateById(@PathVariable("id") Long id,@RequestBody User user )
	//	{
	//		Optional<User> userOptional= userRepository.findById(id);
	//		if(userOptional.isPresent()) {
	//			User userToSave = userOptional.get();
	//			userToSave.setId(user.getId() != null  ? user.getId() : userToSave.getId());
	//			userToSave.setFirstName(user.getFirstName() !=null ? user.getFirstName() : user.getFirstName() );
	//			userToSave.setLaststName(user.getLaststName() !=null ? user.getLaststName() : user.getLaststName() );
	//			userToSave.setBirthDate(user.getBirthDate()!=null ? user.getBirthDate() : user.getBirthDate());
	//			userToSave.setUpdateDate(new Date(System.currentTimeMillis()));
	//			userRepository.save(userToSave);
	//			
	//			return new ResponseEntity<>(userToSave , HttpStatus.OK);
	//		}
	//		else {
	//			return new ResponseEntity<>("User not found with the Id"+id, HttpStatus.OK);
	//		}	
	//	}


	//	@DeleteMapping("/users/{id}")
	//	public ResponseEntity<?> deleteById(@PathVariable("id") Long id )
	//	{   try {
	//			userRepository.deleteById(id);
	//			return new ResponseEntity<>("Succefuly deleted with id "+id, HttpStatus.OK);
	//		}catch(Exception e) {
	//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	//		}	
	//	}



}
