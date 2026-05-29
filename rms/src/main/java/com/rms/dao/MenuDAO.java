package com.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rms.model.MenuItem;
import com.rms.util.DBUtil;

public class MenuDAO {

	public void insert(MenuItem item) throws Exception {
		String sql = "INSERT INTO menu_item(name, price, category) VALUES (?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, item.getName());
			ps.setDouble(2, item.getPrice());
			ps.setString(3, item.getCategory());
			ps.executeUpdate();
		}
	}

	public List<MenuItem> getAll() throws Exception {
		List<MenuItem> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT name, price, category FROM menu_item")) {
			while (rs.next()) {
				list.add(new MenuItem(
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getString("category")));
			}
		}
		return list;
	}
}
