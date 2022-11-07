package com.jabir.fhouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jabir.fhouse.module.AqvaCulture;
import com.jabir.fhouse.module.Plantation;

public class AqvaCulturDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/new?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "jabi@2001";
	// private String jdbcDriver="com.mysql.cj.jdbc.Driver";

	private static final String INSERT_AQVACULTURE_SQL = "INSERT INTO aqvacultures"
			+ "(id,species,area,startdate,eydate)VALUES" + "(?,?,?,?,?)";
	private static final String SELECT_AQVACULTURE_BY_ID = "select id,specie,area,startdate,eydate from aqvacultures where id=?";
	private static final String SELECT_ALL_AQVACULTURE = "select * from aqvacultures";
	private static final String DELETE_AQVACULTURE_SQL = "delete from aqvacultures where id=?;";
	private static final String UPDATE_AQVACULTURE_SQL = "update aqvacultures set species = ?,area = ?,startdate = ?,eydate = ? where id = ?;";

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
	public void insertAqvaCulture(AqvaCulture aqvaculture) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AQVACULTURE_SQL)) {
			preparedStatement.setInt(1, aqvaculture.getId());
			preparedStatement.setString(2, aqvaculture.getSpecies());
			preparedStatement.setString(3, aqvaculture.getArea());
			preparedStatement.setString(4, aqvaculture.getStartdate());
			preparedStatement.setString(5, aqvaculture.getEydate());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select Plantation by id
	public AqvaCulture selectAqvaCulture(int id) {
		AqvaCulture aqvacultures = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AQVACULTURE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String species = rs.getString("species");
				String area = rs.getString("area");
				String startdate = rs.getString("startdate");
				String eydate = rs.getString("eydate");
				aqvacultures = new AqvaCulture(id, species, area, startdate, eydate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aqvacultures;

	}

	// SelectAll AqvaCultures
	public List<AqvaCulture> selectAllAqvaCulture() {
		List<AqvaCulture> aqvacultures = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AQVACULTURE);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String species = rs.getString("species");
				String area = rs.getString("area");
				String startdate = rs.getString("startdate");
				String eydate = rs.getString("eydate");
				aqvacultures.add(new AqvaCulture(id, species, area, startdate, eydate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aqvacultures;

	}

	// Delete AqvaCulture
	public Boolean deleteAqvaCulture(int id) throws SQLException {
		boolean rowDelete;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_AQVACULTURE_SQL);) {
			statement.setInt(1, id);
			rowDelete = statement.executeUpdate() > 0;
		}
		return rowDelete;
	}

	// Update Plantation
	public boolean updateAqvaCulture(AqvaCulture aqvaculture) throws SQLException {
		boolean rowUpdate;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_AQVACULTURE_SQL);) {
			statement.setString(1, aqvaculture.getSpecies());
			statement.setString(2, aqvaculture.getArea());
			statement.setString(3, aqvaculture.getStartdate());
			statement.setString(4, aqvaculture.getEydate());
			statement.setInt(5, aqvaculture.getId());
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

	

