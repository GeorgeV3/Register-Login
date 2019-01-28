package gr.dataverse.demoRegLog.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import gr.dataverse.demoRegLog.model.User;
import gr.dataverse.demoRegLog.pojos.MessageResponce;
import gr.dataverse.demoRegLog.pojos.RecaptchaResponce;
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
	@Autowired
	RestTemplate restTemplate;


	@RequestMapping(value="/login" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponce> loginUserSimple(@RequestParam (value ="email") String email,
			@RequestParam (value="password")String password){
		return ResponseEntity.status(HttpStatus.OK).body(loginService.handleLogin(email, password));
	}



	@RequestMapping(value = "/register" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponce> registerUser(@RequestParam(value="g-recaptcha-response")String captchaResponce , User user){


//	    	I have problem with validate token of the captcha from google so 
//			i disable it and make check the box only about null and empty data....

//	    	String url="https://www.google.com/recaptcha/api/siteverify";
//	    	String params="?secret=6Lc_Po0UAAAAANIFZroaAmIrWtLALiAcZHInUOFw"+captchaResponce;
//	    	
//	    	RecaptchaResponce recaptchaResponce=restTemplate.exchange(url+params,HttpMethod.POST,null, RecaptchaResponce.class).getBody();
//	    	if(recaptchaResponce.isSuccess())
		if (captchaResponce.equals(null) || captchaResponce.equals("")) {
			return ResponseEntity.status(HttpStatus.OK).body(registerService.handleRecaptcha());
		}


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


	@RequestMapping(value="/update/{id}", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponce> update(@PathVariable("id")Long id , User user){ 
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));

	}


	@RequestMapping(value="/changePass/{id}", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponce> changePassword(@PathVariable("id") Long id,@RequestParam (value="oldPassword")String oldPassword 
			, @RequestParam (value="newPassword")String newPassword, @RequestParam (value="confPass")String confPass){	
		return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(id, oldPassword, newPassword, confPass));

	}

}
