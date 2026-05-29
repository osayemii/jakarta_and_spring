package com.jemimahRecipe.dao;

import java.sql.*;
import java.util.*;

import com.jemimahRecipe.model.Recipe;
import com.jemimahRecipe.util.DBUtil;

public class RecipeDAO {
	
	public void insert(Recipe recipe) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO jemimahRecipe(names, descriptions) VALUES (?, ?)");
			
		ps.setString(1, recipe.getName());
		ps.setString(2, recipe.getDescription());

		ps.executeUpdate();
	}
	
	public List<Recipe> getAll() throws Exception{
		List<Recipe> list = new ArrayList<>();
		
		Connection conn = DBUtil.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM jemimahRecipe");
		
		while (rs.next()) {
			list.add(new Recipe(rs.getString(1), rs.getString(2)));
		}
		
		return list;
	}
}