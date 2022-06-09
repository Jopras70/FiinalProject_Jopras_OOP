package Facility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

//Extends class Facility
public class Hotel extends Facility {
    static Scanner s = new Scanner(System.in);
    
    
    //Function to handle hotel reservation
    public void reservationForm(String name, String username){
        
        //Input phone number
        System.out.print("\n\t\tEnter your phone number : ");
        String phone = s.nextLine();
        
        //Picking room type
        System.out.println("\n\t\tAvailable room type : " + 
                "\n\t\t1. Standard Room" +
                "\n\t\t2. Deluxe Room" + 
                "\n\t\t3. Suite Room" + 
                "\n\t\t4. Presidential Suite Room");
        System.out.print("\n\t\tPick a room type [1-4] : ");
        int type = Integer.parseInt(s.nextLine());
        
        //Input check-in date
        System.out.println("\n\t\tEnter arrival / check-in date : ");
        System.out.print("\n\t\t\tDate (DD) : ");
        String dateA = s.nextLine();
        System.out.print("\t\t\tMonth (MM) : ");
        String monthA = s.nextLine();
        System.out.print("\t\t\tYear (YY) : ");
        String yearA = s.nextLine();
       
        //Input check-out date
        System.out.println("\n\t\tEnter departure / check-out date : ");
        System.out.print("\n\t\t\tDate (DD) : ");
        String dateD = s.nextLine();
        System.out.print("\t\t\tMonth (MM) : ");
        String monthD = s.nextLine();
        System.out.print("\t\t\tYear (YY) : ");
        String yearD = s.nextLine();
        
        //Uniting date, month, and year input into one String
        String dateBeforeString = yearA + "-" + monthA + "-" + dateA;
        String dateAfterString = yearD + "-" + monthD + "-" + dateD;
        
        //Get total days between two inputted dates.
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);
        
        long days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        if(days == 0){
            days = 1;
        }
        
        //Calculate the bill with multiplying room fee * total days
        long total = 0;
        String roomType = "";
        switch (type){
            case 1 : 
                total = 750000 * days; 
                roomType = "Standard Room";
                break;
            case 2 : 
                total = 1500000 * days; 
                roomType = "Deluxe Room";
                break;
            case 3 : 
                total = 2300000 * days;
                roomType = "Suite Room";
                break;
            case 4 : 
                total = 4500000 * days;
                roomType = "Presidential Suite Room";
                break;
        }
        
        //CHoosing payment method
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
        
        //Bundling all input data into one String
        String newLine = name + " - " + 
                         username + " - " + 
                         phone + " - " + 
                         roomType + " - " + 
                         dateBeforeString + " - " + 
                         dateAfterString + " - " + 
                         days + " - " +
                         paymentMethodString + " - " +
                         cardNumber + " - " +
                         "IDR " + payment;
        
        
        //Storing reservation data
        try {
            //Get txt file used as Hotel Reservation database
            URL loc = this.getClass().getResource("HotelReservation.txt");
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
    
    //Function to print all hotel reservation data.
    public void printData(){
        try {
            //Get txt file used as Hotel Reservation database
            URL loc = this.getClass().getResource("HotelReservation.txt");
            File file = new File(loc.getPath().toString());
            try (Scanner reader = new Scanner(file)) {
                int i = 1;
                while (reader.hasNextLine()) {
                    String data[] = reader.nextLine().split(" - ");
                    System.out.println("\n\t\tData " + (i));
                    System.out.println("\t\tName : " + data[0]);
                    System.out.println("\t\tUsername: " + data[1]);
                    System.out.println("\t\tPhone Number: " + data[2]);
                    System.out.println("\t\tReserved Room Type : " + data[3]);
                    System.out.println("\t\tReserved Date : " + data[4] + " until " + data[5]);
                    System.out.println("\t\tPayment Method : " + data[7]);
                    System.out.println("\t\tCard Number : " + data[8]);
                    System.out.println("\t\tTotal Bill : " + data[9]);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    
}
