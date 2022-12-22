package com.app.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.app.dao.ConsumerDaoInterface;
import com.app.pojos.Addresses;
import com.app.pojos.Bills;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Payment;

@Service
@Transactional
public class ConsumerService implements ConsumerServiceInterface
{
	@Autowired
	private ConsumerDaoInterface consumerDao;

	@Override
	public String updateProfile(Consumer con, String name, String email, String mobile, String dob,
			String city, String district, String state, String streetLine, String pincode, String country)
	{
		return consumerDao.updateProfile(con, name, email, mobile, dob, city, district, state,
				streetLine, pincode,country);
	}

	@Override
	public String updateImage(Integer id, byte[] imageFile)
	{
		return consumerDao.updateImage(id, imageFile);
	}

	@Override
	public String connectionRequest(Payment pay, Consumer con, Addresses add, String consumerNo,
			ConnectionRequest cr)
	{
		return consumerDao.connectionRequest(pay, con, add, consumerNo, cr);
	}

	@Override
	public void saveRequest(Consumer con, Addresses add, String consumerNo, ConnectionRequest cr)
	{
		consumerDao.saveRequest(con, add, consumerNo, cr);
	}

	@Override
	public List<ConnectionRequest> fetchMyConnectionRequest(Consumer con)
	{
		return consumerDao.fetchMyConnectionRequest(con);
	}

	@Override
	public String saveCompaint(Consumer con, String type, byte[] image)
	{
		return consumerDao.saveCompaint(con, type, image);
	}

	@Override
	public List<Complaints> fetchMyCompaint(Consumer con)
	{
		return consumerDao.fetchMyCompaint(con);
	}

	@Override
	public List<Bills> fetchBills(Consumer con)
	{
		return consumerDao.fetchBills(con);
	}

	@Override
	public Bills fetchBillById(int bid)
	{
		return consumerDao.fetchBillById(bid);
	}

	@Override
	public String payBill(Bills b, Payment p)
	{
		return consumerDao.payBill(b, p);
	}

	@Override
	public int countOfActiveCustomer()
	{
		return consumerDao.countOfActiveCustomer();
	}
}