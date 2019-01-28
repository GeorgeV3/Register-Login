package gr.dataverse.demoRegLog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.org.apache.bcel.internal.generic.NEW;

import gr.dataverse.demoRegLog.model.User;

import gr.dataverse.demoRegLog.pojos.MessageResponce;
import gr.dataverse.demoRegLog.repository.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public MessageResponce handleRecaptcha() {
		return new MessageResponce("FAILED","PLZ CHECK THE BOX");
		
	}


	public MessageResponce handleRegister(User user) {

		User userCheck = new User();
		userCheck =userRepository.findUserByEmail(user.getEmail());

		if( userCheck == null ){

			userRepository.save(user);
			MessageResponce registerResponce= new MessageResponce("SUCCESS", "User registration complete."
					,String.valueOf(user.getUserId()));
			return  registerResponce;
		}
		return new MessageResponce("FAILED", "DUPLICATE EMAIL"
				,"Email : " + String.valueOf(userCheck.getEmail())+" exist.Plz provide new email");
	}

}
