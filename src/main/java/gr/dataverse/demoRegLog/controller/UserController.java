package gr.dataverse.demoRegLog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.pojos.RegLogResponse;
import gr.dataverse.demoRegLog.service.LoginService;
import gr.dataverse.demoRegLog.service.RegisterService;

@RestController
public class UserController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RegisterService registerService;
	
	
	 @RequestMapping(value="/login" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RegLogResponse> loginUserSimple(@RequestParam (value ="email") String email,
	    		@RequestParam (value="password")String password){
	        return ResponseEntity.status(HttpStatus.OK).body(loginService.handleLogin(email, password));
	    }
	    

	    
	    @RequestMapping(value = "/register" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RegLogResponse> registerUser( User user){
	    	return ResponseEntity.status(HttpStatus.OK).body(registerService.handleRegister(user));
	    }

}
