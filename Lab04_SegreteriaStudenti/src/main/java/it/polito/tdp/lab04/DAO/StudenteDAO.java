package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Studente s = new Studente(matricola, cognome, nome, cds);
				studenti.add(s);
			}
			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public Studente getStudente(int matricola) {
		for(Studente s : this.getTuttiGliStudenti()) {
			if(s.getMatricola() == matricola) {
				return s;
			}
		}
		return null;
	}
	
	public List<Corso> getCorsiPerStudente(Studente studente) {
		final String sql = "SELECT * "
				+ "FROM iscrizione, corso "
				+ "WHERE iscrizione.codins = corso.codins AND matricola = ?";
		
		List<Corso> corsi = new LinkedList<Corso>();

		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));				
				corsi.add(c);
			}
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		final String sql = "SELECT codins FROM iscrizione WHERE codins = ? AND matricola = ?";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				
				final String sql2 = "INSERT INTO iscrizione VALUES (?, ?)";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(2, corso.getCodins());
				st2.setInt(1, studente.getMatricola());
				st2.executeUpdate();
				
				//rs2.close();
				st2.close();
				conn.close();
				
				return false;
			}
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
