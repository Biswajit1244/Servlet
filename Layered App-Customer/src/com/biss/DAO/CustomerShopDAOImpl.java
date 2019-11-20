package com.biss.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.biss.bo.CustomerBO;

public class CustomerShopDAOImpl implements CustomerShopDAO {
	private static final String ORDER_DETAILS="INSERT INTO CUSTOMER_SHOPPING VALUES(ORDER_ID.NEXTVAL,?,?,?,?,?,?)";
	public Connection getPooledConnection() throws Exception {
		InitialContext ctx=null;
		DataSource ds=null;
		Connection con=null;
		//Acces jndi registry
		ctx=new InitialContext();
		ds=(DataSource)ctx.lookup("java:/comp/env/DsJndi");
		con=ds.getConnection();
		return con;
	}

	@Override
	public int insertOrderDetails(CustomerBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get pooled connection obj
		con=getPooledConnection();
		//create preparestatement obj
		ps=con.prepareStatement(ORDER_DETAILS);
		//set query value
		ps.setString(1, bo.getcName());
		ps.setString(2, bo.getProduct());
		ps.setInt(3, bo.getQuantity());
		ps.setFloat(4, bo.getTotalAmt());
		ps.setFloat(5, bo.getDiscount());
		ps.setFloat(6, bo.getBillAmt());
		//execute the query
		count=ps.executeUpdate();
		
		return count;
	}

}
