package com.app.controller;

import java.io.IOException;
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
import com.app.pojos.Admin;
import com.app.pojos.Employee;
import com.app.service.AdminServiceInterface;
import com.app.service.EmployeeServiceInterface;
import com.app.service.LoginServiceInterface;
import EmailUtil.java.PasswordSender;

@Controller
@RequestMapping("/Admin")
public class AdminController
{
	@Autowired
	private AdminServiceInterface adminService;
	@Autowired
	private LoginServiceInterface loginService;
	@Autowired
	private EmployeeServiceInterface empService;

	public AdminController()
	{
		System.out.println("in constr of " + getClass().getName());
	}
	
	@GetMapping("/Dashboard")
	public String showAdminDashboard(HttpSession hs)
	{
		hs.setAttribute("cuscount", adminService.countOfActiveCustomer());
		hs.setAttribute("bill", empService.getTotalBillingAmount());
		hs.setAttribute("pbill", empService.getPAidBill());
		return "/Admin/Dashboard";
	}

	@GetMapping("/adminProfile")
	public String showAdmnProfile(HttpSession hs)
	{
		Admin ad = (Admin) hs.getAttribute("userDetails");
		hs.setAttribute("userDetails", loginService.verifyAdmin(ad.getEmail(), ad.getPassword()));
		return "/Admin/adminProfile";
	}

	@PostMapping("/updateProfile")
	public String updateAdminProfile(RedirectAttributes flashMap, @RequestParam String name, @RequestParam String email,
			@RequestParam String mobile, HttpSession hs)
	{
		Admin admin = (Admin) hs.getAttribute("userDetails");
		flashMap.addFlashAttribute("success", adminService.updateAdminProfile(admin, name, email, mobile));
		return "redirect:/Admin/adminProfile";
	}

	@GetMapping("/registredConsumer")
	public String showUsers(HttpSession hs)
	{
		hs.setAttribute("consumerList", adminService.fetchConsumer());
		return "/Admin/registredConsumer";
	}

	@GetMapping("/connectionRequest")
	public String showConnectionRequest(HttpSession hs)
	{
		hs.setAttribute("connectionRequest", adminService.fetchConnectionRequest());
		return "/Admin/connectionRequests";
	}

	@GetMapping("/accept_request")
	public String acceptRequest(@RequestParam int rid, HttpSession hs, Model modelMap, RedirectAttributes flashMap)
	{
		String consumerNo = ConsumerController.generateCI();
		adminService.acceptRequest(consumerNo,rid);
		
		return "redirect:/Admin/connectionRequest";
	}
	
	@GetMapping("/complaintsList")
	public String showComplaintList(HttpSession hs)
	{
		hs.setAttribute("complaintsList", adminService.fetchAllNewComplaints());
		return "/Admin/complaintsList";
	}
	
	@GetMapping("/assignEmployee")
	public String showAssignEmp(HttpSession hs,@RequestParam int rid)
	{
		hs.setAttribute("empList", adminService.fetchEmpsl());
		hs.setAttribute("comp",adminService.fetchCompByID(rid));
		return "/Admin/assignEmployee";
	}
	
	@GetMapping("/registredemployees")
	public String showRegistredEmployees(HttpSession hs)
	{
		hs.setAttribute("empList", adminService.fetchEmps());
		return "/Admin/registredemployees";
	}
	
	@GetMapping("/addEmp")
	public String showAddEmp()
	{
		return "/Admin/addEmp";
	}
	
	@PostMapping("/addEmp")
	public String processEmpRegister(@RequestParam String name, @RequestParam String mobile,
		@RequestParam MultipartFile image, @RequestParam String email, @RequestParam String dob,
		@RequestParam String district,@RequestParam String gender, @RequestParam String city, @RequestParam String streetLine,
		@RequestParam String state, @RequestParam String pinCode,@RequestParam String type,
		@RequestParam String country, Model modelMap, RedirectAttributes flashMap, HttpSession hs) throws IOException
	{
		String fpassword=mobile;
		byte[] imageFile=image.getBytes();
		Employee emp=new Employee(name, type, gender, email, mobile, fpassword, dob, imageFile, "Active");
		flashMap.addFlashAttribute("message", adminService.registerEmp(emp));
		PasswordSender ps=new PasswordSender();
		ps.sendEmail(email, email, fpassword);
		return "redirect:/Admin/registredemployees";
	}
	
	@PostMapping("/updateCompaint")
	public String assignEmpTocomplaint(@RequestParam int cid,@RequestParam int emp,RedirectAttributes flashMap)
	{
		flashMap.addFlashAttribute("message", adminService.assignEmpToComp(cid,emp));
		return "redirect:/Admin/complaintsList";
	}
}