package paketti;

public class Pisteet {
	private int Points = 0;
	
	public void setScore(int a) {
		Points = a + Points;
	}
	
	public int getScore() {
		return Points;
	}

}