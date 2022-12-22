package com.app.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.AdminDaoInterface;
import com.app.pojos.Admin;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

@Service
@Transactional
public class AdminService implements AdminServiceInterface
{
	@Autowired
	private AdminDaoInterface adminDao;

	@Override
	public String updateAdminProfile(Admin admin, String name, String email, String mobile)
	{
		return adminDao.updateAdminProfile(admin, name, email, mobile);
	}

	@Override
	public List<Consumer> fetchConsumer()
	{
		return adminDao.fetchConsumer();
	}

	@Override
	public List<ConnectionRequest> fetchConnectionRequest()
	{
		return adminDao.fetchConnectionRequest();
	}

	@Override
	public void acceptRequest(String consumerNo, int rid)
	{
		adminDao.acceptRequest(consumerNo, rid);
	}

	@Override
	public List<Complaints> fetchAllNewComplaints()
	{
		return adminDao.fetchAllNewComplaints();
	}

	@Override
	public List<Employee> fetchEmps()
	{
		return adminDao.fetchEmps();
	}

	@Override
	public Complaints fetchCompByID(int rid)
	{
		return adminDao.fetchCompByID(rid);
	}

	@Override
	public String registerEmp(Employee emp)
	{
		return adminDao.registerEmp(emp);
	}

	@Override
	public String assignEmpToComp(int cid, int emp)
	{
		return adminDao.assignEmpToComp(cid, emp);
	}

	@Override
	public int countOfActiveCustomer()
	{
		return adminDao.countOfActiveCustomer();
	}

	@Override
	public List<Employee> fetchEmpsl()
	{
		return adminDao.fetchEmpsl();
	}
}