package gr.dataverse.demoRegLog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gr.dataverse.demoRegLog.pojos.MessageResponce;
import gr.dataverse.demoRegLog.repository.UserRepository;
import gr.dataverse.demoRegLog.model.User;


@Service
public class LoginService {
	
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

	@Autowired
	private UserRepository userRepository;
	
	public MessageResponce handleLogin(String email, String password){
		
		User user = userRepository.findUserByEmail(email);

		
		if( user == null ){
			MessageResponce loginResponse= new MessageResponce("FAILED", "WRONG EMAIL");
			return  loginResponse;
		}
		if( passwordEncoder.matches(password, user.getPassword())) {
			return new MessageResponce("SUCCESS", "LOGIN IN", String.valueOf(user.getUserId()));
		}
			
		return new MessageResponce("FAILED", "WRONG PASSWORD");
	}

}
