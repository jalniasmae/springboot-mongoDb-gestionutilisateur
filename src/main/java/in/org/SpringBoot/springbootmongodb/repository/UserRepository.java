package in.org.SpringBoot.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.org.SpringBoot.springbootmongodb.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

	@Query("{'laststName': ?0}")
	Optional <User> findByLaststName(String laststName);
	
	@Query("{'cuid': ?0}")
	Optional <User> findByCuid(String cuid);
	
}
