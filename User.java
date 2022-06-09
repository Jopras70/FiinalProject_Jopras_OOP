package reservation.User;

// Interface User for Customer and Admin
public interface User {
    public boolean login(String username, String password);
    public void update(String name, String username, String password);
    public void add(String name, String username, String password);
    public void printMenu();
}
