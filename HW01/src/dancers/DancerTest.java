package dancers;

import java.util.Random;

/**
 * Created by rapka on 15.11.2015.
 */
public class DancerTest {
    public static void main(String[] args) {
        // true is male, false is female
        Random random = new Random();
        Dancer dude1 = new Dancer(0,true,random.nextInt(210-100) + 100);
        Dancer dude2 = new Dancer(1,true,random.nextInt(210-100) + 100);
        Dancer dude3 = new Dancer(2,true,random.nextInt(210-100) + 100);
        Dancer dude4 = new Dancer(3,true,random.nextInt(210-100) + 100);
        Dancer dude5 = new Dancer(4,true,random.nextInt(210-100) + 100);
        Dancer dude6 = new Dancer(5,true,random.nextInt(210-100) + 100);
        Dancer dude7 = new Dancer(6,true,random.nextInt(210-100) + 100);
        Dancer dude8 = new Dancer(7,true,random.nextInt(210-100) + 100);

        Dancer chick1 = new Dancer(0,false,random.nextInt(210-100) + 100);
        Dancer chick2 = new Dancer(1,false,random.nextInt(210-100) + 100);
        Dancer chick3 = new Dancer(2,false,random.nextInt(210-100) + 100);
        Dancer chick4 = new Dancer(3,false,random.nextInt(210-100) + 100);
        Dancer chick5 = new Dancer(4,false,random.nextInt(210-100) + 100);
        Dancer chick6 = new Dancer(5,false,random.nextInt(210-100) + 100);
        Dancer chick7 = new Dancer(6,false,random.nextInt(210-100) + 100);
        Dancer chick8 = new Dancer(7,false,random.nextInt(210-100) + 100);

        Dancers dancers = new Dancers();

        dancers.findPartnerFor(dude1);
        dancers.findPartnerFor(dude2);
        dancers.findPartnerFor(dude3);
        dancers.findPartnerFor(dude4);
        dancers.findPartnerFor(dude5);
        dancers.findPartnerFor(dude6);
        dancers.findPartnerFor(dude7);
        dancers.findPartnerFor(dude8);

        dancers.findPartnerFor(chick1);
        dancers.findPartnerFor(chick2);
        dancers.findPartnerFor(chick3);
        dancers.findPartnerFor(chick4);
        dancers.findPartnerFor(chick5);
        dancers.findPartnerFor(chick6);
        dancers.findPartnerFor(chick7);
        dancers.findPartnerFor(chick8);
        System.out.println(dancers.returnWaitingList());
        System.out.println(dancers.returnDancersList(dancers.getMen()));
        System.out.println(dancers.returnDancersList(dancers.getWomen()));
    }
}
