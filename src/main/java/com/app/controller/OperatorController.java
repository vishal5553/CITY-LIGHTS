package com.app.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.app.service.AdminServiceInterface;
import com.app.service.EmployeeServiceInterface;

@Controller
@RequestMapping("/Operator")
public class OperatorController
{
	@Autowired
	private EmployeeServiceInterface empService;
	
	@Autowired
	private AdminServiceInterface adminService;
	public OperatorController()
	{		}
	
	@GetMapping("/Dashboard")
	public String showDashboard(HttpSession hs)
	{
		int count=empService.fetchNotifactionCount();
		if(count != 0)
		{
			hs.setAttribute("countNew",count);
		}
		hs.setAttribute("countpc",empService.getCountofPowerCut());
		hs.setAttribute("countp", empService.getPowerCutCount());
		hs.setAttribute("countbill", empService.getTotalBillingAmount());
		hs.setAttribute("countpbill", empService.getPAidBill());
		return "/Operator/Dashboard";
	}
	
	@GetMapping("/powerCutRequest")
	public String showPrequest(HttpSession hs)
	{
		hs.setAttribute("requests", empService.fetchAllRequest());
		return "/Operator/powerCutRequest";
	}
	
	@GetMapping("/togglelinecut")
	public String powerCut(@RequestParam String rid,RedirectAttributes flashMap)
	{
		flashMap.addFlashAttribute("message", empService.togglePCRequest(rid));
		return "redirect:/Operator/powerCutRequest";
	}
	
	@GetMapping("/Bills")
	public String showform(HttpSession hs)
	{
		hs.setAttribute("Consumers", adminService.fetchConsumer());
		return "/Operator/Bills";
	}
	
	@PostMapping("/billing")
	public String processBilling(@RequestParam int id,@RequestParam int unit,@RequestParam String date,
			@RequestParam String dueDate,RedirectAttributes flashMap)
	{
		flashMap.addFlashAttribute("message", empService.addBill(id,unit,date,dueDate));
		return "redirect:/Operator/Bills";
	}
}