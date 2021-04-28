package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> dizionario = new ArrayList<String>();
	
	public void loadDictionary(String language) {
		try {

			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;

			while ((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase());
			}

			Collections.sort(dizionario);

			br.close();
			System.out.println("Dizionario " + language + " loaded. Found " + dizionario.size() + " words.");
			
			return;

		} catch (IOException e) {
			System.err.println("Errore nella lettura del file");
			return;
		}
	}

	public boolean spellCheck(String s) {
		if(dizionario.contains(s)) {
			return true;
		}
		return false;
	}
	
	public boolean spellCheckLinear(String s) {
		boolean trovato = false;
		for(String m : dizionario) {
			if(m.equals(s)) {
				trovato = true;
				break;
			}
		}
		return trovato;
	}
	
	public boolean spellCheckDichotomic(String elementToSearch) {

	    int firstIndex = 0;
	    int lastIndex = this.dizionario.size() - 1;

	    // termination condition (element isn't present)
	    while(firstIndex <= lastIndex) {
	        int middleIndex = (firstIndex + lastIndex) / 2;
	        // if the middle element is our goal element, return its index
	        if (this.dizionario.get(middleIndex).equals(elementToSearch)) {
	            return true;
	        }

	        // if the middle element is smaller
	        // point our index to the middle+1, taking the first half out of consideration
	        else if (this.dizionario.get(middleIndex).compareTo(elementToSearch)<0)
	            firstIndex = middleIndex + 1;

	        // if the middle element is bigger
	        // point our index to the middle-1, taking the second half out of consideration
	        else if (this.dizionario.get(middleIndex).compareTo(elementToSearch)>0)
	            lastIndex = middleIndex - 1;

	    }
	    return false;
	}

}
