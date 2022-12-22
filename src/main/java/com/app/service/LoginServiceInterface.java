package com.app.service;

import com.app.pojos.Addresses;
import com.app.pojos.Admin;
import com.app.pojos.Consumer;
import com.app.pojos.Employee;

public interface LoginServiceInterface
{
	String registerConsumer(Addresses add, Consumer consumer);

	Admin checkAdminAvailablity();

	void registerAdmin(Admin admin1);

	Consumer verifyConsumer(String email, String password);

	Employee verifyEmployee(String email, String password);

	Admin verifyAdmin(String email, String password);

	String fetchPassword(String email);

}