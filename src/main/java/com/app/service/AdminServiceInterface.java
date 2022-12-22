package com.app.service;

import java.util.List;
import com.app.pojos.Admin;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

public interface AdminServiceInterface
{
	String updateAdminProfile(Admin admin, String name, String email, String mobile);

	List<Consumer> fetchConsumer();

	List<ConnectionRequest> fetchConnectionRequest();

	void acceptRequest(String consumerNo, int rid);

	List<Complaints> fetchAllNewComplaints();

	List<Employee> fetchEmps();

	Complaints fetchCompByID(int rid);

	String registerEmp(Employee emp);

	String assignEmpToComp(int cid, int emp);

	int countOfActiveCustomer();

	List<Employee> fetchEmpsl();

}