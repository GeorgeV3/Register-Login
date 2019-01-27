package gr.dataverse.demoRegLog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.pojos.RegLogResponse;
import gr.dataverse.demoRegLog.repository.UserRepository;

@Service
public class UserService {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 


	@Autowired
	private UserRepository userRepository;

	public User findUser(Long id) {
		return (User) userRepository.findUserByUserId(id);
	}


	public void updateUser(Long id , User user) {	
		User updateUser = userRepository.findUserByUserId(id);
		String firstName =user.getFirstName();
		String lastName =user.getLastName();
		String telephone =user.getTelephone();
		String company =user.getCompany();
		String password =null;
		password =user.getPassword();

		if(updateUser!=null){
			if(firstName != null && firstName.trim().length()!=0) {
				updateUser.setFirstName(firstName);
			}
			if(lastName != null && lastName.trim().length()!=0) {
				updateUser.setLastName(lastName);
			}
			if(telephone != null && telephone.trim().length()!=0) {
				updateUser.setTelephone(telephone);
			}
			if(company != null && company.trim().length()!=0) {
				updateUser.setCompany(company);
			}
			if(password != null && password.trim().length()!=0) {
				updateUser.setPassword(password);
			}

			userRepository.save(updateUser);
		}
	}
	
	
	public RegLogResponse changePassword(Long id , String oldPassword , String newPassword){
		User checkUser = userRepository.findUserByUserId(id);
		if( passwordEncoder.matches(oldPassword, checkUser.getPassword())) {
			if(newPassword != null && newPassword.trim().length()!=0 ) {
			checkUser.setPassword(newPassword);
			userRepository.save(checkUser);
			return new RegLogResponse("SUCCESS", "NEW PASSWORD REGISTER");
			}return new RegLogResponse("FAILED", "NEW PASSWORD CANNOT BE NULL");
			
		}

		return new RegLogResponse("FAILED", "WRONG OLD PASSWORD");
	}

}
