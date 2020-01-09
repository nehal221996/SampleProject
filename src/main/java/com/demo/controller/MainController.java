package com.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Admin;
import com.demo.model.User;
import com.demo.service.UserService;
import com.demo.util.PDFGenerator;
import com.demo.util.SHA;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

@Controller
@RequestMapping(value= "/user")
public class MainController 
{
	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView blankRequest()
	{
		ModelAndView model = new ModelAndView();
		Admin admin=new Admin();
		model.addObject("admin",admin);
		model.setViewName("Login");
		return model;
	}
	@RequestMapping(value= "/login", method = RequestMethod.POST )
	public ModelAndView login(@ModelAttribute("admin") Admin admin, HttpServletRequest request)
	{
		ModelAndView model=new ModelAndView();
		
		if(admin.getUname().equals("admin") && admin.getPass().equals("admin")) 
		{
			model.setViewName("redirect:display");
		}
		else 
		{
			String pass = encryptThisString(admin.getPass());
			User u = (User) userService.getUserDetail(admin.getUname(),pass);
			if(u != null)
			{
				HttpSession session = request.getSession(); //session management for login
				session.setAttribute("user", u);
				model.setViewName("redirect:profile");
			}
			else
			{
				String msg = "Login Error";
	    		model.addObject("msg", msg);
				model.setViewName("redirect:index");
			}
		}
		return model;
		
	}
	@RequestMapping(value= "/profile", method = RequestMethod.GET )
	public ModelAndView profile(@ModelAttribute("admin") Admin admin, HttpServletRequest request)
	{
		    ModelAndView model=new ModelAndView();
			HttpSession session = request.getSession();
			User u =  (User) session.getAttribute("user");
			
			if(u == null)
			{
				model.setViewName("redirect:index");
				
			}
			else
			{	
				
				System.out.println(u.getFirstname()+" "+u.getPassword());
				model.addObject("user",u);
				String base64Encoded = Base64.getEncoder().encodeToString(u.getProfile());
				model.addObject("profileImge",base64Encoded);
				model.setViewName("myProfile");
			}
		return model;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		if(u != null)
		{
			model.setViewName("redirect:index");
		}
		else
		{
			User user = new User();
			model.addObject("user",user);
			model.setViewName("register");
		}
		return model;
	}
	
	@RequestMapping(value ="/registerData", method = RequestMethod.POST)
	public ModelAndView regData(@RequestParam("id") int id,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,
			@RequestParam("email") String email,@RequestParam("mobile") String mobile,
			@RequestParam("password") String password,@RequestParam("gender") String gender,@RequestParam("doj") String doj,
			@RequestParam(name="profile",required = false) MultipartFile file,HttpServletRequest request) throws IOException
	{
		System.out.println(file);
		User user = new User();
		SHA sha=new SHA();
		if (file != null && !file.isEmpty()) 
		{
			byte[] bFile = new byte[(int) file.getSize()];
			bFile = file.getBytes();
			user.setProfile(bFile);
		}
		
		HttpSession session=request.getSession();
		
		/*sha.encryptThisString(password);*/
		user.setId(id);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(encryptThisString(password));
		user.setGender(gender);
		user.setDoj(doj);
		System.out.println(encryptThisString(password));
		/*userService.InsertData(user);
		
		ModelAndView m=new ModelAndView();
	        Admin admin = new Admin();
	    	m.addObject("admin",admin);
	        m.setViewName("Login");
	       */
		System.out.println(user);
		ModelAndView m =  new ModelAndView();
	        if(user.getId() > 0) 
	        {
	        	userService.UpdateDataById(user);
	        	m.addObject("user",user);
				m.setViewName("myProfile");
				String msg = "Successfully Updated";
	    		m.addObject("msg", msg);
	        	
	        }
	        else
	        {
	        	userService.InsertData(user);
	        	Admin admin = new Admin();
	    		m.addObject("admin",admin);
	        	m.setViewName("Login");
	        	String msg = "Registration Successfully";
	    		m.addObject("msg", msg);
	        }
		
		return m;	
	}
	
