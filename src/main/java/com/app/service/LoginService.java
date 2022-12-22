package com.app.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.LoginDaoInterface;
import com.app.pojos.Addresses;
import com.app.pojos.Admin;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

@Service
@Transactional
public class LoginService implements LoginServiceInterface
{
	@Autowired
	private LoginDaoInterface loginDao;

	@Override
	public String registerConsumer(Addresses add, Consumer consumer)
	{
		return loginDao.registerConsumer(add, consumer);
	}

	@Override
	public Admin checkAdminAvailablity()
	{
		return loginDao.checkAdminAvailablity();
	}

	@Override
	public void registerAdmin(Admin admin1)
	{
		loginDao.registerAdmin(admin1);
	}

	@Override
	public Consumer verifyConsumer(String email, String password)
	{
		return loginDao.verifyConsumer(email, password);
	}

	@Override
	public Employee verifyEmployee(String email, String password)
	{
		return loginDao.verifyEmployee(email, password);
	}

	@Override
	public Admin verifyAdmin(String email, String password)
	{
		return loginDao.verifyAdmin(email, password);
	}

	@Override
	public String fetchPassword(String email)
	{
		return loginDao.fetchPassword(email);
	}
}