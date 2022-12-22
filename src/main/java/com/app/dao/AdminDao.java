package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.pojos.Admin;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

@Repository
public class AdminDao implements AdminDaoInterface
{
	@Autowired
	private EntityManager mgr;

	@Override
	public String updateAdminProfile(Admin admin, String name, String email, String mobile)
	{
		Admin ad = mgr.find(Admin.class, admin.getId());
		ad.setEmail(email);
		ad.setName(name);
		ad.setMobile(mobile);
		return "Admin Profile Updated";
	}

	@Override
	public List<Consumer> fetchConsumer()
	{
		String jpql = "Select c from Consumer c";
		return mgr.createQuery(jpql, Consumer.class).getResultList();
	}

	@Override
	public List<ConnectionRequest> fetchConnectionRequest()
	{
		String jpql = "select c from ConnectionRequest c where c.status=:status";
		return mgr.createQuery(jpql, ConnectionRequest.class).setParameter("status", "new").getResultList();
	}

	@Override
	public void acceptRequest(String consumerNo, int rid)
	{
		ConnectionRequest cr = mgr.find(ConnectionRequest.class, rid);
		cr.setStatus("Active Customer");
		Consumer con = mgr.find(Consumer.class, cr.getConsumer().getId());
		con.setConsumerNo(consumerNo);
		con.setStatus("Active");
	}

	@Override
	public List<Complaints> fetchAllNewComplaints()
	{
		try
		{
			String jpql = "select c from Complaints c";
			List<Complaints> list = mgr.createQuery(jpql, Complaints.class).getResultList();
			/*
			 * List<Complaints> l=new ArrayList<>(); for(Complaints c:list) {
			 * if(c.getStatus().equals("Resolved")) { l.add(c); }else { continue; } }
			 * list.removeAll(l);
			 */
			if (list != null)
				return list;
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Employee> fetchEmps()
	{
		String jpql = "select e from Employee e";
		return mgr.createQuery(jpql, Employee.class).getResultList();
	}

	@Override
	public Complaints fetchCompByID(int rid)
	{
		return mgr.find(Complaints.class, rid);
	}

	@Override
	public String registerEmp(Employee emp)
	{
		mgr.persist(emp);
		return "Employee Registration done Login Credentials Send to user";
	}

	@Override
	public String assignEmpToComp(int cid, int emp)
	{
		Employee empl = mgr.find(Employee.class, emp);
		Complaints c = mgr.find(Complaints.class, cid);
		if(!c.getStatus().equals("Resolved"))
		{
			c.setEmployee(empl);
			c.setStatus("Line Men Assigned");
			return "Employee assigned to Complaint id " + c.getId();
		}
		else
		{
			return "Complaint already resolved";
		}
	}

	@Override
	public int countOfActiveCustomer()
	{
		String jpql = "Select c from Consumer c where c.status=:status";
		List<Consumer> list = mgr.createQuery(jpql, Consumer.class).setParameter("status", "Active").getResultList();
		int count = 0;
		for (Consumer c : list)
		{
			count = count + 1;
		}
		return count;
	}

	@Override
	public List<Employee> fetchEmpsl()
	{
		String jpql = "select e from Employee e where e.type=:type";
		return mgr.createQuery(jpql, Employee.class).setParameter("type", "Line Men").getResultList();
	}
}