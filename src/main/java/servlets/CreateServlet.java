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

public class CreateServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemName = req.getParameter("item_name");
		Double costPrice = Double.valueOf(req.getParameter("cost"));
		Double sellPrice = Double.valueOf(req.getParameter("sell"));
		PrintWriter out = res.getWriter();

		Service inventory = new ServiceImpl();
		try {
			List<InventoryDTO> dtoList = inventory.create(itemName, costPrice, sellPrice);
			if (CollectionUtils.isNotEmpty(dtoList)) {
				out.write("Insert Successfull");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
