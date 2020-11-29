import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainMenu extends JFrame implements ActionListener {


     //private static Car[] allCars; //change this so that it will be an array-list

    private static ArrayList<Car> allCars;

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

        File outFile = new File("cars.data");

        FileOutputStream outStream = new FileOutputStream(outFile);

        new MainMenu();

        Car c1 = new Car("141-KY-6576", "Economy", "Toyota", "Yaris", "Manual", "Petrol", 4);
        Car c2 = new Car("142-KY-6576", "Economy", "Toyota", "Aygo", "Manual", "Petrol", 4);
        Car c3 = new Car("151-KY-6576", "Premium", "Opel", "Insignia", "Manual", "Petrol", 4);
        Car c4 = new Car("152-KY-6576", "Deluxe", "Volkswagon", "Passat", "Manual", "Diesel", 5);

        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

        allCars = new ArrayList<>(Arrays.asList(c1,c2,c3,c4));

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

        objectOutStream.writeObject(allCars); //in reality this writing would occur when you go to close the application

        outStream.close();

        File inFile = new File("cars.data");
        FileInputStream inStream = new FileInputStream(inFile);

        ObjectInputStream objectInStream = new ObjectInputStream(inStream);

        allCars = (ArrayList<Car>) objectInStream.readObject();//in reality this reading would occur when the application is launched

        JOptionPane.showMessageDialog(null, "Cars details are:\n\n" + c1 + "\n" +
                        c2 + "\n" + c3 + "\n" + c4,
                "Output from Car File", JOptionPane.INFORMATION_MESSAGE);

        inStream.close();
}

    public static void viewCars(ArrayList<Car> allCars) {
        String allCarsData = "";
        Car cr;
        Iterator<Car> iterator = allCars.iterator();
        while (iterator.hasNext()) {
            cr = iterator.next();
            if (cr != null)
                allCarsData += cr + "\n";
        }
        JOptionPane.showMessageDialog(null, allCarsData,
                "List of all Cars", JOptionPane.INFORMATION_MESSAGE);
    }




    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == carItem1) {
            addCar(allCars);
        }
        if (e.getSource() == carItem2) {
            JOptionPane.showMessageDialog(null,"Good");
        }
        if (e.getSource() == carItem3) {
            JOptionPane.showMessageDialog(null,"Good");
        }
        if (e.getSource() == carItem4) {
           viewCars(allCars);
        }

        if (e.getSource() == bookItem1) {
            JOptionPane.showMessageDialog(null,"bgd");
        }

        if (e.getSource() == bookItem2) {
            JOptionPane.showMessageDialog(null,"Goofsd");
        }

        if (e.getSource() == bookItem3) {
            JOptionPane.showMessageDialog(null,"Good");
        }

        if (e.getSource() == adminItem1) {
            JOptionPane.showMessageDialog(null,"Good");
        }
    }

    public static void addCar(ArrayList<Car> allCarsList){

            String regNo = JOptionPane.showInputDialog("Please enter the Registration Number");
            String carClass = JOptionPane.showInputDialog("Please enter the car class, (economy,premium,deluxe)");
            String make = JOptionPane.showInputDialog("Please enter the cars Make");
            String model = JOptionPane.showInputDialog("Please enter the Model of the Car");
            String fuelType = JOptionPane.showInputDialog("Please enter the cars fuel type");
            String transmission = JOptionPane.showInputDialog("Please enter the transmission type");
            String occNoAsString = JOptionPane.showInputDialog("Please enter the max Occupancy");

            int occNo = Integer.parseInt(occNoAsString);

            Car c6 = new Car(regNo,carClass,make,model,fuelType,transmission,occNo);

            allCarsList.add(c6);
            JOptionPane.showMessageDialog(null,"The following Car details has been added to the system:" + "\n"+ regNo +
                "\n" + carClass + "\n" + make + "\n" + model + "\n" + fuelType +
                "\n" + transmission+ "\n" + occNoAsString);

}
    }













