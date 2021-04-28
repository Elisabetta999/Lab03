/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class FXMLController {
	
	Dictionary dizionario = new Dictionary();
	
	 @FXML
	    private ComboBox<String> btnLang;

	    @FXML
	    private Button btnCheck;

	    @FXML
	    private Label labelErrori;
	    
	    @FXML
	    private TextArea txtInput;

	    @FXML
	    private TextArea txtOutput;

	    @FXML
	    private Button btnClear;

	    @FXML
	    private Label labelTempo;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	this.txtInput.clear();
    	this.txtOutput.clear();
    	this.labelErrori.setText(null);
    	this.labelTempo.setText(null);

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	List<String> paroleSbagliate = new ArrayList<String>();
    	boolean trovato = false;
    	int contaErrori = 0;
    	String lang = this.btnLang.getValue();
    	this.dizionario.loadDictionary(lang);
    	String input = this.txtInput.getText().toLowerCase();
    	input = input.replaceAll("\n", " ");
    	input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    	String campi[] = input.split(" ");
    	long start = System.nanoTime();
    	for(String s : campi) {
    		trovato = this.dizionario.spellCheck(s);
    		if(trovato==false) {
    			paroleSbagliate.add(s);
    			contaErrori++;
    		}
    	}
    	long stop = System.nanoTime();
    	String vuota = "";
    	for(String m : paroleSbagliate) {
    		vuota = vuota+m+"\n";
    	}
    	this.txtOutput.setText(vuota);
    	this.labelErrori.setTextFill(Color.RED);
    	this.labelErrori.setText("Errori presenti: "+contaErrori);
    	this.labelTempo.setText("Spell Check time: "+(stop-start)/1e9);

    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	 assert btnLang != null : "fx:id=\"btnLang\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
    	 assert labelTempo != null : "fx:id=\"labelTempo\" was not injected: check your FXML file 'Scene.fxml'.";
    	 
    	 this.btnLang.getItems().addAll("English", "Italiano");
    	 this.btnLang.setValue("English");

    }
}


