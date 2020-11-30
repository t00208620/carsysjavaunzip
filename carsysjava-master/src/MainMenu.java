import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class MainMenu extends JFrame implements ActionListener {


    //private static Car[] allCars; //change this so that it will be an array-list

    private static ArrayList<Car> allCars;
    private static ArrayList<Booking> allBookings;

    JMenuBar menuBar;
    JMenu carsMenu;
    JMenu bookingsMenu;
    JMenu adminMenu;

    JMenuItem carItem1;
    JMenuItem carItem2;
    JMenuItem carItem3;
    JMenuItem carItem4;

    JMenuItem bookItem1;
    JMenuItem bookItem2;
    JMenuItem bookItem3;
    JMenuItem bookItem4;
    JMenuItem adminItem1;

    JPanel jp = new JPanel();
    JLabel jl = new JLabel();


    public MainMenu() {

        super("Car Rental SYS");

        setIconImage(new ImageIcon("./src/car3.jfif").getImage());

        setLayout(new GridBagLayout());
        setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(menuBar);

        jl.setIcon(new ImageIcon("C:\\Users\\sean\\Desktop\\carsys\\car4.png"));
        jp.add(jl);
        add(jp);
        validate();

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.orange);


        carsMenu = new JMenu("Cars");
        carsMenu.setMnemonic(KeyEvent.VK_C);

        carItem1 = new JMenuItem("Add Car");
        carItem1.setMnemonic(KeyEvent.VK_A);
        carItem1.addActionListener(this);
        carsMenu.add(carItem1);

        carItem2 = new JMenuItem("Remove Car");
        carItem2.setMnemonic(KeyEvent.VK_R);
        carItem2.addActionListener(this);
        carsMenu.add(carItem2);


        carItem3 = new JMenuItem("Update Car");
        carItem3.setMnemonic(KeyEvent.VK_U);
        carItem3.addActionListener(this);
        carsMenu.add(carItem3);

        carItem4 = new JMenuItem("View Cars");
        carItem4.setMnemonic(KeyEvent.VK_V);
        carItem4.addActionListener(this);
        carsMenu.add(carItem4);


        bookingsMenu = new JMenu("Bookings");
        bookingsMenu.setMnemonic(KeyEvent.VK_B);

        bookItem1 = new JMenuItem("Make Booking");
        bookItem1.setMnemonic(KeyEvent.VK_M);
        bookItem1.addActionListener(this);
        bookingsMenu.add(bookItem1);

        bookItem2 = new JMenuItem("Change Booking");
        bookItem2.setMnemonic(KeyEvent.VK_H);
        bookItem2.addActionListener(this);
        bookingsMenu.add(bookItem2);

        bookItem3 = new JMenuItem("Cancel Booking");
        bookItem3.setMnemonic(KeyEvent.VK_C);
        bookItem3.addActionListener(this);
        bookingsMenu.add(bookItem3);

        bookItem4 = new JMenuItem("View Bookings");
        bookItem4.setMnemonic(KeyEvent.VK_V);
        bookItem4.addActionListener(this);
        bookingsMenu.add(bookItem4);


        adminMenu = new JMenu("Admin");
        adminMenu.setMnemonic(KeyEvent.VK_A);

        adminItem1 = new JMenuItem("Login");
        adminItem1.setMnemonic(KeyEvent.VK_L);
        adminItem1.addActionListener(this);
        adminMenu.add(adminItem1);


        menuBar.add(carsMenu);
        menuBar.add(bookingsMenu);
        menuBar.add(adminMenu);

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {

        new MainMenu();

        Car c1 = new Car("141-KY-6576", "Economy", "Toyota", "Yaris", "Manual", "Petrol", 4);
        Car c2 = new Car("142-KY-6576", "Economy", "Toyota", "Aygo", "Manual", "Petrol", 4);
        Car c3 = new Car("151-KY-6576", "Premium", "Opel", "Insignia", "Manual", "Petrol", 4);
        Car c4 = new Car("152-KY-6576", "Deluxe", "Volkswagon", "Passat", "Manual", "Diesel", 5);

        allCars = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

        Booking b1 = new Booking("John O Reilly", "john@gmail.com", "Economy", 1, new GregorianCalendar(2020, 11, 02));
        Booking b2 = new Booking("Clare Higgins", "clare@gmail.com", "Premium", 5, new GregorianCalendar(2020, 11, 28));
        Booking b3 = new Booking("Karen Anderson", "karen@gmail.com", "Deluxe", 3, new GregorianCalendar(2021, 10, 7));
        Booking b4 = new Booking("Sheila Foley", "sheila@gmail.com", "Premium", 2, new GregorianCalendar(2020, 5, 17));

        allBookings = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));

