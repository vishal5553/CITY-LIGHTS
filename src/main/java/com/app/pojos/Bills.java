package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "Bill_history")
public class Bills extends BaseEntity
{
	@OneToOne()
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
	private String bill;
	
	private String status;

	private String date;
	
	private String dueDate;
	
	public Bills()
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

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}

	public String getBill()
	{
		return bill;
	}

	public void setBill(String bill)
	{
		this.bill = bill;
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
		return "Bills [consumer=" + consumer + ", bill=" + bill + ", status=" + status + ", date=" + date + ", dueDate="
				+ dueDate + "]";
	}
}