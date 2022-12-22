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
@Table(name = "connectionRequest")
public class ConnectionRequest extends BaseEntity
{
	private String aadharNo;
	
	@Transient
	private String imgUtility;
	
	@Lob
	@Column(name = "aadhar_card", length = 10000000)
	private byte[] aadhar;
	
	@Column(name = "status", length = 30)
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
	public ConnectionRequest()
	{
		super();
	}

	public ConnectionRequest(String aadharNo, byte[] aadhar, String status)
	{
		super();
		this.aadharNo = aadharNo;
		this.aadhar = aadhar;
		this.status = status;
	}

	public String getAadharNo()
	{
		return aadharNo;
	}

	public void setAadharNo(String aadharNo)
	{
		this.aadharNo = aadharNo;
	}

	public String getImgUtility() throws UnsupportedEncodingException
	{
		byte[] encodeBase64 = Base64.encodeBase64(getAadhar());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
	}
	
	public void setImgUtility(String imgUtility)
	{
		this.imgUtility = imgUtility;
	}

	public byte[] getAadhar()
	{
		return aadhar;
	}

	public void setAadhar(byte[] aadhar)
	{
		this.aadhar = aadhar;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Consumer getConsumer()
	{
		return consumer;
	}

	public void setConsumer(Consumer consumer)
	{
		this.consumer = consumer;
	}
	
	@Override
	public String toString()
	{
		return "ConnectionRequest [aadharNo=" + aadharNo + ", imgUtility=" + imgUtility + ", aadhar="
				+ Arrays.toString(aadhar) + ", status=" + status + ", consumer=" + consumer + "]";
	}	
}