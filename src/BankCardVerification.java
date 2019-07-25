import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankCardVerification {
    public static boolean isValidNum(String individualCardNumber)
    {
        String regexIndividualNumber ="(([0-9]{4}-?){4})";
        Pattern numPattern = Pattern.compile(regexIndividualNumber);
        Matcher numMatcher = numPattern.matcher(individualCardNumber);
        return numMatcher.matches();
    }

    public static boolean isValidPin(String pin)
    {
        String regexPin = "([0-9]{4})";
        Pattern pinPattern = Pattern.compile(regexPin);
        Matcher pinMatcher = pinPattern.matcher(pin);
        return pinMatcher.matches();
    }
}
