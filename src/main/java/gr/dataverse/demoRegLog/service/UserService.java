package gr.dataverse.demoRegLog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.pojos.MessageResponce;
import gr.dataverse.demoRegLog.repository.UserRepository;

@Service
public class UserService {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 


	@Autowired
	private UserRepository userRepository;

	public User findUser(Long id) {
		return (User) userRepository.findUserByUserId(id);
	}


	public MessageResponce updateUser(Long id , User user) {	
		User updateUser = userRepository.findUserByUserId(id);
		String firstName =user.getFirstName();
		String lastName =user.getLastName();
		String telephone =user.getTelephone();
		String company =user.getCompany();
		

		if(updateUser!= null){
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

			userRepository.save(updateUser);
		}
		return new MessageResponce("SUCCESS", "YOUR PROFILE UPDATE");
	}
	
	
	public MessageResponce changePassword(Long id , String oldPassword , String newPassword , String confPass){
		User checkUser = userRepository.findUserByUserId(id);
		if( passwordEncoder.matches(oldPassword, checkUser.getPassword())) {
			if(newPassword != null && newPassword.trim().length()!=0 ) {
				if (validatePassword(newPassword) == true && newPassword.equals(confPass) ) {
					checkUser.setPassword(newPassword);
					userRepository.save(checkUser);
					return new MessageResponce("SUCCESS", "NEW PASSWORD REGISTER");
				}else {
					return new MessageResponce("FAILED", "NEW PASSWORD MUST CONTAIN 8 "
							+ "CHAR. ONE NUMBER , ONE LETTER , ONE SPECIAL CHAR @$!%*#?&."
							+ "AND MATCHES WITH CONFIRM PASSWORD");
				}
			}return new MessageResponce("FAILED", "NEW PASSWORD CANNOT BE NULL");
			
		}
		return new MessageResponce("FAILED", "WRONG OLD PASSWORD");
	}
	
	
	public boolean validatePassword(String password){
        return password.matches("(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}");
    }
	

}
