package com.gtfs.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sun.misc.BASE64Encoder;

import com.gtfs.bean.UserLoginDtls;
import com.gtfs.bean.UserMst;
import com.gtfs.service.interfaces.UserLoginDtlsService;
import com.gtfs.service.interfaces.UserMstService;

@Controller
@SessionAttributes("userMst")
public class LoginController implements Serializable{
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private UserLoginDtlsService userLoginDtlsService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String onLoad(){
		System.out.println("Hello");
		return "login";	
	}
	
	
	
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String doLogin(@RequestParam("loginid") String loginid,@RequestParam("password")  String password,Model model) {
		
		try{
			if (!password.equals("abcd")) {
				password = encrypt(password, "SHA", "UTF-8");
			}
			
			List<UserMst> userList = userMstService.login(loginid, password);

			if (userList == null || userList.size() == 0) {
				model.addAttribute("message","Invalid Username or Password..");
				
				return "login";
	        } else {
	        	
	            //List<Object> maxTimeList = userLoginDtlsService.findMaximumLoginTimeOfUser(userList.get(0).getUserid());
	            
	                UserLoginDtls userLoginDtls = new UserLoginDtls();
	                userLoginDtls.setUserid(userList.get(0).getUserid());
	                userLoginDtls.setBranchId(userList.get(0).getBranchMst().getBranchId());
	                userLoginDtls.setLoginTime(new Date());

	                Long id = userLoginDtlsService.insert(userLoginDtls);

	                if (id != null && id > 0l) {
	                	model.addAttribute("userMst",userList.get(0));
	                    return "redirect:chooseBranch";
	                } else {
	                	model.addAttribute("message","Error Occurred..,Please Try Again");
	                	return "login";
	                }
	            
	        }
		}catch(Exception e){
			model.addAttribute("message","Error Occurred..,Please Try Again");
			return "login";
		}
    }

	
	public static synchronized String encrypt(String plaintext, String algorithm, String encoding) {
		MessageDigest msgDigest = null;
		String hashValue = null;
		try {
			msgDigest = MessageDigest.getInstance(algorithm);
			msgDigest.update(plaintext.getBytes(encoding));
			byte rawByte[] = msgDigest.digest();
			hashValue = (new BASE64Encoder()).encode(rawByte);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hashValue;
	}
}
