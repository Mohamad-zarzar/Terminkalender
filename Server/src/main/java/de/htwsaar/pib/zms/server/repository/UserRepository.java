package de.htwsaar.pib.zms.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.htwsaar.pib.zms.server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/*@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	*/
	
	public User findByUsername(String username);

	public User deleteByUsername(String username);
}

