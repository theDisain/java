package dancers;

import java.util.concurrent.atomic.AtomicInteger;

public class Dancer implements IDancer {
	private int height;
	private int ID;
	private boolean isMale;
	private static AtomicInteger nextId = new AtomicInteger();
	public Dancer left = null ;
	public Dancer right = null;
	public int treeHeight;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getID() {
		return ID;
	}

	public void setUniqueId(int uniqueId) {
		this.ID = ID;
	}

	public boolean isMale() {
		return isMale;
	}

	@Override
	public String toString() {
		return "Dancer [height=" + height + ", IDd=" + ID + ", isMale=" + isMale + "] \n";
	}

	public Dancer(int height, boolean isMale){
		this.height = height;
		this.isMale = isMale;
		ID = nextId.incrementAndGet();
	}
	public Dancer(){
	}
}
