package hw;

/**
 * Created by Ain-Joonas on 19.02.2015.
 */
public class Passenger {
    private static int passengerCount = 0;

    protected int passengerID = -1;
    private int age = -1;
    private String place = "Estonia";
    private String residentID = "";

    Passenger(int pid, int age, String place, String rid) {
        this.passengerID = pid;
        this.age = age;
        this.place = place;
        this.residentID = rid;
        passengerCount++;
    }

    public static int getPassengerCount() {
        return passengerCount;
    }

    public int getCode() {
        return passengerID;
    }
}
