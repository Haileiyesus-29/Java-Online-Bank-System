import java.util.*;
public class Account {
    Scanner sc = new Scanner(System.in);
    private final String firstName;
    private final String lastName;
    private final String password;
    private final char sex;

    public Account() {
        System.out.println("Enter first name :");
        this.firstName = sc.nextLine();
        System.out.println("Enter last name :");
        this.lastName = sc.nextLine();
        System.out.println("Enter Sex :");
        this.sex = sc.next().charAt(0);
        System.out.println("Enter password :");
        sc.nextLine();
        this.password = sc.nextLine();
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }


    public char getSex() {
        return sex;
    }

}
