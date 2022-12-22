package com.app.dao;

import java.util.List;
import com.app.pojos.Complaints;
import com.app.pojos.Employee;
import com.app.pojos.PowerCutRequest;

public interface EmployeeDaoInterface
{
	List<Complaints> fetchMyComplaints(Employee emp);

	String toggleComplaintStatus(int rid);

	String addlinnecutRequest(int complaintId, String description, String date,
			String stime, String etime);

	List<PowerCutRequest> fetchPrequest(Employee emp);

	String togglepstatus(int rid);

	List<PowerCutRequest> fetchAllRequest();

	String togglePCRequest(String rid);

	String addBill(int id, int unit, String date, String dueDate);

	int fetchNotifactionCount();

	int getCountofPowerCut();

	int getPowerCutCount();

	int getTotalBillingAmount();

	int getPAidBill();
}