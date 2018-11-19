package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Service;
import services.ServiceImpl;


public class DeleteServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemName = req.getParameter("item_name");
		PrintWriter out = res.getWriter();

		Service inventory = new ServiceImpl();
		try {
			boolean value = inventory.delete(itemName);
			if (value) {
				out.write("Deleted Successfull");
			} else {
				out.write("item not found");
			}
		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
