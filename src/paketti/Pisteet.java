package paketti;

public class Pisteet {
	private int Points;
	
	public Pisteet() {
		
	}
	public void AddScore(int Points) {
		Points = Points + 100;
	}
	
	public int getNewPoints() {
		return Points;
	}

}