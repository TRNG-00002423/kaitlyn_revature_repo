package money;

import java.util.HashSet;
import java.util.Set;

public class MoneyDemo {
    public static void main(String[] args) {

        Money thousandCents = new Money("cents", 1000);
        Money thousandCentsAgain = new Money("cents", 1000);

        Set<Money> monies = new HashSet<Money>();
        monies.add(thousandCents);
        monies.add(thousandCentsAgain);
        System.out.println("Length of HashSet: " + monies.size());

        System.out.println("thousandCents == thousandCentsAgain -> " + (thousandCents == thousandCentsAgain));
        System.out.println("thousandCents.equals(thousandCentsAgain) -> " + thousandCents.equals(thousandCentsAgain));

    }
}