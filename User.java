import java.util.*;
public class User extends Account {
    Scanner sc = new Scanner(System.in);
    private final int account;
    private int balance;
    private int credit;
    private final ArrayList<String> history;

    public User() {
        this.history = new ArrayList<>();
        this.balance = 0;
        this.credit = 0;
        System.out.println("Enter Account Number");
        this.account = sc.nextInt();
        sc.nextLine();
    }


    public int getAccount() {
        return account;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void addHistory(String history) {
        this.history.add(history);
    }
}
