package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnCheck;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.txtNome.clear();
    	this.txtCognome.clear();
		this.txtResult.clear();
    	String m = txtMatricola.getText();
    	int matricola;
    	String s = "";
    	try {
    		matricola = Integer.parseInt(m);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Errore nella scrittura della matricola");
    		return;
    	}
    	
    	if(model.getStudente(matricola) == null) {
    		txtResult.setText("Matricola non esistente");
    		return;
    	}
   		Studente studente = model.getStudente(matricola);
   		for(Corso c : model.getCorsiPerStudente(studente)) {
   			s += c.toString()+"\n";   		
    	}
    	txtResult.setText(s);
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	this.txtNome.clear();
    	this.txtCognome.clear();
		this.txtResult.clear();
    	Corso corso = boxCorsi.getValue();
    	if(corso == null) {
    		txtResult.setText("Selezionare un corso");
    		return;
    	}
    	String studenti = "";
    	for(Studente s : model.getStudentiIscrittiAlCorso(corso)) {
    		studenti += s.toString()+"\n";
    	}
    	txtResult.setText(studenti);
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	this.txtNome.clear();
    	this.txtCognome.clear();
		this.txtResult.clear();
    	Corso corso = boxCorsi.getValue();
    	String m = txtMatricola.getText();
    	boolean check = false;
    	int matricola;
    	try {
    		matricola = Integer.parseInt(m);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Errore nella scrittura della matricola");
    		return;
    	}
    	
    	if(model.getStudente(matricola) == null) {
    		txtResult.setText("Matricola non esistente");
    		return;
    	}
    	Studente studente = model.getStudente(matricola);
    	if(corso == null) {
    		txtResult.setText("Selezionare un corso");
    		return;
    	}
    	else { 		
    		for(Corso c : model.getCorsiPerStudente(studente)) {
    			if(c.equals(corso)) {
    				check = true;
    			}
    		}
    	}
    	if(check == true) {
    		txtResult.setText("Studente già iscritto a questo corso");
    	} else {
    		model.iscriviStudenteACorso(studente, corso);
    		txtResult.setText("Lo studente è stato iscritto a questo corso");
    	}
    	
    }

    @FXML
    void doCheck(ActionEvent event) {
    	this.txtNome.clear();
    	this.txtCognome.clear();
		this.txtResult.clear();
    	String m = txtMatricola.getText();
    	int matricola;
    	boolean check = false;
    	try {
    		matricola = Integer.parseInt(m);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Errore nella scrittura della matricola");
    		return;
    	}
    	
    	if(m.equals("")) {
    		txtResult.setText("Compilare il campo matricola");
    	}
    	else {
    		for(Studente s : model.getTuttiGliStudenti()) {
    			if(matricola == s.getMatricola()) {
    				this.txtNome.setText(s.getNome());
    				this.txtCognome.setText(s.getCognome());
    				check = true;
    			}
    		}
    		if(check == false) {
    			txtResult.setText("Matricola non esistente");
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	txtMatricola.clear();
    }
    
    public void setModel(Model m) {
    	this.model = m;
      	boxCorsi.getItems().addAll(model.getTuttiICorsi());
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }
}
