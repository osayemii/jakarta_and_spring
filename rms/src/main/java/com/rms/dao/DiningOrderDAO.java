package com.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.rms.model.DiningOrder;
import com.rms.util.DBUtil;

public class DiningOrderDAO {

	public void insert(DiningOrder order) throws Exception {
		String sql = "INSERT INTO dining_order(table_number, dish_name, notes, total_price, order_date) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, order.getTableNumber());
			ps.setString(2, order.getDishName());
			ps.setString(3, order.getNotes());
			ps.setDouble(4, order.getTotalPrice());
			ps.setObject(5, order.getOrderDate());
			ps.executeUpdate();
		}
	}

	public List<DiningOrder> getAll() throws Exception {
		List<DiningOrder> list = new ArrayList<>();
		String sql = "SELECT table_number, dish_name, notes, total_price, order_date FROM dining_order";
		try (Connection conn = DBUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				String notes = rs.getString("notes");
				if (notes == null) {
					notes = "";
				}
				list.add(new DiningOrder(
						rs.getInt("table_number"),
						rs.getString("dish_name"),
						notes,
						rs.getDouble("total_price"),
						rs.getObject("order_date", LocalDate.class)));
			}
		}
		return list;
	}
}