/*****************************************************
 *     Code from lecturer John Brosnan
 *    Title:   MainMenu.java (verious lines including array of cars)
 *    Author:  John Brosnan
 *    Site owner/sponsor:  not available
 *    Date: 24/11/2020
 *    Code version:  not applicable
 *    Availability:  not applicable
 *    Modified:  Code refactore
 *****************************************************/

        File outFile = new File("cars.data");
        FileOutputStream outStream = new FileOutputStream(outFile);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
        objectOutStream.writeObject(allCars); //in reality this writing would occur when you go to close the application
        outStream.close();

        File inFile = new File("cars.data");
        FileInputStream inStream = new FileInputStream(inFile);
        ObjectInputStream objectInStream = new ObjectInputStream(inStream);
        allCars = (ArrayList<Car>) objectInStream.readObject();//in reality this reading would occur when the application is launched
        inStream.close();

        objectOutStream.writeObject(allBookings);
        allBookings = (ArrayList<Booking>) objectInStream.readObject();
        inStream.close();
    }

    public static void viewCars(ArrayList<Car> allCars) {

        JComboBox carsCombo = new JComboBox();
        JTextArea output = new JTextArea();

        if (allCars.size() < 1) {
            JOptionPane.showMessageDialog(null, "No Cars added yet", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            Iterator<Car> iterator = allCars.iterator();

            while (iterator.hasNext()) {
                carsCombo.addItem(iterator.next().getRegNo() + "\n");
            }

            JOptionPane.showMessageDialog(null, carsCombo, "Select a Registration Number to view car", JOptionPane.PLAIN_MESSAGE);

            int selected = carsCombo.getSelectedIndex();
            output.append(allCars.get(selected).toString());

            JOptionPane.showMessageDialog(null, output, "Car Details", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void viewBookings(ArrayList<Booking> allBookings) {

        String allBookingData = "";
        Booking br;
        Iterator<Booking> iterator = allBookings.iterator();
        while (iterator.hasNext()) {
            br = iterator.next();
            if (br != null)
                allBookingData += br + "\n";
        }
        JOptionPane.showMessageDialog(null, allBookingData,
                "List of all Cars", JOptionPane.INFORMATION_MESSAGE);
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == carItem1) {
            addCar(allCars);
        }
        if (e.getSource() == carItem2)

        if (e.getSource() == carItem3)

        if (e.getSource() == carItem4) {
            viewCars(allCars);
        }

        if (e.getSource() == bookItem1) {
            addBooking(allBookings);
        }

        if (e.getSource() == bookItem2) {

        }
        if (e.getSource() == bookItem3) {

        }

        if (e.getSource() == bookItem4) {
            viewBookings(allBookings);
        }

        if (e.getSource() == adminItem1) {

        }
    }

    public static void addCar(ArrayList<Car> allCarsList) {

        String regNo = "", carClass = "", make = "", model = "", fuelType = "", transmission = "", occNoAsString = "";

        regNo = JOptionPane.showInputDialog("Please enter the Registration Number");
        carClass = JOptionPane.showInputDialog("Please enter the car class, (economy,premium,deluxe)");
        make = JOptionPane.showInputDialog("Please enter the cars Make");
        model = JOptionPane.showInputDialog("Please enter the Model of the Car");
        fuelType = JOptionPane.showInputDialog("Please enter the cars fuel type");
        transmission = JOptionPane.showInputDialog("Please enter the transmission type");
        occNoAsString = JOptionPane.showInputDialog("Please enter the max Occupancy");
        int occNo = Integer.parseInt(occNoAsString);

        Car c5 = new Car(regNo, carClass, make, model, fuelType, transmission, occNo);

        allCarsList.add(c5);

        JOptionPane.showMessageDialog(null, "The following Car details has been added to the system:" + "\n" + regNo +
                "\n" + carClass + "\n" + make + "\n" + model + "\n" + fuelType +
                "\n" + transmission + "\n" + occNoAsString);
    }

    public static void addBooking(ArrayList<Booking> allBookingsList) {

        String name = "", email = "", carClass = "", noOfDaysAsString = "", collectDate = "";

        name = JOptionPane.showInputDialog("Enter the customers name");
        email = JOptionPane.showInputDialog("Enter the customers email address");
        carClass = JOptionPane.showInputDialog("Enter the Class of Car wanted (Premium,economy or deluxe)");
        noOfDaysAsString = JOptionPane.showInputDialog("Enter the Number Of Days the car is to be rented for");
        collectDate = JOptionPane.showInputDialog("Enter the date the car is to be collected (YYYY-MM-DD)");
        int noOfDays = Integer.parseInt(noOfDaysAsString);
        int day = Integer.parseInt(collectDate.substring(0, 2));
        int month = Integer.parseInt(collectDate.substring(3, 5));
        int year = Integer.parseInt(collectDate.substring(6, 10));

        Booking b5 = new Booking(name, email, carClass, noOfDays, new GregorianCalendar(year, month - 1, day));

        allBookingsList.add(b5);

        JOptionPane.showMessageDialog(null, "The following Booking details has been added to the system:" +
                "\n" + name + "\n" + email + "\n" + carClass + "\n" + noOfDaysAsString +
                "\n" + collectDate);
    }
}
















