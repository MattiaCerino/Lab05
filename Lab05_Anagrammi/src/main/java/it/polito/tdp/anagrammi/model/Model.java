package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {
	
	private List<String> soluzione;
	public AnagrammaDAO dao = new AnagrammaDAO();

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso.
	 * @param parola parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		this.soluzione = new ArrayList<>() ;
		
		parola=parola.toUpperCase() ;
		
		List<Character> disponibili = new ArrayList<>() ;
		for(int i=0; i<parola.length(); i++) {
			disponibili.add(parola.charAt(i)) ;
		}
		
		// avvia la ricorsione
		cerca("", 0, disponibili) ; 
		
		return this.soluzione;
	}
	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma.
	 * 
	 * @param parziale parte iniziale dell'anagramma costruito finora
	 * @param livello livello della ricorsione, sempre uguale a parziale.length()
	 * @param disponibili insieme delle lettere non ancora utilizzate
	 */
	private void cerca(String parziale, int livello, List<Character> disponibili) {
		// CONTROLLO SE SONO NEL CASO TERMINALE
		if(disponibili.size()==0) { // livello==parola.length()
			// if(parziale è nel dizionario)
			// if(parziale non è presente nella soluzione)
			this.soluzione.add(parziale);
			
		}
		
		// CASO NORMALE
		// provare ad aggiungere a 'parziale' tutti i caratteri presenti tra i 'disponibili'
		for(Character ch: disponibili) {
			String tentativo = parziale + ch; // Concateno un carattere alla stringa parziale costruendo una stringa da usare come
			// stringa iniziale del livello successivo
			// non c'è bisogno che io faccia BACKTRACKING
			
			//if(nel dizionario esistono delle parole che iniziano con 'tentativo'?)
			
			List<Character> rimanenti = new ArrayList<>(disponibili) ;
			rimanenti.remove(ch) ;
			
			cerca(tentativo, livello+1, rimanenti) ;
		}
	}
	
	public boolean isCorrect (String anagramma) {
		return dao.isCorrect(anagramma);
	}

}

/*
Dato di partenza: parola da anagrammare, di lunghezza N
SOLUZIONE PARZIALE: una parte dell'anagramma già costruito (i primi caratteri)
LIVELLO: numero di lettere di cui è composta la soluzione parziale
SOLUZIONE FINALE: soluzione di lunghezza N -> caso terminale
CASO TERMINALE: salvare la soluzione trovate
GENERAZIONE DELLE NUOVE SOLUZIONI: provare a aggiungere una lettera, scegliendola
tra quelle che non sono ancora state utilizzate (nella soluzione parziale).
*/