package reis;

/**
 * Created by Ain-Joonas on 20.03.2015.
 */
public class Eripakett extends Reis {
    private int serialNumber;
    protected static int counter = 100000;
    private double people = 0;
    public Eripakett(String name, double length, double maxPeople, double price) {
        super(name, length, maxPeople, price);
        this.length = length;
        this.price = price;
        this.maxPeople = maxPeople;
        this.name = name;
        if(people >= maxPeople) this.people = maxPeople;
        if(maxPeople >= 15) price = 150;
        this.serialNumber = counter++;
        nullifyPeople();
    }

    public double setPeople(double people){ //Võimaldab inimeste hulka sättida.
        this.people = people;
        return people;
    }
    public double addPeople(double people){ //Võimaldab lisada inimesi
        this.people += people;
        return people;
    }
    public double removePeople(double people){ //VÕimaldab eemaldada inimesi
        this.people -= people;
        return people;
    }
    private double nullifyPeople(){ //Teeb kindlaks, et ei saa olla 0 inimest
        if(people <= 0)this.people = 0; return this.people;
    }
    public double pricePerPerson(double people){ //Arvutab hinna inimese kohta
        if(people >= maxPeople) return -1;
        double pricePerPerson = price / people;
        return pricePerPerson;
    }

    @Override
    public String toString() {
        return name + "\t" + "length: " + length + "hours or " + length / 24 + "days" +  ','
                + "price: " + price + " EUR" + ','
                +"Registered amount of people: " + people + ','
                + "maximal amount of people: " + maxPeople + ','
                + "serial number: " + serialNumber + "\n";
    }
}
