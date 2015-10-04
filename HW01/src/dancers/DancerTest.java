package dancers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DancerTest {
	DancerTree men = new DancerTree();
	DancerTree women = new DancerTree();
	public static Random random = new Random();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Dancer> dd = new ArrayList<Dancer>();
		List<Dancer> da = new ArrayList<Dancer>();
		for(int i = 0;i <= 150;i++){
			dd.add(new Dancer(random.nextInt((220-120) + 1) +120,true));
			da.add(new Dancer(random.nextInt((220-120) + 1) +120, true));
		}
		System.out.println(dd.toString());


	}

}
