package com.app.pojos;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "consumer_tbl")
public class Consumer extends BaseEntity
{
	@Column(name = "consumer_no", length = 40)
	private String consumerNo;
	
	@Column(name = "name", length = 40)
	private String name;
	
	@Column(name = "email", length = 30)
	private String email;
	
	@Column(name = "password", length = 30)
	private String password;
	
	@Column(name = "gender", length = 10)
	private String gender;
	
	@Column(name = "mobile", length = 15)
	private String mobile;
	
	@Column(name = "date_of_birth", length = 15)
	private String dob;
	
	@Transient
	private String imgUtility;
	
	@Lob
	@Column(name = "profile_image", length = 10000000)
	private byte[] image;
	
	@Column(name = "status", length = 30)
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_address_id")
	private Addresses address;

	public Consumer()
	{
		System.out.println("Faculty Table is Getting Ready........");
	}

	public Consumer(String name, String email, String password, String gender, String mobile,
			String dob, byte[] image, String status)
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.mobile = mobile;
		this.dob = dob;
		this.image = image;
		this.status = status;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getImgUtility() throws UnsupportedEncodingException
	{
		byte[] encodeBase64 = Base64.encodeBase64(getImage());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
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

	public String getConsumerNo()
	{
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo)
	{
		this.consumerNo = consumerNo;
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
		return "Consumer [consumerNo=" + consumerNo + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobile=" + mobile + ", dob=" + dob + ", imgUtility=" + imgUtility + ", image="
				+ Arrays.toString(image) + ", status=" + status + ", address=" + address + "]";
	}
}