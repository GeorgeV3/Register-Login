package gr.dataverse.demoRegLog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void updateUser(User user) {
		User updateUser = userRepository.findUserByUserId(user.getUserId());
		if(updateUser!=null){
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setTelephone(user.getTelephone());
			updateUser.setCompany(user.getCompany());
			updateUser.setPassword(user.getPassword());
			userRepository.save(updateUser);
		}
	}

}
