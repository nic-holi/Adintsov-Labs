// This entire file is part of my masterpiece.
// Bill Yu

package by.bsiur.game.leader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class LeaderBoard {
	private static final String LEADERS_FILE = "leaders.txt";
	private ArrayList<Leader> leaders;
	private static final int SIZE = 10;

	public LeaderBoard() {
		ArrayList<Leader> lds = read();
		leaders = lds != null ? lds : new ArrayList<Leader>();
	}


	public boolean canGetOn(int score) {
		if (leaders.size() < SIZE) return true;
		return score > leaders.get(leaders.size() - 1).getScore();
	}


	public void putOn(Leader l) {
		leaders.add(l);
		leaders.sort(null);
		//truncate leaders
		for (int i = SIZE; i < leaders.size(); i++) {
			leaders.remove(i);
		}
	}
	

	public ArrayList<Leader> getLeaders() {
		int currentSize = leaders.size();
		ArrayList<Leader> present = new ArrayList<Leader>(leaders);
		for (int i = 0; i < SIZE - currentSize; i++) {
			present.add(new Leader("-", 0));
		}
		return present;
	}


	public void save() {
		try {
			FileOutputStream fout = new FileOutputStream(LEADERS_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(leaders);
			oos.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	private ArrayList<Leader> read() {
		try{
			FileInputStream fin = new FileInputStream(LEADERS_FILE);
			ObjectInputStream ois = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			ArrayList<Leader> lds = (ArrayList<Leader>) ois.readObject();
			ois.close();
			return lds;
		} catch(Exception ex) {
			return null;
		}
	}
}
