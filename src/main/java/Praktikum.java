public class Praktikum {

    public static void main(String[] args) {
        String name = args.toString();
        Account account = new Account(name);
        account.checkNameToEmboss();
    }
}
