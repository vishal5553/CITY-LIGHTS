package com.app.pojos;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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
@Table(name = "complaint_box")
public class Complaints extends BaseEntity
{
	@OneToOne()
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
	private String type;
	
	private String status;
	
	@OneToOne()
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@Transient
	private String imgUtility;
	
	@Lob
	@Column(name = "meter_image", length = 10000000)
	private byte[] image;
	
	public Complaints()
	{
		super();
	}
	
	public Consumer getConsumer()
	{
		return consumer;
	}
	
	public void setConsumer(Consumer consumer)
	{
		this.consumer = consumer;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public Employee getEmployee()
	{
		return employee;
	}
	
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
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
	
	@Override
	public String toString()
	{
		return "Complaints [consumer=" + consumer + ", type=" + type + ", status=" + status + ", employee=" + employee
				+ ", imgUtility=" + imgUtility + ", image=" + Arrays.toString(image) + "]";
	}
}