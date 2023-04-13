//package LogIn;
package Prototype;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        String Username;
        String NewUser;
        String Password;
        String NewPass;
        String st;
        File UserData = new File("UserData.txt");
        BufferedReader br = new BufferedReader(new FileReader(UserData));
        List arrList = new ArrayList<String>();
        BufferedWriter brw = new BufferedWriter(new FileWriter(UserData, true));




            Scanner input3 = new Scanner(System.in);

        System.out.println("Existing User? Type yes or no. "); // later would be implimented as button by the JavaFX personnel
        String UserType = input3.next();


            if (UserType.equals("yes")) {
                authenticate();
            } else {
               createUser();

            }
        }


    public static void authenticate() throws IOException {
        File UserData = new File("UserData.txt"); //currently using a txt file to store user data once the SQL database has been established this will be updated
        BufferedReader br = new BufferedReader(new FileReader(UserData));
        String st = br.readLine();
        Scanner scanner = new Scanner(new File("UserData.txt"));

        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter Username : ");
        String username = input1.next();
        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter Password : ");
        String password = input2.next();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                if(username.equals(br.readLine())){
                    if(password.equals(br.readLine())) {
                        System.out.println("Access Granted");
                    }
                    else{
                        System.out.println("Access Denied");
                    }
                }

                }

        } finally {
            br.close();
        }

        }
    public static void createUser() throws IOException {
        File UserData = new File("/Users/dhruvjain/Documents/CSE 360/UserData.txt");
        BufferedWriter brw = new BufferedWriter(new FileWriter(UserData, true));
        Scanner input4 = new Scanner(System.in);
        System.out.println("Create a  Username : ");
       String NewUser = input4.next();
        brw.write(NewUser + System.lineSeparator());
        brw.flush();


        Scanner input5 = new Scanner(System.in);
        System.out.println("Create a Password : ");
       String NewPass = input5.next();
        brw.write(NewPass + System.lineSeparator());
        brw.close();

        authenticate();
    }
    }






        // Press ⌃R or click the green arrow button in the gutter to run the code.



