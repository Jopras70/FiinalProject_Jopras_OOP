package reservation;

import Facility.Hotel;
import Facility.Spa;
import java.util.Scanner;
import reservation.User.Admin;
import reservation.User.Customer;

public class Reservation {
    static Scanner s = new Scanner(System.in);

    static void mainMenu(){
        System.out.println("\nLogin as : " + 
                "\n1. Admin" +
                "\n2. Customer" +
                "\n3. Exit");
    }
    
    public static void main(String[] args) {
        int mainMenu = 0;
        String inputName, inputUsername, inputPassword;
        Admin admin = new Admin();
        Customer customer = new Customer();
        Hotel hotel = new Hotel();
        Spa spa = new Spa();
        
        while(mainMenu != 3){
            int customerMenu = 0, adminMenu = 0;
            mainMenu();
            System.out.print("Choose a Menu : ");
            mainMenu = Integer.parseInt(s.nextLine());
            
            switch(mainMenu){
                case 1 : 
                    while(true){
                        System.out.print("\nEnter Username : "); inputUsername = s.nextLine();
                        System.out.print("Enter Password : "); inputPassword = s.nextLine();
                        if(admin.login(inputUsername, inputPassword) == false)
                            System.out.println("\nWrong username or password. Please try again.");
                        else
                            break;
                    }
                    
                    while(adminMenu != 4){
                        admin.printMenu();
                        System.out.print("\n\tChoose a Menu : ");
                        adminMenu = Integer.parseInt(s.nextLine());
                        
                        switch(adminMenu){
                            case 1 : 
                                System.out.print("\n\t\tEnter New Name : "); inputName = s.nextLine();
                                System.out.print("\t\tEnter New Username : "); inputUsername = s.nextLine();
                                System.out.print("\t\tEnter New Password : "); inputPassword = s.nextLine();
                                admin.update(inputName, inputUsername, inputPassword);
                                break;
                                
                            case 2 :
                                hotel.printData();
                                break;
                                
                            case 3 :
                                spa.printData();
                                break;
                        }
                    }
                    
                    break;
                    
                    
                    
                case 2 :
                    String haveAccount;
                    System.out.print("Do you already have an account? (y/n) : ");
                    haveAccount = s.nextLine();
                    
                    if(haveAccount.equals("y")){
                        while(true){
                            System.out.print("\nEnter Username : "); inputUsername = s.nextLine();
                            System.out.print("Enter Password : "); inputPassword = s.nextLine();
                            if(customer.login(inputUsername, inputPassword) == false)
                                System.out.println("\nWrong username or password. Please try again.");
                            else
                                break;
                        }
                    }
                    else{
                        System.out.print("\nEnter Name : "); inputName = s.nextLine();
                        System.out.print("Enter Username : "); inputUsername = s.nextLine();
                        System.out.print("Enter Password : "); inputPassword = s.nextLine();
                        customer.add(inputName, inputUsername, inputPassword);
                    }
                    
                    
                    while(customerMenu != 4){
                        customer.printMenu();
                        System.out.print("\n\tChoose a Menu : ");
                        customerMenu = Integer.parseInt(s.nextLine());
                        
                        switch(customerMenu){
                            case 1 : 
                                System.out.print("\n\t\tEnter New Name : "); inputName = s.nextLine();
                                System.out.print("\t\tEnter New Username : "); inputUsername = s.nextLine();
                                System.out.print("\t\tEnter New Password : "); inputPassword = s.nextLine();
                                customer.update(inputName, inputUsername, inputPassword);
                                break;
                                
                            case 2 :
                                hotel.reservationForm(customer.getName(), customer.getUsername());
                                break;
                                
                            case 3 :
                                spa.reservationForm(customer.getName(), customer.getUsername());
                                break;
                        }
                    }
                    
                    
                    break;
            }
        }
    }
    
}
