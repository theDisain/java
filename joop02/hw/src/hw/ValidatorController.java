package hw;

/**
 * Created by Ain-Joonas on 19.02.2015.
 */
public class ValidatorController {
    public static void main(String[] args) {
        Person.free_cities.add("Haapsalu");
        Person.free_cities.add("Tallinn");
        Validator v = new Validator();
        System.out.println("Validating 12 - " + v.validate(12));
        System.out.println("12 validated: " + v.isValidated(12));

}

}
