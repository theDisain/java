package hw;

import java.util.ArrayList;

public class Validator implements ValidatorService {

    protected ArrayList<Person> people = new ArrayList<Person>();

    public Validator() {
        Person Maku = new Person("Maku","Makusson",5,"Tallinn",12);
        Person Ene = new Person("Ene","Tammistu",28,"Haapsalu",18952);
        people.add(Maku);
        people.add(Ene);
        System.out.println(Maku.first_name + " "+ Maku.last_name + "," + Maku.aage + "," + Maku.home_city + "," + Maku.passengerID);

    }

    public boolean validate(int id) {
        for (Person p : people)
            if (p.passengerID == id) {
                p.validated = true;
                return true;
            }
        return false;
    }

    public boolean isValidated(int id) {
        for (Person p : people)
            if (p.passengerID == id)
                return p.validated;
        return false;
    }
}
