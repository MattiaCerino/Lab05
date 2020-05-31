package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.polito.tdp.anagrammi.DAO.ConnectDB;

public class AnagrammaDAO {
	
	public boolean isCorrect (String anagramma) {
		String sql = "SELECT parola.nome FROM parola WHERE parola.nome = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				conn.close();
				return true;
			}
			else
				conn.close();
				return false;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
