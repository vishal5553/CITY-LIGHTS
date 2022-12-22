package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "admin_tbl")
public class Admin extends BaseEntity
{
	@Column(name = "name", length = 40)
	private String name;
	@Column(name = "email", length = 30)
	private String email;
	@Column(name = "password", length = 30)
	private String password;
	@Column(name = "mobile", length = 15)
	private String mobile;
	@Column(name = "status", length = 10)
	private String status;

	public Admin()
	{
		System.out.println("Admin Table is Getting Ready......");
	}

	public Admin(String name, String email, String password, String mobile, String status)
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.status = status;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "Admin [name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile
				+ ", status=" + status + "]";
	}
}