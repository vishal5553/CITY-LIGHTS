package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity
{
	private String cardNo;
	private String cardName;
	private double amount;
	private String expDate;
	private String cvv;
	
	public Payment()
	{
		super();
	}

	public String getExpDate()
	{
		return expDate;
	}

	public void setExpDate(String expDate)
	{
		this.expDate = expDate;
	}

	public String getCvv()
	{
		return cvv;
	}

	public void setCvv(String cvv)
	{
		this.cvv = cvv;
	}

	public Payment(String cardNo, String cardName, double amount, String expDate, String cvv)
	{
		super();
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.amount = amount;
		this.expDate = expDate;
		this.cvv = cvv;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getCardName()
	{
		return cardName;
	}

	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	@Override
	public String toString()
	{
		return "Payment [cardNo=" + cardNo + ", cardName=" + cardName + ", amount=" + amount + ", expDate=" + expDate
				+ ", cvv=" + cvv + "]";
	}
}