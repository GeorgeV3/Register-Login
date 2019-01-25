package gr.dataverse.demoRegLog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.dataverse.demoRegLog.model.User;

import gr.dataverse.demoRegLog.pojos.RegLogResponse;
import gr.dataverse.demoRegLog.repository.UserRepository;

@Service
public class RegisterService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public RegLogResponse handleRegister(User user) {

		User userCheck = new User();
		userCheck =userRepository.findUserByEmail(user.getEmail());
		
		if( userCheck == null ){
			
			userRepository.save(user);
			RegLogResponse registerResponce= new RegLogResponse("SUCCESS", "User registration complete."
					,String.valueOf(user.getUserId()));
			return  registerResponce;
		}
		return new RegLogResponse("FAILED", "DUPLICATE EMAIL"
				,"Email : " + String.valueOf(userCheck.getEmail())+" exist.Plz provide new email");
	}

}
