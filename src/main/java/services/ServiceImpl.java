package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.InventoryDAO;
import dao.InventoryDAOImpl;
import dto.InventoryDTO;

public class ServiceImpl implements Service {
	InventoryDAO dao = new InventoryDAOImpl();
	InventoryDTO dto = new InventoryDTO();
	List<InventoryDTO> dtoList = new ArrayList<InventoryDTO>();

	public List<InventoryDTO> create(String itemName, Double costPrice, Double sellingPrice) throws SQLException {
		dtoList = dao.create(itemName, costPrice, sellingPrice);
		return dtoList;
	}

	public List<InventoryDTO> updateBuy(String itemName, int quantity) throws SQLException {
		dtoList = dao.updateBuy(itemName, quantity);

		return dtoList;
	}

	public List<InventoryDTO> updateSell(String itemName, int quantity) throws SQLException {
		dtoList = dao.updateSell(itemName, quantity);

		return dtoList;
	}

	public boolean delete(String itemName) throws SQLException {
		dao.delete(itemName);
		return true;
	}

	public List<InventoryDTO> getReport() {
		dtoList = dao.report();
		return dtoList;
	}

}
