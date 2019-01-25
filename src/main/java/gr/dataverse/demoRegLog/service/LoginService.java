package gr.dataverse.demoRegLog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gr.dataverse.demoRegLog.pojos.RegLogResponse;
import gr.dataverse.demoRegLog.repository.UserRepository;
import gr.dataverse.demoRegLog.model.User;


@Service
public class LoginService {
	
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

	@Autowired
	private UserRepository userRepository;
	
	public RegLogResponse handleLogin(String email, String password){
		
		User user = userRepository.findUserByEmail(email);

		
		if( user == null ){
			RegLogResponse loginResponse= new RegLogResponse("FAILED", "WRONG EMAIL");
			return  loginResponse;
		}
		if( passwordEncoder.matches(password, user.getPassword())) {
			return new RegLogResponse("SUCCESS", "LOGIN IN", String.valueOf(user.getUserId()));
		}
			
		return new RegLogResponse("FAILED", "WRONG PASSWORD");
	}

}
