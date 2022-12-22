package com.app.pojos;

import java.io.UnsupportedEncodingException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Table(name = "employee_tbl")
public class Employee extends BaseEntity
{
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "gender", length = 10)
	private String gender;
	
	@Column(name = "email", length = 30)
	private String email;
	
	@Column(name = "mobile", length = 15)
	private String mobile;
	
	@Column(name = "password", length = 30)
	private String password;
	
	@Column(name = "date_of_birth", length = 15)
	private String dob;
	
	@Transient
	private String imgUtility;
	
	@Lob
	@Column(name = "profile_image", length = 10000000)
	private byte[] image;
	
	@Column(name = "status", length = 10)
	private String status;

	private String type;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_address_id")
	private Addresses address;

	public Employee()
	{
		System.out.println("Employee");
	}

	public Employee(String name, String type, String gender, String email, String mobile,
			String password, String dob, byte[] image, String status)
	{
		super();
		this.type = type;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.dob = dob;
		this.image = image;
		this.status = status;
	}

	public String getImgUtility() throws UnsupportedEncodingException
	{
		byte[] encodeBase64 = Base64.encodeBase64(getImage());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setImgUtility(String imgUtility)
	{
		this.imgUtility = imgUtility;
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDob()
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Addresses getAddress()
	{
		return address;
	}

	public void setAddress(Addresses address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		return "Employee [name=" + name + ", gender=" + gender + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + ", dob=" + dob + ", status=" + status + ", address=" + address + "]";
	}
}