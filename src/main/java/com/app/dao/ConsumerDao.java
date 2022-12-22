package com.app.dao;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.app.pojos.Addresses;
import com.app.pojos.Bills;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Payment;

@Repository
public class ConsumerDao implements ConsumerDaoInterface
{
	@Autowired
	private EntityManager mgr;

	@Override
	public String updateProfile(Consumer con, String name, String email, String mobile, String dob, String city,
			String district, String state, String streetLine, String pincode, String country)
	{
		Consumer s = mgr.find(Consumer.class, con.getId());
		Addresses add = new Addresses(city, district, state, country, streetLine, Integer.parseInt(pincode));
		s.setName(name);
		s.setEmail(email);
		s.setMobile(mobile);
		s.setDob(dob);
		s.setAddress(add);
		return "Your Profile is updated";
	}

	@Override
	public String updateImage(Integer id, byte[] imageFile)
	{
		Consumer con=mgr.find(Consumer.class, id);
		con.setImage(imageFile);
		return "Profile Image Updated";
	}

	@Override
	public String connectionRequest(Payment pay, Consumer con, Addresses add, String consumerNo,
			ConnectionRequest cr)
	{
		mgr.persist(pay);
		return "Please Find Otp on your Email and process to payment Confirm";
	}

	@Override
	public void saveRequest(Consumer con, Addresses add, String consumerNo, ConnectionRequest cr)
	{
		Consumer con1=mgr.find(Consumer.class, con.getId());
	//	con1.setConsumerNo(consumerNo);
		con1.setAddress(add);
		ConnectionRequest cr1=cr;
		cr1.setConsumer(con1);
		mgr.persist(cr1);
	}

	@Override
	public List<ConnectionRequest> fetchMyConnectionRequest(Consumer con)
	{
		String jpql="select c from ConnectionRequest c where c.consumer=:con";
		return mgr.createQuery(jpql, ConnectionRequest.class).setParameter("con", con).getResultList();
	}

	@Override
	public String saveCompaint(Consumer con, String type, byte[] image)
	{
		Complaints c=new Complaints();
		c.setConsumer(con);
		c.setType(type);
		c.setStatus("New");
		c.setImage(image);
		mgr.persist(c);
		return "Compaint registred successfully";
	}

	@Override
	public List<Complaints> fetchMyCompaint(Consumer con)
	{
		String jpql="select c from Complaints c where c.consumer=:con";
		return mgr.createQuery(jpql, Complaints.class).setParameter("con", con).getResultList();
	}

	@Override
	public List<Bills> fetchBills(Consumer con)
	{
		return mgr.createQuery("select b from Bills b where b.consumer=:con", Bills.class).setParameter("con", con).getResultList();
	}

	@Override
	public String payBill(Bills b, Payment p)
	{
		mgr.persist(p);
		Bills b1=mgr.find(Bills.class, b.getId());
		b1.setStatus("Paid");
		return "Bill Paid SuccessFully";
	}

	@Override
	public Bills fetchBillById(int bid)
	{
		return mgr.find(Bills.class, bid);
	}

	@Override
	public int countOfActiveCustomer()
	{
		String jpql = "Select c from Consumer c where c.status=:status";
		List<Consumer> list = mgr.createQuery(jpql, Consumer.class).setParameter("status", "Active").getResultList();
		int count = 0;
		for (Consumer c : list)
		{
			count = count + 1;
		}
		return count;
	}
}