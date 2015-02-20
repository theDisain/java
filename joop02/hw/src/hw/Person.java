package hw;

import java.util.ArrayList;

/**
 * Created by Ain-Joonas on 19.02.2015.
 */
public class Person extends Validator{
    public static ArrayList<String> free_cities = new ArrayList<String>();
    public boolean validated = false;
    public String first_name;
    public String last_name;
    public int aage = -1;
    public String home_city;
    public int passengerID = -1;



        public Person (String fname, String lname, int age, String hcity, int id) {

            first_name = fname;
            last_name = lname;
            aage = age;
            home_city = hcity;
            passengerID = id;

            if (age < 8) {
                this.validated = true;
                return;
            }
            for (String c : free_cities)
                if (hcity.toLowerCase().equals(c.toLowerCase())) {
                    this.validated = true;
                    break;
                }
    }
}
