package Model;

public class Studente extends PersonaUniversitaria {

	public Studente(int matricola, String cognome, String nome) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public void printQualifica() {
		System.out.println("qualifica: STUDENTE");
	}
	

}