	@RequestMapping("/display")
	public ModelAndView show(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		ModelAndView m = new ModelAndView();
		if(u != null)
		{
			m.setViewName("redirect:index");
		}
		else
		{
			List<User> udata = userService.getUserList();
			System.out.println("udata");
			System.out.println(udata);
			/*String base64Encoded = Base64.getEncoder().encodeToString(u.getProfile());
			m.addObject("profileImge",base64Encoded);*/
			m.addObject("udata",udata);
			m.setViewName("viewUsers");
		}
		return m;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteData(@RequestParam("did") int did)
	{
		ModelAndView m = new ModelAndView();
		userService.deleteData(did);
		m.setViewName("redirect:display");
		return m;
	}
	
	@RequestMapping(value="/update")
	public ModelAndView updateData(@RequestParam("uid") int uid) 
	{
		ModelAndView m = new ModelAndView();
		User user = userService.getDataById(uid);
		m.addObject("user", user);
		m.setViewName("update");
		return m;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView openLogout(HttpSession session)
	{

		session.invalidate();
		// session = request.getSession(false);
		// System.out.println("Session : " + session);
		ModelAndView page = new ModelAndView();
		Admin admin=new Admin();
		page.addObject("admin",admin);
		page.setViewName("redirect:/user/index");
		/*String msg = "You are Successfully Logout";
		page.addObject("msg", msg);*/
		return page;
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		ModelAndView page = new ModelAndView();
		page.setViewName("resetPassword");
		return page;		
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ModelAndView updatePass(HttpServletRequest request,Model model,@RequestParam("pass_old") String password,
			@RequestParam("pass") String new_password)
	{
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int id=u.getId();
		String passwordDB = null;

		List<User> usr=userService.getUserById(id);
		for(User user: usr)
		{
			passwordDB = user.getPassword();
		}
		
		String pass_old = encryptThisString(password);
		System.out.println("encrypted old password"+pass_old);
		System.out.println("old password:" + password);
		System.out.println("new password:" + new_password);
		
		String newpassword=encryptThisString(new_password);
		System.out.println("encrypted new password"+newpassword);
		
			userService.updatePassword(newpassword,id);
			String msg = "Your Password is Successfully Reset";
			model.addAttribute("msg", msg);
			ModelAndView page = new ModelAndView();
			page.setViewName("myProfile");
			return page;
		
		
	}
	
	// representing the line chart doj vs no. of employee
	@RequestMapping(value="/line")
	public ModelAndView showHome(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		ModelAndView model=new ModelAndView();
		if(u != null)
		{
			model.setViewName("redirect:index");
		}
		else
		{
			model.setViewName("LineChart");
		}
		return model;
	}
	
	@RequestMapping(value = "/linechartdata")
	public String getDataFromDisplay1(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int sid=u.getId();
		System.out.println(sid);
		
		List<User> user=userService.findAll(sid);
		return null;
		
	}
	
	
	
	// download the employee list in pdf
	@GetMapping(value = "/empdownload", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customerReport(HttpServletRequest request) throws IOException
	{
		/*int id=Integer.parseInt(request.getParameter("id"));*/
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int sid=u.getId();
		/*int sid=(Integer) session.getAttribute("id");*/
		System.out.println(sid);
		
		List<User> user=userService.findAll(sid);
		ByteArrayInputStream bis = PDFGenerator.customerPDFReport(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employee_list.pdf");
		
		return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	
		
	}
	
	
	
	public static String encryptThisString(String input) 
	{
		try 
		{	
			// getInstance() method is called with algorithm SHA-1 
			MessageDigest md=MessageDigest.getInstance("SHA-1");
			
			// digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
			byte[] messageDigest = md.digest(input.getBytes());
			
			// Convert byte array into signum representation
			BigInteger no=new BigInteger(1, messageDigest);
			
			 // Convert message digest into hex value 
			String hashtext=no.toString(16);
			
			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32)
			{ 
                hashtext = "0" + hashtext; 
            } 
			
			// return the HashText
			return hashtext;
		} 
		
		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}
}
