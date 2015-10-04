package dancers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DacerTest {
	public static Random random = new Random();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Dancer> dd = new ArrayList<Dancer>();
		for(int i = 0;i <= Integer.MAX_VALUE;i++){
			dd.add(new Dancer(random.nextInt((220-120) + 1) +120,random.nextBoolean()));
		}
		System.out.println(dd.toString());

	}

}
