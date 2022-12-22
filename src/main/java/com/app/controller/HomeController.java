package com.app.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.app.pojos.Addresses;
import com.app.pojos.Admin;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;
import com.app.service.LoginServiceInterface;
import EmailUtil.java.PasswordSender;

@Controller
@RequestMapping("/")
public class HomeController
{
	@Autowired
	private LoginServiceInterface loginService;

	public HomeController()
	{
		System.out.println("in controller of " + getClass().getName());
	}

	@GetMapping("/")
	public String homepageProvicer()
	{
		Admin admin = loginService.checkAdminAvailablity();
		if (admin != null)
		{		}
		else
		{
			Admin admin1 = new Admin("Super User", "admin@gmail.com", "Admin#9860", "8767855143", "Active");
			loginService.registerAdmin(admin1);
		}
		return "/Index";
	}

	@GetMapping("/registration")
	public String regPageProvider()
	{
		return "/Registration";
	}

	@PostMapping("/signup")
	public String processRegistration(@RequestParam String name, @RequestParam String mobile,
			@RequestParam MultipartFile image, @RequestParam String email, @RequestParam String dob,
			@RequestParam String district, @RequestParam String gender, @RequestParam String city,
			@RequestParam String streetLine, @RequestParam String state, @RequestParam String pinCode,
			@RequestParam String country, @RequestParam String password, @RequestParam String cpassword, Model modelMap,
			RedirectAttributes flashMap, HttpSession hs)
	{
		if(password.equals(cpassword))
		{
			try 
			{
				byte[] imageFile = image.getBytes();
				Consumer consumer = new Consumer(name, email, cpassword, gender, mobile, dob, imageFile, "new");
				Addresses add = new Addresses(city, district, state, country, streetLine, Integer.parseInt(pinCode));
				flashMap.addFlashAttribute("message", loginService.registerConsumer(add, consumer));
				return "redirect:/";
			}
			catch (Exception e) 
			{
				return "/Registration";
			}
		}
		else
		{
			modelMap.addAttribute("passworderror", "Password and confirmed password is not matched");
			return "/Registration";
		}
	}

	@PostMapping("/userLogin")
	public String processLogin(@RequestParam String email, @RequestParam String password, Model modelMap,
			RedirectAttributes flashMap, HttpSession hs)
	{
		try
		{
			Consumer con = loginService.verifyConsumer(email, password);
			Employee emp = loginService.verifyEmployee(email, password);
			Admin admin = loginService.verifyAdmin(email, password);
			if (con != null)
			{
				hs.setAttribute("userDetails", con);
				return "redirect:/Consumer/Dashboard";
			} 
			else if (emp != null)
			{
				if (emp.getType().equals("Operator"))
				{
					hs.setAttribute("userDetails", emp);
					return "redirect:/Operator/Dashboard";
				} 
				else
				{
					hs.setAttribute("userDetails", emp);
					return "redirect:/Employee/Dashboard";
				}
			}
			else if (admin != null)
			{
				hs.setAttribute("userDetails", admin);
				return "redirect:/Admin/Dashboard";
			}
			else
			{
				flashMap.addFlashAttribute("error", "Invalid Credentials");
				return "redirect:/";
			}
		}
		catch (Exception e)
		{
			flashMap.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String Logout(HttpSession hs)
	{
		hs.getAttribute("userDetails");
		hs.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/forgetpassword")
	public String showForgetpasswordPage()
	{
		return "/Forgetpassword";
	}
	
	@PostMapping("/getpassword")
	public String getPassword(@RequestParam String email, HttpSession hs, Model modelMap,RedirectAttributes flashMap) 
	{
		String psw= loginService.fetchPassword(email);
		//String decryptPassword = AESUtils.decrypt(psw, secretKey);
		//System.out.println(decryptPassword);
		PasswordSender passwordsender = new PasswordSender();
		//otpSender.sendpassEmail(email, decryptPassword);
		passwordsender.sendEmail(email, email, psw);
		return "redirect:/";
	}
}