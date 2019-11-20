package com.biss.service;

import com.biss.dto.CustomerDTO;

public interface CustomerService {
	public String generateBillAmount(CustomerDTO dto)throws Exception;
}
