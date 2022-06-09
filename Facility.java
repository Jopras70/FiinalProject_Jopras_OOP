package Facility;

import java.util.Scanner;

public class Facility {
    static Scanner s = new Scanner(System.in);
    
    //Function to handle payment with cash
    public long transaksi(long total){
        System.out.println("\n\t\tTotal Bill : " + total);
        System.out.print("\t\tEnter Paid Bill : ");
        long paidBill = Integer.parseInt(s.nextLine());
        //Count the total change for customer's payment.
        long change = paidBill - total;
        if(change > 0){
            System.out.println("\n\t\tYour change is IDR " + change + ". Thank you for completing your payment. Hope you enjoy our services.");
        }
        else{
            System.out.println("\n\t\tThank you for your payment. Hope you enjoy our services!");
        }
        return total;
    }
    
    //Function to handle payment using credit card
    public long transaksi(String cardNumber, long total){
        System.out.println("\n\t\tTotal Bill : " + (total - (total * 0.06)));
        System.out.println("\t\tThank you for your payment. Hope you enjoy our services!");
        return total;
    }
}
