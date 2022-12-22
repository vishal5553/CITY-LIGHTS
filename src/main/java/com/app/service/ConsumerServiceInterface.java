package com.app.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.app.pojos.Addresses;
import com.app.pojos.Bills;
import com.app.pojos.Complaints;
import com.app.pojos.ConnectionRequest;
import com.app.pojos.Consumer;
import com.app.pojos.Payment;

public interface ConsumerServiceInterface
{
	String updateProfile(Consumer con, String name, String email, String mobile, String dob, String city,
			String district, String state, String streetLine, String pincode, String country);

	String updateImage(Integer id, byte[] imageFile);

	String connectionRequest(Payment pay, Consumer con, Addresses add, String consumerNo, ConnectionRequest cr);

	void saveRequest(Consumer con, Addresses add, String consumerNo, ConnectionRequest cr);

	List<ConnectionRequest> fetchMyConnectionRequest(Consumer con);

	String saveCompaint(Consumer con, String type, byte[] image);

	List<Complaints> fetchMyCompaint(Consumer con);

	List<Bills> fetchBills(Consumer con);

	Bills fetchBillById(int bid);

	String payBill(Bills b, Payment p);

	int countOfActiveCustomer();
}