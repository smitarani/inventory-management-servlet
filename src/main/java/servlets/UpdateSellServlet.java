package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;

import dto.InventoryDTO;
import services.Service;
import services.ServiceImpl;


public class UpdateSellServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemName = req.getParameter("item_name");
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));

		PrintWriter out = res.getWriter();

		Service inventory = new ServiceImpl();
		try {
			List<InventoryDTO> dtoList = inventory.updateSell(itemName, quantity);
			if (CollectionUtils.isNotEmpty(dtoList)) {
				out.write("update Successfull");
			}
		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
