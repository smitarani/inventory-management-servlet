package services;

import java.sql.SQLException;
import java.util.List;

import dto.InventoryDTO;

public interface Service {

	public List<InventoryDTO> create(String itemName, Double costPrice, Double sellingPrice) throws SQLException;

	public boolean delete(String itemName) throws SQLException;

	public List<InventoryDTO> updateBuy(String itemName, int quantity) throws SQLException;

	public List<InventoryDTO> updateSell(String itemName, int quantity) throws SQLException;

	public List<InventoryDTO> getReport();
}
