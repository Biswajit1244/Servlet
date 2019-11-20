package com.biss.service;

import com.biss.DAO.CustomerShopDAOImpl;
import com.biss.bo.CustomerBO;
import com.biss.dto.CustomerDTO;

public class CustomerServiceImpl implements CustomerService {
	CustomerShopDAOImpl dao=null;
	public CustomerServiceImpl() {
		dao=new CustomerShopDAOImpl();
	}
	@Override
	public String generateBillAmount(CustomerDTO dto) throws Exception {
		float totalPrice=0.0f;
		float discount=0.0f;
		float billAmnt=0.0f;
		CustomerBO bo=null;
		int count=0;
		totalPrice=dto.getPrice()*dto.getQuantity();
		//Perform bussiness method
		if(totalPrice>=20000 && totalPrice<40000) {
			discount=(totalPrice/100)*5.0f;
			billAmnt=totalPrice-discount;
		}
		else if(totalPrice>=40000) {
			discount=(totalPrice/100)*10.0f;
			billAmnt=totalPrice-discount;
		}
		else {
			billAmnt=totalPrice;
		}
		//Convert DTO obj to BO obj
		bo=new CustomerBO();
		bo.setcName(dto.getcName());
		bo.setProduct(dto.getProduct());
		bo.setQuantity(dto.getQuantity());
		bo.setTotalAmt(totalPrice);
		bo.setDiscount(discount);
		bo.setBillAmt(billAmnt);
		//use DAO class
		count=dao.insertOrderDetails(bo);
		if(count==0)
			return "order placed failed";
		else
			return "Order Placed Successfully..<br>Name:-"+dto.getcName()+"<br>Product:-"+dto.getProduct()+
					"<br>Total Price:-"+totalPrice+"<br>Discount:-"+discount+"<br>-----------------------------------<br>"+
					"Bill Amount="+billAmnt;
	}

}
