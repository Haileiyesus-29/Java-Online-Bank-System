import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Cashier> cashiers = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while(!exit){
            switch (loginDisplay()){
                case 1 -> userLogin();
                case 2 -> cashierLogin();
                case 3 -> managerLogin();
                case 0 -> exit = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }

    public static int loginDisplay(){
        System.out.println("""
                ***** Welcome to Bank *****
                1. User login
                2. Cashier login
                3. Manager login
                0. Exit
                
                Enter your choice...\s""");
        return sc.nextInt();
    }
    public static void userLogin(){
        int account;
        String password;
        System.out.println("Enter your account number");
        account = sc.nextInt();
        System.out.println("Enter your password");
        password = sc.nextLine();
        int index = userValidator(account, password);
        if(index != -1) userMenu(users.get(index));
        else System.out.println("Invalid username or password");
    }

    public static void userMenu(User user) {
        boolean exit = false;
        while (!exit){
            System.out.println("""
                *************************
                1. Transfer money
                2. Show balance
                3. Ask for debit
                4. Transaction history
                5. Today's currency
                
                0. Go back
                Enter your choice...\s""");
            int choice = sc.nextInt();
            switch (choice){
                case 1 -> transferMoney(user);
                case 2 -> getBalance(user);
                case 3 -> debitMoney(user);
                case 4 -> System.out.println("Transaction history");
                case 5 -> System.out.println("currency");
                case 0 -> exit = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }
    public static void cashierLogin(){
        String id;
        String password;
        System.out.println("Enter your id");
        sc.nextLine();
        id = sc.nextLine();
        System.out.println("Enter your password");
        password = sc.nextLine();
        int index = cashierValidator(id, password);
        if(index != -1) cashierMenu();
        else System.out.println("Invalid ID or password");
    }
    public static void cashierMenu() {
        boolean exit = false;
        while (!exit){
            System.out.println("""
                *************************
                1. Transfer from account
                2. Deposit on account
                3. Debit on account
                4. Withdraw from account
                5. Today's currency
                6. Create new account
                7. Get account data
                0. Go back
                Enter your choice...\s""");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    User user = cashierAccountFinder();
                    if(user != null) transferMoney(user);
                }
                case 2 -> {
                    User user = cashierAccountFinder();
                    if(user != null) depositMoney(user);
                }
                case 3 -> {
                    User user = cashierAccountFinder();
                    if(user != null) debitMoney(user);
                }
                case 4 -> {
                    User user = cashierAccountFinder();
                    if(user != null) withdrawMoney(user);
                }
                case 5 -> System.out.println("currency");
                case 6 -> createCustomerAccount();
                case 7 -> {
                    User user = cashierAccountFinder();
                    if(user != null) getCustomerData(user);
                }
                case 0 -> exit = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }
    private static void getBalance(User user){
        System.out.println("The total balance is : " + user.getBalance());
    }
    private static void getCustomerData(User u){
        String str = """
                FirstName     LastName     AccountNum     Sex     Balance     Credit
                """;
        System.out.println(
                        str + "\n" +
                        u.getFirstName()
                        + "     "+ u.getLastName()
                        + "     "+u.getAccount()
                        + "     "+u.getSex()
                        + "     "+u.getBalance()
                        + "     "+u.getCredit()
        );
    }
    private static void createCustomerAccount(){
        User user = new User();
        users.add(user);
        System.out.println("Successfully added new customer to the system");
    }
    private static void withdrawMoney(User user){
        System.out.println("Enter the amount you want to withdraw");
        int amount = sc.nextInt();
        if(user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            System.out.println("Successfully withdrew " + amount + "birr");
        } else System.out.println("the account doesn't have enough money");
    }
    private static void debitMoney(User user){
        System.out.println("Enter the amount you want to borrow");
        int amount = sc.nextInt();
        if((totalDeposit() - totalDebited()) > amount && user.getCredit() == 0) {
            user.setBalance(user.getBalance() + amount);
            user.setCredit(amount);
            System.out.println(amount + "birr successfully credited to your account.");
            System.out.println("Your current balance is "+ user.getBalance());
        } else System.out.println("Can't done the request right now!");
    }
    private static void depositMoney(User user){
        System.out.println("Enter the amount you want to deposit");
        int amount = sc.nextInt();
        user.setBalance(user.getBalance() + amount);
        System.out.println("Successfully deposited!");
    }
    private static void transferMoney(User user){
        System.out.println("Enter the account number you want to transfer");
        int account = sc.nextInt();
        int index = searchUser(account);
        if(index != -1){
            System.out.println("Enter the amount you want to transfer");
            int amount = sc.nextInt();
            if(user.getBalance() >= amount){
                User u = users.get(index);
                u.setBalance(u.getBalance()+amount);
            } else System.out.println("the account doesn't have sufficient amount!");
        } else System.out.println("The account doesn't exist");
    }


    public static void managerLogin(){
        String id;
        String password;
        System.out.println("Enter an ID");
        sc.nextLine();
        id = sc.nextLine();
        System.out.println("Enter password");
        password = sc.nextLine();
        if(Manager.getId().equals(id) && Manager.getPassword().equals(password))
            managerMenu();
        else System.out.println("Incorrect id or password");
    }
    public static void managerMenu(){
        boolean exit = false;
        while(!exit){
            System.out.println("""
                ************************
                1. show all customers data
                2. search customer
                3. remove customer
                4. add new cashier
                5. show all cashiers data
                6. remove cashier
                7. set currency
                8. make interest money
                9. total deposited amount
                10. total debited amount
                0. Go back
                Enter your choice...\s""");
            int option = sc.nextInt();
//            sc.nextLine();
            switch (option) {
                case 1 -> showAllCustomers();
                case 2 -> searchCustomer();
                case 3 -> removeCustomer();
                case 4 -> addCashier();
                case 5 -> showAllCashiers();
                case 6 -> removeCashier();
                case 7 -> System.out.println("manage user data");
                case 8 -> makeInterest();
                case 9 -> System.out.println("The total amount of money deposited on the bank is " + totalDeposit());
                case 10 -> System.out.println("The total amount of money debited on the bank is " +  totalDebited());
                case 0 -> exit = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }
    private static void showAllCustomers(){
        if(users.isEmpty()) {
            System.out.println("No data!");
            return;
        }
        String str = """
                  FName     LName     AcctNum     Sex     Bal    Credit
                """;
        System.out.println(str);
        for(int i=0; i<users.size(); i++){
            User u = users.get(i);
            System.out.println(
                    (i+1) + ") " + u.getFirstName()
                            + "     "+ u.getLastName()
                            + "     "+u.getAccount()
                            + "     "+u.getSex()
                            + "     "+u.getBalance()
                            + "     "+u.getCredit()
            );
        }
    }
    private static void searchCustomer(){
        int account;
        System.out.println("Enter the account number you want to search for");
        account = sc.nextInt();
        int index = searchUser(account);
        if( index != -1){
            String str = """
                  FName     LName     AccNum     Sex     Bal     Credit
                """;
            User u = users.get(index);
            System.out.println(
                    str + "\n" +
                    (index + 1)  + ") " + u.getFirstName()
                            + "     "+ u.getLastName()
                            + "     "+u.getAccount()
                            + "     "+u.getSex()
                            + "     "+u.getBalance()
                            + "     "+u.getCredit()
            );
        } else System.out.println("Account doesn't exist!");
    }
    private static void removeCustomer(){
        int account;
        System.out.println("Enter the account number you want to remove from the system");
        sc.nextLine();
        account = sc.nextInt();
        int index = searchUser(account);
        if( index != -1){
            users.remove(index);
            System.out.println("Successfully removed the customer!");
        } else System.out.println("Account doesn't exist!");
    }

    private static void addCashier(){
        Cashier cashier = new Cashier();
        cashiers.add(cashier);
        System.out.println("Successfully added new cashier");
    }
    private static void showAllCashiers(){
        if(cashiers.isEmpty()) {
            System.out.println("No data!");
            return;
        }
        String str = """
                  FtName     LName     Branch     Sex     ID
                """;
        System.out.println(str);
        for(int i=0; i<cashiers.size(); i++){
            Cashier c = cashiers.get(i);
            System.out.println(
                    (i+1) + ") " + c.getFirstName()
                            + "     "+ c.getLastName()
                            + "     "+c.getBranch()
                            + "     "+c.getSex()
                            + "     "+c.getId()
            );
        }
    }
    public static void removeCashier(){
        String id;
        System.out.println("Enter an ID of cashier you want to remove");
        id = sc.nextLine();
        int index = -1;
        for(int i=0; i<cashiers.size(); i++){
            Cashier c = cashiers.get(i);
            if(c.getId().equals(id)){
                index = i;
            }
        }
        if( index != -1){
            cashiers.remove(index);
            System.out.println("Successfully removed the cashier!");
        } else System.out.println("Account doesn't exist!");
    }

    private static void makeInterest(){
        for (User u : users) {
            u.setBalance((int) (u.getBalance() * 1.01));
        }
        System.out.println("new interest successfully added");
    }
    private static int totalDeposit(){
        int total = 0;
        for (User u : users) {
            total += u.getBalance();
        }
//        System.out.println("The total deposited money on the bank is "+ total);
        return total;
    }
    private static int totalDebited(){
        int total = 0;
        for (User u : users) {
            total += u.getCredit();
        }
//        System.out.println("The total debited money to the customers is "+ total);
        return total;
    }

    private static User cashierAccountFinder(){
        System.out.println("Enter account number");
        int account = sc.nextInt();
        if(searchUser(account) != -1){
            return users.get(searchUser(account));
        } else {
            System.out.println("Account doesn't exist");
        }
        return null;
    }
    public static int searchUser(int account){
        for(int i=0; i<users.size(); i++)
            if(users.get(i).getAccount() == account) return i;
        return -1;
    }
    public static int userValidator(int account, String password){
        for(int i=0; i<users.size(); i++)
            if(users.get(i).getAccount() == account && users.get(i).getPassword().equals(password))
                return i;
        return -1;
    }
    public static int cashierValidator(String id, String password){
        for(int i=0; i<cashiers.size(); i++)
            if(cashiers.get(i).getId().equals(id) && cashiers.get(i).getPassword().equals(password))
                return i;
        return -1;
    }

}