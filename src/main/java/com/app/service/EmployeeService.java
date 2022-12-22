package com.app.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.EmployeeDaoInterface;
import com.app.pojos.Complaints;
import com.app.pojos.Employee;
import com.app.pojos.PowerCutRequest;

@Service
@Transactional
public class EmployeeService implements EmployeeServiceInterface
{
	@Autowired
	private EmployeeDaoInterface empDao;
	
	@Override
	public List<Complaints> fetchMyComplaints(Employee emp)
	{
		return empDao.fetchMyComplaints(emp);
	}

	@Override
	public String toggleComplaintStatus(int rid)
	{
		return empDao.toggleComplaintStatus(rid);
	}

	@Override
	public String addlinnecutRequest(int complaintId, String description, String date,
			String stime, String etime)
	{
		return empDao.addlinnecutRequest(complaintId, description, date, stime, etime);
	}

	@Override
	public List<PowerCutRequest> fetchPrequest(Employee emp)
	{
		return empDao.fetchPrequest(emp);
	}

	@Override
	public String togglepstatus(int rid)
	{
		return empDao.togglepstatus(rid);
	}

	@Override
	public List<PowerCutRequest> fetchAllRequest()
	{
		return empDao.fetchAllRequest();
	}

	@Override
	public String togglePCRequest(String rid)
	{
		return empDao.togglePCRequest(rid);
	}

	@Override
	public String addBill(int id, int unit, String date, String dueDate)
	{
		return empDao.addBill(id, unit, date, dueDate);
	}

	@Override
	public int fetchNotifactionCount()
	{
		return empDao.fetchNotifactionCount();
	}

	@Override
	public int getCountofPowerCut()
	{
		return empDao.getCountofPowerCut();
	}

	@Override
	public int getPowerCutCount()
	{
		return empDao.getPowerCutCount();
	}

	@Override
	public int getTotalBillingAmount()
	{
		return empDao.getTotalBillingAmount();
	}

	@Override
	public int getPAidBill()
	{
		return empDao.getPAidBill();
	}
}