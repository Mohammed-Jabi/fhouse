package com.jabir.fhouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jabir.fhouse.module.Plantation;

public class PlantationDoa/* Data access object */ {

	private String jdbcURL = "jdbc:mysql://localhost:3306/new?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "jabi@2001";
	// private String jdbcDriver="com.mysql.cj.jdbc.Driver";

	private static final String INSERT_PLANTATION_SQL = "INSERT INTO plantations"
			+ "(id,plantation,area,pdate,ydate)VALUES" + "(?,?,?,?,?)";
	private static final String SELECT_PLANTATION_BY_ID = "select id,plantation,area,pdate,ydate from plantations where id=?";
	private static final String SELECT_ALL_PLANTATION = "select * from plantations";
	private static final String DELETE_PLANTATION_SQL = "delete from plantations where id=?;";
	private static final String UPDATE_PLANTATION_SQL = "update plantations set plantation = ?,area = ?,pdate = ?,ydate = ? where id = ?;";

	protected Connection getConnection() {
		Connection connectio = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connectio = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connectio;
	}

	// Insert plantation
	public void insertPlantation(Plantation plantation) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLANTATION_SQL)) {
			preparedStatement.setInt(1, plantation.getId());
			preparedStatement.setString(2, plantation.getPlantation());
			preparedStatement.setString(3, plantation.getArea());
			preparedStatement.setString(4, plantation.getPdate());
			preparedStatement.setString(5, plantation.getYdate());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select Plantation by id
	public Plantation selectPlantation(int id) {
		Plantation plantations = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLANTATION_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String plantation = rs.getString("plantation");
				String area = rs.getString("area");
				String pdate = rs.getString("pdate");
				String ydate = rs.getString("ydate");
				plantations = new Plantation(id, plantation, area, pdate, ydate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plantations;

	}

	// SelectAll Plantation
	public List<Plantation> selectAllPlantation() {
		List<Plantation> plantations = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLANTATION);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String plantation = rs.getString("plantation");
				String area = rs.getString("area");
				String pdate = rs.getString("pdate");
				String ydate = rs.getString("ydate");
				plantations.add(new Plantation(id, plantation, area, pdate, ydate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plantations;

	}

	// Delete Plantation
	public Boolean deletePlantation(int id) throws SQLException {
		boolean rowDelete;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PLANTATION_SQL);) {
			statement.setInt(1, id);
			rowDelete = statement.executeUpdate() > 0;
		}
		return rowDelete;
	}

	// Update Plantation
	public boolean updatePlantation(Plantation plantation) throws SQLException {
		boolean rowUpdate;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PLANTATION_SQL);) {
			statement.setString(1, plantation.getPlantation());
			statement.setString(2, plantation.getArea());
			statement.setString(3, plantation.getPdate());
			statement.setString(4, plantation.getYdate());
			statement.setInt(5, plantation.getId());
			rowUpdate = statement.executeUpdate() > 0;
		}
		return rowUpdate;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}

	}
}
