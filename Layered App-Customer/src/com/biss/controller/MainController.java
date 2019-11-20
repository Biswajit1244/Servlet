package com.biss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biss.dto.CustomerDTO;
import com.biss.service.CustomerServiceImpl;
import com.biss.vo.CustomerVO;
@WebServlet("/orderurl")
public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CustomerServiceImpl service=null;
	@Override
	public void init() throws ServletException {
		service=new CustomerServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CustomerDTO dto=null;
		PrintWriter pw=null;
		CustomerVO vo=null;
		String result=null;
		//get stream obj
		pw=res.getWriter();
		res.setContentType("text/html");
		//Read form data and set to CustomerVo Obj
		vo=new CustomerVO();
		vo.setcName(req.getParameter("ename"));
		vo.setProd(req.getParameter("prod"));
		vo.setQuan(req.getParameter("equantity"));
		vo.setPrice(req.getParameter("eprice"));
		//convert vo Data to Dto
		dto=new CustomerDTO();
		dto.setcName(vo.getcName());
		dto.setProduct(vo.getProd());
		dto.setQuantity(Integer.parseInt(vo.getQuan()));
		dto.setPrice(Float.parseFloat(vo.getPrice()));
		//use Service
		try {
			result = service.generateBillAmount(dto);
			pw.println("<h2 style='color:red;text-align:center'>"+result+"</h2>");
		}catch(SQLException se) {
			pw.println("<h2 style='color:red;text-align:center'>Internal problem</h2>");
			se.printStackTrace();
		}
		catch (Exception e) {
			pw.println("<h2 style='color:red;text-align:center'>Internal problem</h2>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
