package com.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rms.model.Staff;
import com.rms.util.DBUtil;

public class StaffDAO {

	public void insert(Staff staff) throws Exception {
		String sql = "INSERT INTO staff(name, email, age, phone, address) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getEmail());
			ps.setInt(3, staff.getAge());
			ps.setString(4, staff.getPhone());
			ps.setString(5, staff.getAddress());
			ps.executeUpdate();
		}
	}

	public List<Staff> getAll() throws Exception {
		List<Staff> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT name, email, age, phone, address FROM staff")) {
			while (rs.next()) {
				list.add(new Staff(
						rs.getString("name"),
						rs.getString("email"),
						rs.getInt("age"),
						rs.getString("phone"),
						rs.getString("address")));
			}
		}
		return list;
	}
}
