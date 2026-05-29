package com.tcms2.dao;

import com.tcms2.model.Student;
import com.tcms2.util.DBUtil;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void save(Student student) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO students(name, email) VALUES (?, ?)");

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());

            ps.executeUpdate();
        }
    }

    public List<Student> getAll() throws Exception {
        List<Student> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                list.add(new Student(
                    rs.getString("name"),
                    rs.getString("email")
                ));
            }
        }
        return list;
    }
}
