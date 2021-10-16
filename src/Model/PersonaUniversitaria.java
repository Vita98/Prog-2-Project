package Model;

public abstract class PersonaUniversitaria{
	protected int matricola;
	protected String cognome;
	protected String nome;
	
	public void chiSono() {
		System.out.println("cognome: " + cognome);
		System.out.println("nome: " + nome);
		printQualifica();
		System.out.println("matricola: " + matricola + "\n");
	}
	
	public abstract void printQualifica();
	
	@Override
	public boolean equals(Object arg0) {
		PersonaUniversitaria p;
		try {
			p = (PersonaUniversitaria)arg0;
		}catch(ClassCastException e) {
			return false;
		}
		
		return this.matricola == p.matricola;
	}
}
