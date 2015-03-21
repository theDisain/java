package Kontor;

import reis.Eripakett;
import reis.Reis;
import reisibyroo.Reisibyroo;


/**
 * Created by Ain-Joonas on 20.03.2015.
 */
public class reisiTest {
    public static void main(String[] args) {
        Reisibyroo s = new Reisibyroo();
        Reisibyroo tln = new Reisibyroo();
        System.out.println("sõna");
        Reis suusareis = s.uusReis("suusareis", 2,3,4);
        System.out.println(suusareis.toString());
        Eripakett itaalia = (Eripakett) s.uusEriReis("Itaalia", 24,2,5);
        System.out.println(itaalia.number());
        Reis kalastus = s.uusReis("kalareis", 3,4,5);
        itaalia.setPeople(1);
        s.releaseList();
        Eripakett ajasReis = (Eripakett)tln.uusEriReis("Ajas", 48, 200,300000);

        tln.releaseList();
        System.out.println("Nyyd v2ljastame kohaliku byroo reisid");
        ajasReis.setMaxPeople(30);
        tln.releaseLocalList();
        System.out.println("Price per person for the trip Ajas " + ajasReis.pricePerPerson(4));



    }
}
