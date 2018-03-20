package HW5;

import java.util.Random;

public class RandomCheckoutData {
    private String name;
    private String surname;
    private String email;
    private String address;
    private String postcode;
    private String city;

    public RandomCheckoutData() {
        generateData();
    }

    private void generateData() {
        name = "Name" + generateRandomString(5);
        surname = "Surname" + generateRandomString(5);
        email = "email" + generateRandomString(5) + "@test.com";
        address = "Street " + String.valueOf((System.currentTimeMillis()));
        postcode = String.valueOf((long) (10000 + (int)(Math.random()*89999)));
        city = "City" + String.valueOf((System.currentTimeMillis()));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public static String generateRandomString (int length) {
        Random random = new Random();

        char[] values = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };

        String out = "";
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(values.length);
            out += values[idx];
        }

        return out;
    }
}
