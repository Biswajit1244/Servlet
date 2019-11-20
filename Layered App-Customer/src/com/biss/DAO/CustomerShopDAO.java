package com.biss.DAO;

import java.sql.SQLException;
import com.biss.bo.CustomerBO;

public interface CustomerShopDAO {
	public int insertOrderDetails(CustomerBO bo)throws Exception;
}
