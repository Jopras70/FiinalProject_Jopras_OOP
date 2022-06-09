package reservation.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

// Implementing User Interface
public class Admin implements User{
    String name;
    String username;
    String password;

    
    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Function to handle login for admin
    @Override
    public boolean login(String username, String password) {
        try {
            URL loc = this.getClass().getResource("Admin.txt");
            File file = new File(loc.getPath().toString());
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    String data[] = reader.nextLine().split(" - ");
                    if(data[1].equals(username) && data[2].equals(password)){
                        this.name = data[0];
                        this.username = username;
                        this.password = password;
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        return false;
    }

    // Function to handle updating admin data
    @Override
    public void update(String name, String username, String password) {
        try {
            URL loc = this.getClass().getResource("Admin.txt");
            File file = new File(loc.getPath().toString());
            String fileContents;
            try (Scanner reader = new Scanner(file)) {
                StringBuilder buffer = new StringBuilder();
                while(reader.hasNextLine()) {
                    buffer.append(reader.nextLine()).append(System.lineSeparator());
                }   fileContents = buffer.toString();
            }
            String oldLine = this.name + " - " + this.username + " - " + this.password;
            String newLine = name + " - " + username + " - " + password;
            fileContents = fileContents.replaceAll(oldLine, newLine);
            try{
                FileWriter writer = new FileWriter(loc.getPath().toString());
                writer.append(fileContents);
                writer.flush();
            } catch (IOException e) {
                System.out.print("An error occurred.");
            }
        } catch (FileNotFoundException e) {
            System.out.print("An error occurred.");
        }
    }
    
    // Function to handle adding new admin data
    @Override
    public void add(String name, String username, String password) {
        String newLine = name + " - " + username + " - " + password;
        try {
            URL loc = this.getClass().getResource("Admin.txt");
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
                System.out.print("An error occurred.");
            }
        } catch (Exception e) {
            System.out.print("An error occurred.");
        }
    }
    
    //Function to print menu for admin
    @Override
    public void printMenu(){
        System.out.println("\n\tMENU : " + 
                "\n\t1. Update Profile Data" +
                "\n\t2. See Hotel Reservation Transaction Data" + 
                "\n\t3. See Spa Reservation Transaction Data" + 
                "\n\t4. Logout");
    }
    
    
}
