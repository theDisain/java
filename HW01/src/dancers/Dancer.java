package dancers;

import java.util.concurrent.atomic.AtomicInteger;

public class Dancer {
	private int height;
	private int uniqueId;
	private boolean sex;
	private static AtomicInteger nextId = new AtomicInteger();
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public boolean isSex() {
		return sex;
	}

	@Override
	public String toString() {
		return "Dancer [height=" + height + ", uniqueId=" + uniqueId + ", sex=" + sex + "] \n";
	}

	public Dancer(int height, boolean sex){
		this.height = height;
		this.sex = sex;
		uniqueId = nextId.incrementAndGet();
	}
	

}
