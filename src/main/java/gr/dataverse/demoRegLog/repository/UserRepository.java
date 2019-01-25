package gr.dataverse.demoRegLog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import gr.dataverse.demoRegLog.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	 User findUserByEmail(String email);
	 User findUserByUserId(Long id);

	

}
