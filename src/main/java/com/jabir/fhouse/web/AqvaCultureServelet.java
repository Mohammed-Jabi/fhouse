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

import com.jabir.fhouse.dao.AqvaCulturDao;
import com.jabir.fhouse.dao.PlantationDoa;
import com.jabir.fhouse.module.AqvaCulture;
import com.jabir.fhouse.module.Plantation;

/**
 * Servlet implementation class AqvaCultureServelet
 */
@WebServlet("/")
public class AqvaCultureServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AqvaCulturDao aqvacultureDao;
	
	public AqvaCultureServelet() {
		this.aqvacultureDao = new AqvaCulturDao();

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
				insertAqvaCulture(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteAqvaCulture(request,response);
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
				updateAqvaCulture(request,response);
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
				listAqvaCulture(request,response);
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
	
	private void listAqvaCulture(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<AqvaCulture> listAqvaCulture = aqvacultureDao.selectAllAqvaCulture();
		request.setAttribute("listAqvaCulture", listAqvaCulture);
		RequestDispatcher dispatcher = request.getRequestDispatcher("plantation-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("aqvaculture-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		AqvaCulture existingAqvaCulture = aqvacultureDao.selectAqvaCulture(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("aqvaculture-form.jsp");
		request.setAttribute("aqvaCulture", existingAqvaCulture);
		dispatcher.forward(request, response);

	}

	private void insertAqvaCulture(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String species = request.getParameter("species");
		String area =       request.getParameter("area");
		String startdate=       request.getParameter("startdate");
		String eydate =      request.getParameter("eydate");
		AqvaCulture newAqvaCulture = new AqvaCulture(species,area,startdate,eydate);
		aqvacultureDao.insertAqvaCulture(newAqvaCulture);
		response.sendRedirect("list");
	}
	
	private void updateAqvaCulture(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String species = request.getParameter("species");
		String area = request.getParameter("area");
		String startdate = request.getParameter("startdate");
		String eydate = request.getParameter("eydate");
		
		AqvaCulture book = new AqvaCulture(id,species,area,startdate,eydate);
		aqvacultureDao.updateAqvaCulture(book);
		response.sendRedirect("list");
	}
	
	private void deleteAqvaCulture(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		aqvacultureDao.deleteAqvaCulture(id);
		response.sendRedirect("list");

	}
	

}
