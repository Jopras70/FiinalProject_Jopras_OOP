package Facility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;

public class Spa extends Facility{
    static Scanner s = new Scanner(System.in);
    
    //Function to handle spa reservation
    public void reservationForm(String name, String username){
        //Making arraylist(s) to store spa menu and price, and user-picked spa menu and prices.
        ArrayList<String> menuName = new ArrayList<String>();
        ArrayList<Integer> menuPrice = new ArrayList<Integer>();
        ArrayList<String> pickedMenuName = new ArrayList<String>();
        ArrayList<Integer> pickedMenuPrice = new ArrayList<Integer>();
        
        try {
            //Get txt file used as spa menu database
            URL loc = this.getClass().getResource("SpaMenu.txt");
            File file = new File(loc.getPath().toString());
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    String data[] = reader.nextLine().split(" - ");
                    menuName.add(data[0]);
                    menuPrice.add(Integer.parseInt(data[1]));
                }
            }
        } catch (Exception e) {
//            System.out.println("An error occurred.");
        }
        
        //Print spa menu
        System.out.println("\n\t\tSpa Menu : ");
        for (int i = 0; i < menuName.size()-1; i++) {
            System.out.println("\t\t" + (i+1) + ". " + menuName.get(i) + " - IDR " + menuPrice.get(i));
        }
        
        //Enter total number of services to book
        int number;
        System.out.print("\n\t\tEnter number of services you want to book : ");
        number = Integer.parseInt(s.nextLine());
        
        //Input all desired spa services
        int inputServiceNumber;
        System.out.println("");
        for(int i = 0; i < number; i++){
            System.out.print("\t\tPick Service [1-15] : ");
            inputServiceNumber = Integer.parseInt(s.nextLine());
            pickedMenuName.add(menuName.get(inputServiceNumber - 1));
            pickedMenuPrice.add(menuPrice.get(inputServiceNumber - 1));
        }
        
        //Input phone number
        System.out.print("\n\t\tEnter your phone number : ");
        String phone = s.nextLine();
        
        
        // Input appointment date
        System.out.println("\n\t\tEnter appointment date : ");
        System.out.print("\t\t\tDate (DD) : ");
        String date = s.nextLine();
        System.out.print("\t\t\tMonth (MM) : ");
        String month = s.nextLine();
        System.out.print("\t\t\tYear (YY) : ");
        String year = s.nextLine();
        String dateString = year + "-" + month + "-" + date;
        
        //Calculating total fee
        long total = 0;
        for (int i = 0; i < pickedMenuPrice.size(); i++) {
            total = total + pickedMenuPrice.get(i);
        }
        
        // Bundling all picked services in one String
        System.out.println(pickedMenuName.size());
        String allServices = "";
        for (int i = 0; i < pickedMenuName.size(); i++) {
            if(i == 0)
                allServices = allServices + pickedMenuName.get(i);
            else
                allServices = allServices + ", " + pickedMenuName.get(i);
        }
          
        // Choosing a payment method
        System.out.println("\n\t\tPayment Methods : " + 
                "\n\t\t1. Cash" +
                "\n\t\t2. Credit Card");
        System.out.print("\n\t\tChoose a payment method [1-2] : ");
        int paymentMethod = Integer.parseInt(s.nextLine());

        long payment;
        String cardNumber, paymentMethodString;
        if(paymentMethod == 1){
            payment = this.transaksi(total);
            paymentMethodString = "Cash";
            cardNumber = "NULL";
        }
        else{
            System.out.print("\n\t\tEnter Your Credit Card Number : ");
            cardNumber = s.nextLine();
            payment = this.transaksi(cardNumber, total);
            paymentMethodString = "Credit Card";
        }

        // Bundling all input data into one String
        String newLine = name + " - " + 
                         username + " - " + 
                         phone + " - " + 
                         allServices + " - " + 
                         dateString + " - " + 
                         paymentMethodString + " - " +
                         cardNumber + " - " +
                         "IDR " + payment;

        // Storing reservation data
        try {
            // Get txt file used as spa Reservation database
            URL loc = this.getClass().getResource("SpaReservation.txt");
            File file = new File(loc.getPath().toString());
            String fileContents;
            try (Scanner reader = new Scanner(file)) {
                StringBuilder buffer = new StringBuilder();
                while(reader.hasNextLine()) {
                    buffer.append(reader.nextLine()).append(System.lineSeparator());
                }   fileContents = buffer.toString();
            }
            fileContents = fileContents + newLine;
            try{
                FileWriter writer = new FileWriter(loc.getPath().toString());
                writer.append(fileContents);
                writer.flush();
            } catch (IOException e) {
                System.out.print("An error occurred." + e);
            }
        } catch (Exception e) {
            System.out.print("An error occurred." + e);
        }
    }
    
    // Function to print all spa reservation data.
    public void printData(){
        try {
            // Get txt file used as Spa Reservation database
            URL loc = Hotel.class.getResource("SpaReservation.txt");
            File file = new File(loc.getPath().toString());
            try (Scanner reader = new Scanner(file)) {
                int i = 1;
                while (reader.hasNextLine()) {
                    String data[] = reader.nextLine().split(" - ");
                    System.out.println("\n\t\tData " + (i));
                    System.out.println("\t\tName : " + data[0]);
                    System.out.println("\t\tUsername: " + data[1]);
                    System.out.println("\t\tPhone Number: " + data[2]);
                    System.out.println("\t\tReserved Spa Services : " + data[3]);
                    System.out.println("\t\tReserved Date : " + data[4]);
                    System.out.println("\t\tPayment Method : " + data[5]);
                    System.out.println("\t\tCard Number : " + data[6]);
                    System.out.println("\t\tTotal Bill : " + data[7]);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    
//    public static void main(String[] args) {
//        Spa spa = new Spa();
//        spa.reservationForm("", "");
//    }
}
