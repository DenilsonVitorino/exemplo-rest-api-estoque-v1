package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAOFactory;
import dao.ProductDAO;
import model.Product;

//@WebServlet(name = "ProdutoController", urlPatterns = "/product")
public class ProdutoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Gson gson = new Gson();
	
	ProductDAO productDAO = DAOFactory.createProductDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(req.getParameter("id"));
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		resp.setStatus(200);	
		PrintWriter printWriter = resp.getWriter();		
		printWriter.print(gson.toJson(productDAO.getAll()));		
		printWriter.flush();		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setStatus(201);			
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = req.getReader();
		String atributos = null;
		while((atributos = bufferedReader.readLine()) != null) 
			stringBuffer.append(atributos);
		Product product = gson.fromJson(stringBuffer.toString(), Product.class);
		PrintWriter printWriter = resp.getWriter();		
		printWriter.print(gson.toJson(productDAO.saveProduct(product)));		
		printWriter.flush();
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setStatus(200);			
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = req.getReader();
		String atributos = null;
		while((atributos = bufferedReader.readLine()) != null) 
			stringBuffer.append(atributos);
		Product product = gson.fromJson(stringBuffer.toString(), Product.class);
		Product productResp = productDAO.updateProduct(product);
		PrintWriter printWriter = resp.getWriter();		
		if (productResp ==  null) {
			resp.setStatus(404);
			printWriter.print("Product was not found.");
		} else {
			printWriter.print(gson.toJson(productResp));	
		}				
		printWriter.flush();
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setStatus(200);			
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = req.getReader();
		String atributos = null;
		while((atributos = bufferedReader.readLine()) != null) 
			stringBuffer.append(atributos);
		Product product = gson.fromJson(stringBuffer.toString(), Product.class);
		String productResp = productDAO.deleteProduct(product.getId());
		PrintWriter printWriter = resp.getWriter();		
		if (productResp ==  null) {
			resp.setStatus(404);
			printWriter.print("Product was not found.");
		} else {
			printWriter.print(gson.toJson(productResp));	
		}				
		printWriter.flush();
	}

}
