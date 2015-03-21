package reisibyroo;

import reis.Eripakett;
import reis.Reis;

import java.util.ArrayList;

/**
 * Created by Ain-Joonas on 20.03.2015.
 */
public class Reisibyroo {
    private static ArrayList<Reis> reisid = new ArrayList<>(); //Üleüldine reiside array
    private ArrayList<Reis> kohalikudReisid = new ArrayList<>(); //Kohalikus büroos loodud reiside array
    // Nii uusReis kui uusEriReis on nn 'tehased', mis lubavad kontoritel ehk reisibüroodel väljastada reise.
    public Reis uusReis(String name, double length, double maxPeople, double price){
        Reis reis = new Reis(name, length,maxPeople,price);
        reisid.add(reis);
        kohalikudReisid.add(reis);
        return reis;
    }
    public Reis uusEriReis(String name, double length, double maxPeople, double price){
        Reis erareis = new Eripakett(name, length,maxPeople,price);
        reisid.add(erareis);
        kohalikudReisid.add(erareis);
        return erareis;
    }
    //Reiside eemaldamise vahendid
    public void removeTripAll(Reis reis){
        reisid.remove(reis);
        kohalikudReisid.remove(reis);
    }
    public void removeTripLocal(Reis reis){kohalikudReisid.remove(reis);}
    public void removeTripGlobal(Reis reis){reisid.remove(reis);}

    //Polümorfismi tõestamise vahendid
    public void releaseList(){ System.out.println(reisid.toString());}
    public void releaseLocalList(){System.out.println(kohalikudReisid.toString());}
    public String releaseListAsString(){return reisid.toString();}
    public String releaseLocalListAsString(){return kohalikudReisid.toString();}

}
