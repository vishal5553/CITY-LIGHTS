package com.app.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.app.pojos.Employee;
import com.app.service.EmployeeServiceInterface;

@Controller
@RequestMapping("/Employee")
public class EmployeeController
{
	@Autowired
	private EmployeeServiceInterface empService;
	public EmployeeController()
	{
		System.out.println("in constr of "+getClass().getName());
	}
	
	@GetMapping("/Dashboard")
	public String showDashBoard(HttpSession hs)
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
		return "/Employee/Dashboard";
	}
	
	@GetMapping("/complaints")
	public String showComplaints(HttpSession hs)
	{
		Employee emp=(Employee) hs.getAttribute("userDetails");
		hs.setAttribute("complaints", empService.fetchMyComplaints(emp));
		return "/Employee/complaints";
	}
	
	@GetMapping("/toggleComplaint")
	public String toggleComplaint(HttpSession hs, @RequestParam int rid, RedirectAttributes flashMap)
	{
		flashMap.addFlashAttribute("message", empService.toggleComplaintStatus(rid));
		return "redirect:/Employee/complaints";
	}
	
	@GetMapping("/lineCutForm")
	public String showLineCutForm(HttpSession hs)
	{
		Employee emp=(Employee) hs.getAttribute("userDetails");
		hs.setAttribute("complaints", empService.fetchMyComplaints(emp));
		return "/Employee/lineCutForm";
	}
	@PostMapping("/lineCutForm")
	public String processPowerCut(RedirectAttributes flashMap,@RequestParam int complaintId,
			@RequestParam String description,@RequestParam String date,@RequestParam String stime,@RequestParam String etime)
	{
		flashMap.addFlashAttribute("message",empService.addlinnecutRequest
				(complaintId,description,date,stime,etime));
		return "redirect:/Employee/linecutStatus";
	}
	
	@GetMapping("/linecutStatus")
	public String request(HttpSession hs)
	{
		Employee emp=(Employee) hs.getAttribute("userDetails");
		hs.setAttribute("requests", empService.fetchPrequest(emp));
		return "/Employee/linecutStatus";
	}
	
	@GetMapping("/togglelinecut")
	public String togglePRequest(@RequestParam int rid,HttpSession hs,RedirectAttributes flashMap)
	{
		flashMap.addFlashAttribute("message", empService.togglepstatus(rid));
		return "redirect:/Employee/linecutStatus";
	}
}