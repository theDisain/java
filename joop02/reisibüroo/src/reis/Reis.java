package reis;
/**
 * Created by Ain-Joonas on 20.03.2015.
 */
public class Reis {
    protected double length;
    protected double maxPeople;
    protected double price;
    protected String name;
    private int serialNumber;
    protected static int counter = 100000;

    public Reis( String name, double length, double maxPeople, double price){
        this.length = length;
        this.maxPeople = maxPeople;
        this.price = price;
        this.name = name;
        this.serialNumber = counter++;
    }
    public void setMaxPeople(double maxPeople) {this.maxPeople = maxPeople;}
    public double getMaxPeople(){return maxPeople;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public double getLength() {return length;}
    public void setLength(double length) {this.length = length;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return name + "\t"
                + "length: " + length + "hours " + "or " + length / 24 + "days" +  ','
                + "price: " + price + "EUR "+ ','
                + "maximal amount of people: " +
                maxPeople + ','
                + "serial number: " + serialNumber + "\n";

    }
    public String number(){return  "serialnumber: #" + serialNumber;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reis reis = (Reis) o;

        if (Double.compare(reis.length, length) != 0) return false;
        if (Double.compare(reis.maxPeople, maxPeople) != 0) return false;
        if (Double.compare(reis.price, price) != 0) return false;
        if (!name.equals(reis.name)) return false;

        return true;
    }

    @Override
    public int hashCode() { //Algne mõte oli esitada hashcode kui seerianumber, mis on iseenesest viable, mõeldes, et hashcode on unikaalne, kuid tuleb mõista, et täisarvuline integer on alati lihtsamini loetav.
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxPeople);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
