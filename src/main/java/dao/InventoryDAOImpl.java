package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.InventoryDTO;

public class InventoryDAOImpl implements InventoryDAO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:33060/inventory_management";
	
	// Database credentials
		static final String USER = "root";
		static final String PASSWORD = "simmi@0502";

	List<InventoryDTO> dtoList = new ArrayList<InventoryDTO>();
	InventoryDTO dto = new InventoryDTO();

	public List<InventoryDTO> create(String itemName, Double costPrice, Double sellingPrice)
			throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		int max_inv_id = 1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String get_itemName = "Select item_name from inventory_management.item_details where item_name = '" + itemName
					+ "'";
			System.out.println(get_itemName);
			ResultSet i_names = stmt.executeQuery(get_itemName);
			if (i_names.isBeforeFirst()) {
				dtoList = null;
			}

			if (!i_names.isBeforeFirst()) {
				// get the max id and add one to provide new id for new item
				String max_id_query = "SELECT max(item_id) FROM inventory_management.item_details";
				ResultSet max_id = stmt.executeQuery(max_id_query);
				while (max_id.next()) {
					max_inv_id = max_id.getInt(1) + 1;
				}
				String insert_query = "INSERT INTO item_details (item_id, item_name, selling_price, cost_price) values ('"
						+ max_inv_id + "','" + itemName + "'," + sellingPrice + "," + costPrice + ")";
				stmt.executeUpdate(insert_query);
				String select_query = "SELECT * from inventory_management";
				ResultSet rs = stmt.executeQuery(select_query);
				while (rs.next()) {
					System.out.println(rs.getString("cost_price") + " " + rs.getString("item_name") + " "
							+ rs.getString("selling_price"));
				}
				dto.setDone(true);
				dtoList.add(dto);
				max_id.close();
			}

			// Clean-up environment
			stmt.close();
			conn.close();

		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	public List<InventoryDTO> updateBuy(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String update_query = "UPDATE item_details SET quantity = " + quantity + "  WHERE (item_name = '"
					+ itemName + "')";
			int rs = stmt.executeUpdate(update_query);
			dto.setDone(true);
			dtoList.add(dto);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dtoList;
	}

	public List<InventoryDTO> updateSell(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String getItemQuantityQuery = "Select quantity from item_details where item_name = '" + itemName
					+ "'";
			ResultSet result_quantity = stmt.executeQuery(getItemQuantityQuery);
			int avaliable_Quantity = 0;
			while (result_quantity.next()) {
				avaliable_Quantity = result_quantity.getInt("quantity");
			}
			int updatedQuantity = avaliable_Quantity - quantity;

			String getSoldQuantityQuery = "Select sold_quantity from item_details where item_name = '" + itemName
					+ "'";
			ResultSet result_sold_quantity = stmt.executeQuery(getSoldQuantityQuery);
			int sold_Quantity = 0;
			while (result_sold_quantity.next()) {
				sold_Quantity = result_sold_quantity.getInt("sold_quantity");
			}
			int updatedSoldQuantity = sold_Quantity + quantity;

			String updateSellQuery = "UPDATE inventory_management.item_details SET quantity = " + updatedQuantity
					+ ", sold_quantity = " + updatedSoldQuantity + " WHERE (item_name = '" + itemName + "')";
			int rs = stmt.executeUpdate(updateSellQuery);
			dto.setDone(true);
			dtoList.add(dto);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dtoList;
	}

	// To Delete the item from the inventory
	public boolean delete(String itemName) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String get_itemName = "Select item_name from inventory_management.item_details where item_name = '" + itemName
					+ "'";
			ResultSet i_names = stmt.executeQuery(get_itemName);

			if (i_names.isBeforeFirst()) {
				String delete_query = "DELETE from inventory_management.item_details WHERE (item_name = '" + itemName
						+ "')";
				int rs = stmt.executeUpdate(delete_query);
				// Clean-up environment
				stmt.close();
				conn.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;

	}

	public List<InventoryDTO> report() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String q = "SELECT * from item_details where active=1";
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {

				Double profit = 0.00;
				String itemName = rs.getString("item_name");
				Double buy_price = rs.getDouble("cost_price");
				Double sell_price = rs.getDouble("selling_price");
				Integer quantity = rs.getInt("quantity");
				Double spent_amt = rs.getDouble("cost_price") * rs.getInt("quantity");
				profit = profit + ((rs.getDouble("selling_price") - rs.getDouble("cost_price"))
						* rs.getInt("sold_quantity") * sell_price);
				InventoryDTO newdto = new InventoryDTO();


				newdto.setItem_name(itemName);
				newdto.setCost_price(buy_price);
				newdto.setSelling_price(sell_price);
				newdto.setQuantity(quantity);
				newdto.setProfit(profit);

				dtoList.add(newdto);
			}
			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (

		Exception exc) {
			exc.printStackTrace();
		}
		return dtoList;
	}

	@Override
	public List<InventoryDTO> updateSellPrice(String itemName, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

}
