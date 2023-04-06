package in.org.SpringBoot.springbootmongodb.exception;

public class UserCollectionException extends Exception{



	private static final long serialVersionUID = 1L;

	public UserCollectionException(String message) {
		super(message);
	}

	public static String NotFoundException(Long id) {
		return "User with id "+id+"not found !";
	}

	public static String UserAlreadyExists() {
		return "User with given name already exists";
	}

}
