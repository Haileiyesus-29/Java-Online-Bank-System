import java.util.*;
public class Cashier extends Account {
    Scanner sc = new Scanner(System.in);
    private final String branch;
    private final String id;

    public Cashier() {
        System.out.println("Enter an ID");
        this.id = sc.nextLine();
        System.out.println("Enter branch ");
        this.branch = sc.nextLine();
    }

    public String getBranch() {
        return branch;
    }

    public String getId() {
        return id;
    }

}
