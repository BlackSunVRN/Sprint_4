import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Account {

    private final String name;

    public Account(String name) {
        this.name = name;
    }

    Pattern pattern = Pattern.compile("(?=(^\\S+\\s\\S+$))(.{3,19})");

    public boolean checkNameToEmboss() {
        try {
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        } catch (NullPointerException e) {
            return false;
        }
    }
}
