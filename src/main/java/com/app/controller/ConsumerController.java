package com.app.controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.app.pojos.Addresses;
import com.app.pojos.Bills;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Payment;
import com.app.service.ConsumerServiceInterface;
import com.app.service.LoginServiceInterface;
import EmailUtil.java.OTPSender;
import net.bytebuddy.asm.Advice.AssignReturned.ToAllArguments;

@Controller
@RequestMapping("/Consumer")
public class ConsumerController
{
	@Autowired
	private ConsumerServiceInterface consumerService;
	@Autowired
	private LoginServiceInterface loginService;

	public ConsumerController()
	{
		System.out.println("inn Constr of " + getClass().getName());
	}

	@GetMapping("/Dashboard")
	public String showConsumerDashboard(HttpSession hs)
	{
//		hs.setAttribute("cuscount", consumerService.countOfActiveCustomer());
//		hs.setAttribute("bill", empService.getTotalBillingAmount());
//		hs.setAttribute("pbill", empService.getPAidBill());
		return "/Consumer/Dashboard";
	}

	@GetMapping("/consumerProfile")
	public String ShowConsumerProfile(HttpSession hs)
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		hs.setAttribute("userDetails", loginService.verifyConsumer(con.getEmail(), con.getPassword()));
		return "/Consumer/consumerProfile";
	}

	@PostMapping("/updateProfile")
	public String updateStudentProfile(@RequestParam String name, @RequestParam String email,
			@RequestParam String mobile, @RequestParam String dob, @RequestParam String city,
			@RequestParam String district, @RequestParam String state, @RequestParam String streetLine,
			@RequestParam String pincode, @RequestParam String country, HttpSession hs, Model modelMap,
			RedirectAttributes flashMap)
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		modelMap.addAttribute("success", consumerService.updateProfile(con, name, email, mobile, dob, city, district,
				state, streetLine, pincode, country));
		return "redirect:/Consumer/consumerProfile";
	}

	@PostMapping("/updateImage")
	public String updateImage(@RequestParam MultipartFile image, Model modelMap, RedirectAttributes flashMap,
			HttpSession hs)
	{
		try
		{
			Consumer con = (Consumer) hs.getAttribute("userDetails");
			byte[] imageFile = image.getBytes();
			flashMap.addFlashAttribute("success", consumerService.updateImage(con.getId(), imageFile));
			return "redirect:/Consumer/consumerProfile";
		} 
		catch (Exception e) {
			modelMap.addAttribute("errors", "Internal Server Error Occoured");
			return "/Consumer/consumerProfile";
		}
	}

	@GetMapping("/newConnection")
	public String showAddNewConnection()
	{
		return "/Consumer/newConnection";
	}

	public static String generateCI()
	{
		String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random_method = new Random();
		char[] otpchar = new char[10];
		String otp = "";
		for (int i = 0; i < 10; i++)
		{
			otpchar[i] = digits.charAt(random_method.nextInt(digits.length()));
			otp = otp + otpchar[i];
		}
		return otp;
	}

	public static int generateOTP()
	{
		String digits = "0123456789";
		Random random_method = new Random();
		char[] otpchar = new char[10];
		String otp = "";
		for (int i = 0; i < 5; i++)
		{
			otpchar[i] = digits.charAt(random_method.nextInt(digits.length()));
			otp = otp + otpchar[i];
		}
		return Integer.parseInt(otp);
	}

	@PostMapping("/connectionRequest")
	public String requestConnection(HttpSession hs, @RequestParam String aadharNo, @RequestParam MultipartFile aadhar,
			@RequestParam String city, @RequestParam String district, @RequestParam String state,
			@RequestParam String streetLine, @RequestParam String pincode, @RequestParam String country)
			throws IOException
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		byte[] imageFile = aadhar.getBytes();
		Addresses add = new Addresses(city, district, state, country, streetLine, Integer.parseInt(pincode));
		String consumerNo = ConsumerController.generateCI();
		int otp = ConsumerController.generateOTP();
		ConnectionRequest cr = new ConnectionRequest(aadharNo, imageFile, "new");
		hs.setAttribute("address", add);
		hs.setAttribute("consumerNo", consumerNo);
		hs.setAttribute("otp", otp);
		hs.setAttribute("consumerRequeust", cr);
		return "/Consumer/payment";
	}

	@PostMapping("/payment")
	public String payment(@RequestParam String cardNo, @RequestParam String cardName, @RequestParam double amount,
			@RequestParam String expDate, @RequestParam String cvv, HttpSession hs, Model modelMap)
	{
		Payment pay = new Payment(cardNo, cardName, amount, expDate, cvv);
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		Addresses add = (Addresses) hs.getAttribute("address");
		String consumerNo = (String) hs.getAttribute("consumerNo");
		hs.setAttribute("pay", pay);
		int otp = (int) hs.getAttribute("otp");
		ConnectionRequest cr = (ConnectionRequest) hs.getAttribute("consumerRequeust");
		modelMap.addAttribute("message", consumerService.connectionRequest(pay, con, add, consumerNo, cr));
		OTPSender op = new OTPSender();
		System.out.print(otp);
		op.sendEmail(con.getEmail(), otp);
		return "/Consumer/confirmPayment";
	}

	@PostMapping("/cpayment")
	public String processRequest(@RequestParam int otp, HttpSession hs, Model modelMap)
	{
		int otp1 = (int) hs.getAttribute("otp");
		if (otp1 == otp)
		{
			Consumer con = (Consumer) hs.getAttribute("userDetails");
			Addresses add = (Addresses) hs.getAttribute("address");
			String consumerNo = (String) hs.getAttribute("consumerNo");
			ConnectionRequest cr = (ConnectionRequest) hs.getAttribute("consumerRequeust");
			consumerService.saveRequest(con, add, consumerNo, cr);
			return "redirect:/Consumer/myRequest";
		} 
		else
		{
			modelMap.addAttribute("message", "Invalid Otp");
			return "/Consumer/confirmPayment";
		}
	}
	
	@GetMapping("/payBill")
	public String processBill(@RequestParam int bid, HttpSession hs)
	{
		hs.setAttribute("bill", consumerService.fetchBillById(bid));
		return "/Consumer/payBill";
	}

	@PostMapping("/billpayment")
	public String processBillPay(HttpSession hs, @RequestParam String cardNo, @RequestParam String cardName,
			@RequestParam String expDate, @RequestParam String cvv, RedirectAttributes flashMap)
	{
		Bills b = (Bills) hs.getAttribute("bill");
		Payment p = new Payment(cardNo, cardName, Double.parseDouble(b.getBill()), expDate, cvv);
		flashMap.addFlashAttribute("message", consumerService.payBill(b, p));
		return "redirect:/Consumer/viewBills";
	}

	@GetMapping("/myRequest")
	public String showPendingRequest(HttpSession hs)
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		hs.setAttribute("myRequest", consumerService.fetchMyConnectionRequest(con));
		return "/Consumer/myRequest";
	}

	@GetMapping("/complaints")
	public String showComplaint(HttpSession hs, RedirectAttributes flashMap)
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		hs.setAttribute("complaintList", consumerService.fetchMyCompaint(con));
		return "/Consumer/complaints";
	}

	@PostMapping("/saveCompaint")
	public String saveComapaint(@RequestParam String type, @RequestParam MultipartFile image, HttpSession hs,
			Model modelMap, RedirectAttributes flashMap) throws IOException
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		byte[] imageFile = image.getBytes();
		flashMap.addFlashAttribute("message", consumerService.saveCompaint(con, type, imageFile));
		return "redirect:/Consumer/complaints";
	}

	@GetMapping("viewBills")
	public String showBills(HttpSession hs)
	{
		Consumer con = (Consumer) hs.getAttribute("userDetails");
		hs.setAttribute("bills", consumerService.fetchBills(con));
		return "/Consumer/viewBills";
	}

	@GetMapping("/consumptionCalculator")
	public String showCal()
	{
		return "/Consumer/consumptionCalculator";
	}

	@PostMapping("/calculate")
	public String processCal(@RequestParam int fan, @RequestParam int tv, @RequestParam int ref, @RequestParam int ac,
			@RequestParam int bulb, @RequestParam int day, RedirectAttributes flashMap)
	{
		double totalamount=0;
		double totalunit=0;
		int fanunit=fan*day*3;
		int tvunit=tv*day*3;
		int acunit=ac*day*5;
		int bulbunit=bulb*day*2;
		int refunit=ref*day*4;
		totalunit=fanunit+acunit+bulbunit+refunit+tvunit;
		totalamount=totalunit*10;
		flashMap.addFlashAttribute("message", "Your Total consumption for "+day+" dayes is Total Unit="+totalunit+" and the bill amount is "+totalamount);
		return "redirect:/Consumer/consumptionCalculator";
	}
}