package Model;

public class Docente extends PersonaUniversitaria{

	public Docente(int matricola, String cognome, String nome) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
	}

	@Override
	public void printQualifica() {
		System.out.println("qualifica: DOCENTE");
	}
	
}
