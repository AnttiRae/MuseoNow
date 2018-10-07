package paketti;

public class Henkilot {

	private int ika;
	private String nimi;
	private int palkka;
	
	public Henkilot(String n, int i, int p) {
		nimi = n;
		ika = i;
		palkka = p;
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public int getIka() {
		return ika; 
	}
	public int getPalkka() {
		return palkka;
	}

}
