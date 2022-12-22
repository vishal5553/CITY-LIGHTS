package com.app.dao;

import java.util.*;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.pojos.Addresses;
import com.app.pojos.Admin;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

@Repository
public class LoginDao implements LoginDaoInterface
{
	@Autowired
	private EntityManager mgr;

	@Override
	public String registerConsumer(Addresses add, Consumer consumer)
	{
		Consumer con=consumer;
		con.setAddress(add);
		con.setConsumerNo(null);
		mgr.persist(con);
		return "Your Registration is successfull please login";
	}

	@Override
	public Admin checkAdminAvailablity()
	{
		try
		{
			String jpql="Select a from Admin a";
			Admin admin=mgr.createQuery(jpql, Admin.class).getSingleResult();
			if(admin!=null)
				return admin;
			return null;
		} 
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public void registerAdmin(Admin admin1)
	{
		mgr.persist(admin1);
	}

	@Override
	public Consumer verifyConsumer(String email, String password)
	{
		try
		{
			String jpql="select c from Consumer c where c.email=:email and c.password=:password";
			Consumer obj= mgr.createQuery(jpql, Consumer.class).setParameter("email", email).setParameter("password", password).getSingleResult();
			if(obj != null)
				return obj;
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public Employee verifyEmployee(String email, String password)
	{
		try
		{
			String jpql="select c from Employee c where c.email=:email and c.password=:password";
			Employee obj= mgr.createQuery(jpql, Employee.class).setParameter("email", email).setParameter("password", password).getSingleResult();
			if(obj != null)
				return obj;
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public Admin verifyAdmin(String email, String password)
	{
		try
		{
			String jpql="select c from Admin c where c.email=:email and c.password=:password";
			Admin obj= mgr.createQuery(jpql, Admin.class).setParameter("email", email).setParameter("password", password).getSingleResult();
			if(obj != null)
				return obj;
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public String fetchPassword(String email)
	{
		String objpass = "";
		try
		{
			String jpql="select c from Consumer c where c.email=:email";
			Consumer obj= mgr.createQuery(jpql, Consumer.class).setParameter("email", email).getSingleResult();
			if(obj != null)
			{	
				objpass = obj.getPassword();
				return objpass;
			}
				return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}