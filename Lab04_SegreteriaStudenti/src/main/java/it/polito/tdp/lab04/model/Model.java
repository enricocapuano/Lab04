package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	CorsoDAO corsoDao;
	StudenteDAO studenteDao ;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Studente> getTuttiGliStudenti() {
		return studenteDao.getTuttiGliStudenti();
	}
	
	public List<Corso> getTuttiICorsi() {
		return corsoDao.getTuttiICorsi();
	}
	
	public List<Corso> getCorsiPerStudente(Studente studente) {
		return studenteDao.getCorsiPerStudente(studente);
	}
	
	public Studente getStudente(int matricola) {
		return studenteDao.getStudente(matricola);
	}
	
	public Corso getCorso(String codins) {
		return corsoDao.getCorso(codins);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		return studenteDao.iscriviStudenteACorso(studente, corso);
	}
	
}
