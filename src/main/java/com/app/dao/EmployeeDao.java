package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.pojos.Bills;
import com.app.pojos.Complaints;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;
import com.app.pojos.PowerCutRequest;

@Repository
public class EmployeeDao implements EmployeeDaoInterface
{
	@Autowired
	private EntityManager mgr;

	@Override
	public List<Complaints> fetchMyComplaints(Employee emp)
	{
		String jpql = "select c from Complaints c where c.employee=:emp";
		return mgr.createQuery(jpql, Complaints.class).setParameter("emp", emp).getResultList();
	}

	@Override
	public String toggleComplaintStatus(int rid)
	{
		Complaints c = mgr.find(Complaints.class, rid);
		String status = c.getStatus();
		if (status.equals("Line Men Assigned"))
			c.setStatus("Work in Process");
		else if (status.equals("Work in Process"))
			c.setStatus("Resolved");
		return "Status changed";
	}

	@Override
	public String addlinnecutRequest(int complaintId, String description, String date, 
			String stime, String etime)
	{
		Complaints c = mgr.find(Complaints.class, complaintId);
		PowerCutRequest pc = new PowerCutRequest(c, description, date, stime, etime, "New");
		mgr.persist(pc);
		return "request Send SuccessFully wait for Operator Action";
	}

	@Override
	public List<PowerCutRequest> fetchPrequest(Employee emp)
	{
		String jpql = "select p from PowerCutRequest p";
		List<PowerCutRequest> list = mgr.createQuery(jpql, PowerCutRequest.class).getResultList();
		Employee e = mgr.find(Employee.class, emp.getId());
		List<PowerCutRequest> list1 = new ArrayList<>();
		for (PowerCutRequest p : list)
		{
			if (p.getComplaint().getEmployee().equals(e))
			{
				list1.add(p);
			}
			else
			{
				continue;
			}
		}
		if (list1 != null)
			return list1;
		return null;
	}

	@Override
	public String togglepstatus(int rid)
	{
		PowerCutRequest pr = mgr.find(PowerCutRequest.class, rid);
		if (pr.getStatus().equals("Approved & Power Cut"))
		{
			pr.setStatus("Resolved");
			return "Marked As Resolved";
		}
		else
		{
			return "Wait for Line Cut";
		}
	}

	@Override
	public List<PowerCutRequest> fetchAllRequest()
	{
		String jpql = "select p from PowerCutRequest p";
		List<PowerCutRequest> list = mgr.createQuery(jpql, PowerCutRequest.class).getResultList();
		if (list != null)
			return list;
		return null;
	}

	@Override
	public String togglePCRequest(String rid)
	{
		PowerCutRequest pc = mgr.find(PowerCutRequest.class, Integer.parseInt(rid));
		String status = pc.getStatus();
		if (status.equals("New"))
		{
			pc.setStatus("Approved & Power Cut");
			return "Status changed";
		}
		else if (status.equals("Resolved"))
		{
			pc.setStatus("Power On");
			return "Status changed";
		}
		else
		{
			return "Wait FOr Line Men Response";
		}
	}

	@Override
	public String addBill(int id, int unit, String date, String dueDate)
	{
		Consumer con = mgr.find(Consumer.class, id);
		Bills b = new Bills();
		b.setConsumer(con);
		b.setBill("" + unit * 10);
		b.setDate(date);
		b.setDueDate(dueDate);
		b.setStatus("Not Paid");
		mgr.persist(b);
		return "Billing Entry Done for customer " + con.getConsumerNo();
	}

	@Override
	public int fetchNotifactionCount()
	{
		String jpql = "select p from PowerCutRequest p where p.status=:status";
		List<PowerCutRequest> list = mgr.createQuery(jpql, PowerCutRequest.class)
				.setParameter("status", "New").getResultList();
		int count = 0;
		for (PowerCutRequest p : list)
		{
			count = count + 1;
		}
		return count;
	}

	@Override
	public int getCountofPowerCut()
	{
		String jpql = "select p from PowerCutRequest p where p.status=:status";
		List<PowerCutRequest> list = mgr.createQuery(jpql, PowerCutRequest.class)
				.setParameter("status", "New").getResultList();
		int count = 0;
		for (PowerCutRequest p : list)
		{
			count = count + 1;
		}
		return count;
	}

	@Override
	public int getPowerCutCount()
	{
		String jpql = "select p from PowerCutRequest p where p.status=:status";
		List<PowerCutRequest> list = mgr.createQuery(jpql, PowerCutRequest.class)
				.setParameter("status", "Approved & Power Cut").getResultList();
		int count = 0;
		for (PowerCutRequest p : list)
		{
			count = count + 1;
		}
		return count;
	}

	@Override
	public int getTotalBillingAmount()
	{
		String jpql = "select b from Bills b";
		List<Bills> list = mgr.createQuery(jpql, Bills.class).getResultList();
		int count = 0;
		for (Bills b : list)
		{
			count = count + Integer.parseInt(b.getBill());
		}
		return count;
	}

	@Override
	public int getPAidBill()
	{
		String jpql = "select b from Bills b";
		List<Bills> list = mgr.createQuery(jpql, Bills.class).getResultList();
		int count = 0;
		for (Bills b : list)
		{
			if (b.getStatus().equals("Paid"))
			{
				count = count + Integer.parseInt(b.getBill());
			}
			else
			{
				continue;
			}
		}
		return count;
	}
}