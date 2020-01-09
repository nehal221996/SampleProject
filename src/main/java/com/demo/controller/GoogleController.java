package com.demo.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Admin;
import com.demo.model.User;
import com.demo.service.UserService;
import com.demo.util.SHA;


@Controller
public class GoogleController 
{
	@Autowired
	UserService userService;
	
	/*@Autowired
	private JavaMailSender javaMailSender;*/
	
	
	@RequestMapping(value="/callback")
	public String signPage(WebRequest request, Model model, HttpServletRequest req, HttpSession session)
			throws MalformedURLException, IOException, ParseException
	{
		String code = request.getParameter("code");
		System.out.println(code);
		System.out.println("controller");
		String tokeUrl="https://www.googleapis.com/oauth2/v4/token";
		HttpsURLConnection httpClient = (HttpsURLConnection) new URL(tokeUrl).openConnection();
		String client_id = "732118873335-vcaolepnkn18gu13tlonpo6p1i5beotc.apps.googleusercontent.com";
		String redirect_uri = "http://localhost:8080/callback";
		String c_s = "5lT1G2FVFALWEUZRO60WdtUk";
		String scope = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile openid";
		
		String urlParameters = "code=" + code + "&redirect_uri=" + redirect_uri + "&client_id=" + client_id
				+ "&client_secret=" + c_s + "&scope=" + scope + "&grant_type=authorization_code";
		httpClient.setDoOutput(true); //to use Url connection for output
		
		try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) 
		{
			wr.writeBytes(urlParameters);
			wr.flush();
		}

		int responseCode = httpClient.getResponseCode(); //Ok
		System.out.println("\nSending 'POST' request to URL : " + tokeUrl);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		String res = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) 
		{

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) 
			{
				response.append(line);
			}

			// print result
			System.out.println("access token");
			System.out.println(response.toString());
			res = response.toString();
		} 
		finally
		{
			httpClient.disconnect();
		}
		System.out.println("check " + res);
		// to read data from a web server, and display the data in a web page
		JSONParser parser=new JSONParser(); 
		JSONObject newJObject = (JSONObject) parser.parse(res);
		String accesstoken = (String) newJObject.get("access_token");
		System.out.println("at");
		System.out.println(accesstoken);
		
		
		// http get request

				String proUrl = "https://www.googleapis.com/userinfo/v2/me";
				HttpsURLConnection httpClient1 = (HttpsURLConnection) new URL(proUrl).openConnection();
				httpClient1.setRequestMethod("GET");
				httpClient1.setRequestProperty("Authorization", "Bearer " + accesstoken + "");
				// String urlParameter = "Bearer=" + accesstoken + "";
				int responseCode1 = httpClient1.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + proUrl);
				System.out.println("Response Code : " + responseCode1); //ok
				String res_profile = null;
				
				try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient1.getInputStream()))) 
				{

					String line1;
					StringBuilder response = new StringBuilder();

					while ((line1 = in.readLine()) != null) 
					{
						response.append(line1);
					}

					// print result
					System.out.println("profile");
					System.out.println(response.toString());
					res_profile = response.toString();
				}
				finally
				{
					httpClient1.disconnect();
				}

				JSONParser parser1 = new JSONParser();
				JSONObject newJObject1 = (JSONObject) parser1.parse(res_profile);
				String google_id = (String) newJObject1.get("id");
				String name = (String) newJObject1.get("name");
				String picture = (String) newJObject1.get("picture");
				String email = (String) newJObject1.get("email");
				System.out.println(google_id);
				System.out.println(email);
				System.out.println(name);

				boolean userFound;
				userFound = userService.findUserBygoogle(google_id, name);
		
				System.out.println(userFound);
		
				if(userFound == true)
				{
					List<User> usr = userService.getUserByName(name);
					String user_name=null;
					int user_id=0;
					String user_pass=null;
					User u = null;
					
					/*if(usr.size() > 0) 
					{
						 u = usr.get(0);
							user_name = u.getFirstname();
							user_id = u.getId();
							user_pass=u.getPassword();
							System.out.println(user_name);
							System.out.println(user_id);
							System.out.println(user_pass);
					}
					*/
					u = usr.get(0);
					user_name = u.getFirstname();
					user_id = u.getId();
					user_pass=u.getPassword();
					System.out.println(user_name);
					System.out.println(user_id);
					System.out.println(user_pass);
					
					session=req.getSession();
					session.setAttribute("user", u);
					
					return ("redirect:/user/profile");
				}
				else
				{
					// session.invalidate();
					// generating random password
					/*List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 2),
					new CharacterRule(EnglishCharacterData.LowerCase, 2),
					new CharacterRule(EnglishCharacterData.Digit, 2),
					new CharacterRule(EnglishCharacterData.Special, 2));

					PasswordGenerator generator = new PasswordGenerator();
					String password = generator.generatePassword(10, rules);*/

					/*System.out.println("the random generated password :" + password);*/
					model.addAttribute("google_id", google_id);
					model.addAttribute("firstname", name);
					model.addAttribute("email", email);
					/*model.addAttribute("password", password);*/
					ModelAndView page = new ModelAndView();
					page.setViewName("signup");
					return "/signup";
				}
				//return "signup";		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView showRegistration()
	{
		ModelAndView page = new ModelAndView();
		page.setViewName("signup");
		return page;
	}
	
	// saving user through goolge sign in
	@RequestMapping(value = "/user/Form", method = RequestMethod.POST)
	public ModelAndView saveCompany(@ModelAttribute("admin") Admin admin,@RequestParam("google_id") String google_id,@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("password") String password,@RequestParam("gender") String gender,
			@RequestParam("doj") String doj,
			@RequestParam(name="profile",required = false) MultipartFile file) throws IOException
	{
		User user = new User();
		
		if (file != null && !file.isEmpty()) 
		{
			byte[] bFile = new byte[(int) file.getSize()];
			bFile = file.getBytes();
			user.setProfile(bFile);
		}
		
		String pass=encryptThisString(password);
		
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(pass);
		user.setGender(gender);
		user.setDoj(doj);
		user.setGoogle_id(google_id);
		
		/*SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Confirmation Mail");
		msg.setText("Dear " + firstname + " you are successfully registered  \n your passowrd=" + pass
				+ " \n please change your password after first login");
		javaMailSender.send(msg);*/
		
		userService.saveUser(user);
		ModelAndView page = new ModelAndView();
		page.setViewName("Login");
		
		return page;
		
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
			e.printStackTrace();
		}
		return input;
	}
}
