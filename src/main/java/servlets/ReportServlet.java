package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InventoryDTO;
import services.Service;
import services.ServiceImpl;

public class ReportServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");
		PrintWriter out = response.getWriter();

		try {
			Service inventory = new ServiceImpl();
			List<InventoryDTO> dtoList = inventory.getReport();
			int counter = 0;
			// out.write("Inventory Management Report ");
			out.write("<html><body>");
			out.write("\t INVENTORY REPORT \t\n");
			out.write("Item Name    " + "    " + "Bought At   " + "    " + "Sold At  " + "   " + "AvailableQty  " + "  "
					+ "Value  ");
			out.write("<br>------------" + "    " + "-------------" + "    " + "------------" + "   "
					+ "---------------" + "  " + "---------");

			for (InventoryDTO dto : dtoList) {

				out.write("<br>" + dto.getItem_name() + "              " + dto.getCost_price() + "            "
						+ dto.getSelling_price() + "            " + dto.getQuantity() + "             "
						+ ((dto.getCost_price()) * (dto.getQuantity())));

				out.write("<br> profit for this item is " + dto.getProfit());

			}
		} catch (Exception e) {
			response.sendRedirect("inventory.html");
		}
	}
}
