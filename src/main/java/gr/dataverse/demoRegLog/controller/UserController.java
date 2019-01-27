package gr.dataverse.demoRegLog.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.pojos.RegLogResponse;
import gr.dataverse.demoRegLog.service.LoginService;
import gr.dataverse.demoRegLog.service.RegisterService;
import gr.dataverse.demoRegLog.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private UserService userService;
	
	
	 @RequestMapping(value="/login" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RegLogResponse> loginUserSimple(@RequestParam (value ="email") String email,
	    		@RequestParam (value="password")String password){
	        return ResponseEntity.status(HttpStatus.OK).body(loginService.handleLogin(email, password));
	    }
	    

	    
	    @RequestMapping(value = "/register" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RegLogResponse> registerUser( User user){
	    	return ResponseEntity.status(HttpStatus.OK).body(registerService.handleRegister(user));
	    }
	    
	    
	    @RequestMapping(value = "/user/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> findByUsername(@PathVariable("id")Long id){
	        User user = userService.findUser(id);
	        
	        if(user == null){
	        	   return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	        	  }
	        	  
	        	  return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	    
	    
	    @RequestMapping(value="/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Void> update(@PathVariable("id")Long id , User user){ 
	    	userService.updateUser(id, user);
	     HttpHeaders headers = new HttpHeaders();
	     return new ResponseEntity<Void>(headers, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/changePass/{id}", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RegLogResponse> changePassword(@PathVariable("id") Long id,@RequestParam (value="oldPassword")String oldPassword 
	    		, @RequestParam (value="newPassword")String newPassword){	
	     return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(id, oldPassword, newPassword));
	     
	    }

}
