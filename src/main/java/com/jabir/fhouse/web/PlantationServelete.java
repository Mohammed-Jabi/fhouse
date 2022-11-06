package com.jabir.fhouse.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jabir.fhouse.dao.PlantationDoa;
import com.jabir.fhouse.module.Plantation;

@WebServlet("/")
public class PlantationServelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlantationDoa plantationDao;

	public PlantationServelete() {
		this.plantationDao = new PlantationDoa();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
			
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		switch (action) {
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			try {
				insertPlantation(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deletePlantation(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updatePlantation(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			 try {
				listPlantation(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}
	
	private void listPlantation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Plantation> listPlantation = plantationDao.selectAllPlantation();
		request.setAttribute("listPlantation", listPlantation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("plantation-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("plantation-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Plantation existingPlantation = plantationDao.selectPlantation(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("plantation-form.jsp");
		request.setAttribute("plantation", existingPlantation);
		dispatcher.forward(request, response);

	}

	private void insertPlantation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String plantation = request.getParameter("plantation");
		String area =       request.getParameter("area");
		String pdate=       request.getParameter("pdate");
		String ydate =      request.getParameter("ydate");
		Plantation newPlantation = new Plantation(plantation,area,pdate,ydate);
		plantationDao.insertPlantation(newPlantation);
		response.sendRedirect("list");
	}
	
	private void updatePlantation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String plantation = request.getParameter("plantation");
		String area = request.getParameter("area");
		String pdate = request.getParameter("pdate");
		String ydate = request.getParameter("ydate");
		
		Plantation book = new Plantation(id,plantation,area,pdate,ydate);
		plantationDao.updatePlantation(book);
		response.sendRedirect("list");
	}
	
	private void deletePlantation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		plantationDao.deletePlantation(id);
		response.sendRedirect("list");

	}
	
}
