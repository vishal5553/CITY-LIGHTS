package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="power_cut_request")
public class PowerCutRequest extends BaseEntity
{
	@OneToOne()
	@JoinColumn(name = "complaint_id")
	private Complaints complaint;
	
	private String date;
	
	private String description;
	
	private String stime;
	
	private String etime;
	
	private String status;

	public PowerCutRequest()
	{
		super();
	}

	public PowerCutRequest(Complaints complaint,String description, String date,
			String stime, String etime, String status)
	{
		super();
		this.complaint = complaint;
		this.description=description;
		this.date = date;
		this.stime = stime;
		this.etime = etime;
		this.status = status;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Complaints getComplaint()
	{
		return complaint;
	}

	public void setComplaint(Complaints complaint)
	{
		this.complaint = complaint;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getStime()
	{
		return stime;
	}

	public void setStime(String stime)
	{
		this.stime = stime;
	}

	public String getEtime()
	{
		return etime;
	}

	public void setEtime(String etime)
	{
		this.etime = etime;
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
		return "PowerCutRequest [complaint=" + complaint + ", date=" + date + ", stime=" + stime + ", etime=" + etime
				+ ", status=" + status + "]";
	}
}