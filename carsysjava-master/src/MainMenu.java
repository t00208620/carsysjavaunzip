import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class MainMenu extends JFrame implements ActionListener {

    private static ArrayList<Car> allCars;
    private static ArrayList<Booking> allBookings;


    JMenuBar menuBar;
    JMenu carsMenu;
    JMenu bookingsMenu;
    JMenu adminMenu;

    JMenuItem addCar;
    JMenuItem removeCar;
    JMenuItem updateCar;
    JMenuItem viewCars;

    JMenuItem makeBooking;
    JMenuItem changeBooking;
    JMenuItem cancelBooking;
    JMenuItem viewBookings;

    JMenuItem loginAdmin;
    JMenuItem quitAdmin;

    JPanel jp = new JPanel();
    JLabel jl = new JLabel();

    private static File file; //added by JB


    public MainMenu() throws IOException, ClassNotFoundException {

        super("Car Rental SYS");

        setIconImage(new ImageIcon("carsysjava-master\\src\\car3.jpg").getImage());

        setLayout(new GridBagLayout());
        setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(menuBar);

        jl.setIcon(new ImageIcon("carsysjava-master\\src\\car4.png"));
        jp.add(jl);
        add(jp);
        validate();

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.orange);


        carsMenu = new JMenu("Cars");
        carsMenu.setMnemonic(KeyEvent.VK_C);

        addCar = new JMenuItem("Add Car");
        addCar.setMnemonic(KeyEvent.VK_A);
        addCar.addActionListener(this);
        carsMenu.add(addCar);

        removeCar = new JMenuItem("Remove Car");
        removeCar.setMnemonic(KeyEvent.VK_R);
        removeCar.addActionListener(this);
        carsMenu.add(removeCar);


        updateCar = new JMenuItem("Update Car");
        updateCar.setMnemonic(KeyEvent.VK_U);
        updateCar.addActionListener(this);
        carsMenu.add(updateCar);

        viewCars = new JMenuItem("View Cars");
        viewCars.setMnemonic(KeyEvent.VK_V);
        viewCars.addActionListener(this);
        carsMenu.add(viewCars);


        bookingsMenu = new JMenu("Bookings");
        bookingsMenu.setMnemonic(KeyEvent.VK_B);

        makeBooking = new JMenuItem("Make Booking");
        makeBooking.setMnemonic(KeyEvent.VK_M);
        makeBooking.addActionListener(this);
        bookingsMenu.add(makeBooking);

        changeBooking = new JMenuItem("Change Booking");
        changeBooking.setMnemonic(KeyEvent.VK_H);
        changeBooking.addActionListener(this);
        bookingsMenu.add(changeBooking);

        cancelBooking = new JMenuItem("Cancel Booking");
        cancelBooking.setMnemonic(KeyEvent.VK_C);
        cancelBooking.addActionListener(this);
        bookingsMenu.add(cancelBooking);

        viewBookings = new JMenuItem("View Bookings");
        viewBookings.setMnemonic(KeyEvent.VK_V);
        viewBookings.addActionListener(this);
        bookingsMenu.add(viewBookings);

        adminMenu = new JMenu("Admin");
        adminMenu.setMnemonic(KeyEvent.VK_A);

        loginAdmin = new JMenuItem("Login");
        loginAdmin.setMnemonic(KeyEvent.VK_L);
        loginAdmin.addActionListener(this);
        adminMenu.add(loginAdmin);


        quitAdmin = new JMenuItem("Quit");
        quitAdmin.setMnemonic(KeyEvent.VK_Q);
        quitAdmin.addActionListener(this);
        adminMenu.add(quitAdmin);

        menuBar.add(adminMenu);


        menuBar.add(carsMenu);
        menuBar.add(bookingsMenu);

        setVisible(true);

        createFileMenu();

        readBookingsFromFile();
        readCarsFromFile();



    }

    public static void main(String[] args) throws Exception {

        new MainMenu();

        Runtime.getRuntime().exec("net user admin password");


        Car c1 = new Car("141-KY-6576", "Economy", "Toyota", "Yaris", "Manual", "Petrol", 4);
        Car c2 = new Car("142-KY-6576", "Economy", "Toyota", "Aygo", "Manual", "Petrol", 4);
        Car c3 = new Car("151-KY-6576", "Premium", "Opel", "Insignia", "Manual", "Petrol", 4);
        Car c4 = new Car("152-KY-6576", "Deluxe", "Volkswagon", "Passat", "Manual", "Diesel", 5);

        if (!file.exists())
            allCars = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

        Booking b1 = new Booking("John O Reilly", "john@gmail.com", "Economy", 1, new GregorianCalendar(2020, 11, 02));
        Booking b2 = new Booking("Clare Higgins", "clare@gmail.com", "Premium", 5, new GregorianCalendar(2020, 11, 28));
        Booking b3 = new Booking("Karen Anderson", "karen@gmail.com", "Deluxe", 3, new GregorianCalendar(2021, 10, 7));
        Booking b4 = new Booking("Sheila Foley", "sheila@gmail.com", "Premium", 2, new GregorianCalendar(2020, 5, 17));


        //JB - only set allBookings to this "prepopulated array-list if the file bookings.data doesn't already exist
        //You may need to do this type of thing also for the cars.data file
        if (!file.exists())
            allBookings = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));
    }

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

    public static void saveBookingsToFile() throws IOException {

        //put this code into a method called saveCarsToFile() and then call this when when a window-closing event occurs
        //create a private inner class called WindowEventHandler and have that class inherit from the WindowAdapter class
        //you'll override the windowClosing() method

        File outFile = new File("bookings.data");
        FileOutputStream outStream = new FileOutputStream(outFile);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

        for (Booking b : allBookings) {
            System.out.println(b);
        }

        objectOutStream.writeObject(allBookings); //in reality this writing would occur when you go to close the application
        objectOutStream.close();
        outStream.close();
    }

    public void createFileMenu() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        saveBookingsToFile();
                        saveCarsToFile();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Data saved successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);

                    System.exit(0);
                }
            }
        });
    }

    public void readBookingsFromFile() {

        try {

            file = new File("bookings.data");

            if (file.exists()) {

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allBookings = (ArrayList<Booking>) is.readObject();
                is.close();

                for (Booking b : allBookings) {
                    System.out.println(b);
                }

                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            } else {
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Class of object deserialised not a match for anything used in this application", "Error", JOptionPane.ERROR_MESSAGE);
            cnfe.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problem reading from the file", "Error", JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public static void saveCarsToFile() throws IOException {

        File outFile = new File("cars.data");
        FileOutputStream outStream = new FileOutputStream(outFile);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

        for (Car c : allCars) {
            System.out.println(c);
        }

        objectOutStream.writeObject(allCars); //in reality this writing would occur when you go to close the application
        objectOutStream.close();
        outStream.close();

    }


    public void readCarsFromFile() {
        //put this code into a method called readCarsFromFile() and then call this when whe the application launches

        try {

            file = new File("cars.data");

            if (file.exists()) {

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allCars = (ArrayList<Car>) is.readObject();
                is.close();

                for (Car c : allCars) {
                    System.out.println(c);
                }

                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            } else {
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Class of object deserialised not a match for anything used in this application", "Error", JOptionPane.ERROR_MESSAGE);
            cnfe.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problem reading from the file", "Error", JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public static void viewCars(ArrayList<Car> allCars) {

        String allCarData = "";
        Car cr;
        Iterator<Car> iterator = allCars.iterator();
        while (iterator.hasNext()) {
            cr = iterator.next();
            if (cr != null)
                allCarData += cr + "\n";
        }
        JOptionPane.showMessageDialog(null, allCarData,
                "List of all Bookings", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void viewBookings(ArrayList<Booking> allBookings) {

        JComboBox bookingsCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Booking Info:\n");
        output.setBackground(Color.orange);

        if (allBookings.size() < 1) {
            JOptionPane.showMessageDialog(null, "No bookings are on the system yet. Add Bookings please", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Iterator<Booking> iterator = allBookings.iterator();

            while (iterator.hasNext()) {
                bookingsCombo.addItem(iterator.next().getName() + "\n");
            }

            JOptionPane.showMessageDialog(null, bookingsCombo, "Select Customer to view booking details", JOptionPane.PLAIN_MESSAGE);

            int selected = bookingsCombo.getSelectedIndex();
            output.append(allBookings.get(selected).toString());

            JOptionPane.showMessageDialog(null, output, "Booking Details", JOptionPane.PLAIN_MESSAGE);
        }



    }




    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCar) {
            addCar(allCars);
        }
        if (e.getSource() == removeCar) {
            cancelCar(allCars);
        }
        if (e.getSource() == updateCar) {
            changeCar(allCars);
        }
        if (e.getSource() == viewCars) {
            viewCars(allCars);
        }





        if (e.getSource() == makeBooking) {
            addBooking(allBookings);
        }

        if (e.getSource() == changeBooking) {
            changeBooking(allBookings);

        }
        if (e.getSource() == cancelBooking) {
            cancelBooking(allBookings);

        }

        if (e.getSource() == viewBookings) {
            viewBookings(allBookings);
        }
        if (e.getSource() == loginAdmin)
        {
            String username="sean";
            String password="123";

            username = JOptionPane.showInputDialog("Enter username: (sean)");
            password = JOptionPane.showInputDialog("Enter password: (1234)");


            if (username.equals("sean") && password.equals("1234")) {
                JOptionPane.showMessageDialog(null, "Access Granted! Welcome!");
            }
            else if ((!username.equals("sean")) && (!password.equals("1234"))){
                JOptionPane.showMessageDialog(null, "Invalid Username & Password!");
            }
            else if (!username.equals("sean")) {
                JOptionPane.showMessageDialog(null, "Invalid username!");

            } else if (!password.equals("1234")) {
                JOptionPane.showMessageDialog(null, "Invalid password!");
            }
        }



        if (e.getSource() == quitAdmin) {
            System.exit(0);
        }



    }


    public static void addCar(ArrayList<Car> allCarsList) {

        String regNo = "", carClass = "", make = "", model = "", transmission = "", fuelType = "", occNoAsString = "";

        regNo = JOptionPane.showInputDialog("Please enter the Registration Number");
        carClass = JOptionPane.showInputDialog("Please enter the car class, (economy,premium,deluxe)");
        make = JOptionPane.showInputDialog("Please enter the cars Make");
        model = JOptionPane.showInputDialog("Please enter the Model of the Car");
        fuelType = JOptionPane.showInputDialog("Please enter the cars fuel type");
        transmission = JOptionPane.showInputDialog("Please enter the transmission type");
        occNoAsString = JOptionPane.showInputDialog("Please enter the max Occupancy");
        int occNo = Integer.parseInt(occNoAsString);

        Car c5 = new Car(regNo, carClass, make, model, transmission, fuelType, occNo);

        allCarsList.add(c5);

        JOptionPane.showMessageDialog(null, "The following Car details has been added to the system:" + "\n" + regNo +
                "\n" + carClass + "\n" + make + "\n" + model + "\n" + transmission +
                "\n" + fuelType + "\n" + occNoAsString);
    }

    public static void addBooking(ArrayList<Booking> allBookingsList) {

        String name = "", email = "", carClass = "", noOfDaysAsString = "", collectDate = "";
        int i = 0;

        name = JOptionPane.showInputDialog("Enter the customers name");
        email = JOptionPane.showInputDialog("Enter the customers email address");
        carClass = JOptionPane.showInputDialog("Enter the Class of Car wanted (Premium,Economy,Deluxe)");
        noOfDaysAsString = JOptionPane.showInputDialog("Enter the Number Of Days the car is to be rented for");
        collectDate = JOptionPane.showInputDialog("Enter the date the car is to be collected (DD-MM-YYYY)");
        int noOfDays = Integer.parseInt(noOfDaysAsString);
        int day = Integer.parseInt(collectDate.substring(0, 2));
        int month = Integer.parseInt(collectDate.substring(3, 5));
        int year = Integer.parseInt(collectDate.substring(6, 10));

        Booking b5 = new Booking(name, email, carClass, noOfDays, new GregorianCalendar(year, month - 1, day));

        allBookingsList.add(b5);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        JOptionPane.showMessageDialog(null, "The following Booking details has been added to the system:" +
                "\n" + name + "\n" + email + "\n" + carClass + "\n" + noOfDaysAsString +
                "\n" + dateFormatter.format(allBookings.get(i).getCollectDate().getTime()));
    }

    public static void cancelBooking(ArrayList<Booking> cancelList) {

        String name = "", answer = "";
        int i = 0;
        if (i == allBookings.size())
            JOptionPane.showMessageDialog(null, "That name could not be found!");

        name = JOptionPane.showInputDialog("Name of customer you would like to remove: ");


        for (i = 0; i < allBookings.size(); i++)
            if (allBookings.get(i).getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Found " + name + "!");


                JOptionPane.showMessageDialog(null, "The current booking is:" + allBookings.get(i));
                answer = JOptionPane.showInputDialog("To remove this Customer, enter yes: ");


                if (answer.equalsIgnoreCase("yes")) {
                    allBookings.remove(allBookings.get(i));
                    i--;
                }

                break;
            }
    }

    public void changeBooking(ArrayList<Booking> changeList) {

        String name = "", email = "", carClass="", noOfDaysAsString="";
        int i;
        GregorianCalendar collectDate;

        for (i = 0; i < allBookings.size(); i++)
            if (allBookings.get(i).getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null,"Found " + name + "!");
                break;
            }

        if (i == allBookings.size())

            name = JOptionPane.showInputDialog("Please enter the name of the person whose address you wish to amend: ");


        {
            JOptionPane.showMessageDialog(null, "That name could not be found!");

        }



        for (i = 0; i < allBookings.size(); i++)
            if (allBookings.get(i).getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null,"Found " + name + "!");

                JOptionPane.showMessageDialog(null,"The current email for this person is " + allBookings.get(i).getEmail());
                email = JOptionPane.showInputDialog("Enter the new email for this person: ");
                allBookings.get(i).setEmail(email);


                JOptionPane.showMessageDialog(null,"The current car class this person wants is " + allBookings.get(i).getCarClass());
                carClass = JOptionPane.showInputDialog("Enter the new class of car for this person: ");
                allBookings.get(i).setCarClass(carClass);


                JOptionPane.showMessageDialog(null,"The no of days this person wants the car for is " + allBookings.get(i).getNoOfDays());
                noOfDaysAsString = JOptionPane.showInputDialog("Enter how many days the customer now wants the car for: ");
                int noOfDays = Integer.parseInt(noOfDaysAsString);
                allBookings.get(i).setNoOfDays(noOfDays);



                JOptionPane.showMessageDialog(null,"Displaying updated state of this Booking \n" + allBookings.get(i));

                break;

            }
    }








    public static void cancelCar(ArrayList<Car> cancelList) {

        String regNo = "", answer = "";
        int i = 0;
        if (i == allCars.size())
            JOptionPane.showMessageDialog(null, "Registration number could not be found!");

        regNo = JOptionPane.showInputDialog("Registration Number you would like to remove:  (142or151or152-KY-6576)");


        for (i = 0; i < allCars.size(); i++)
            if (allCars.get(i).getRegNo().equalsIgnoreCase(regNo)) {
                JOptionPane.showMessageDialog(null, "Found " + regNo+ "!");


                JOptionPane.showMessageDialog(null, "The current booking is:" + allCars.get(i));
                answer = JOptionPane.showInputDialog("To remove this Car, enter yes: ");


                if (answer.equalsIgnoreCase("yes")) {
                    allCars.remove(allCars.get(i));
                    i--;
                }

                break;
            }
    }

    public void changeCar(ArrayList<Car> allCarsList) {

        String regNo = "", carClass="", make="", model="", transmission="", fuelType="";
        int i;


        for (i = 0; i < allCars.size(); i++)
            if (allCars.get(i).getRegNo().equalsIgnoreCase(regNo)) {
                JOptionPane.showMessageDialog(null,"Found " + regNo + "!");
                break;
            }

        if (i == allCars.size())

            regNo = JOptionPane.showInputDialog("Please enter the Registration Number of the car you wish to amend: (viewCarList-KY-6576)");

        {
            JOptionPane.showMessageDialog(null, "That Registration Number could not be found!");
        }



        for (i = 0; i < allCars.size(); i++)
            if (allCars.get(i).getRegNo().equalsIgnoreCase(regNo)) {
                JOptionPane.showMessageDialog(null,"Found " + regNo + "!");


                JOptionPane.showMessageDialog(null,"The current manufacturer of this car is " + allCars.get(i).getMake());
                make = JOptionPane.showInputDialog("Enter the new manufacturer for this car: ");
                allCars.get(i).setMake(make);

                JOptionPane.showMessageDialog(null,"The current model of this cars make is " + allCars.get(i).getModel());
                model = JOptionPane.showInputDialog("Please update the model of this make: ");
                allCars.get(i).setModel(model);

                JOptionPane.showMessageDialog(null,"The current class title for this car is " + allCars.get(i).getCarClass());
                carClass = JOptionPane.showInputDialog("Enter the new class title for this car: ");
                allCars.get(i).setCarClass(carClass);

                JOptionPane.showMessageDialog(null,"The current transmission type of this car is " + allCars.get(i).getTransmission());
                transmission = JOptionPane.showInputDialog("Enter the new transmission of this car: ");
                allCars.get(i).setTransmission(transmission);

                JOptionPane.showMessageDialog(null,"The current fuel type for this car is " + allCars.get(i).getFuelType());
                fuelType = JOptionPane.showInputDialog("Enter the new fuel type of this car: ");
                allCars.get(i).setFuelType(fuelType);


                JOptionPane.showMessageDialog(null,"The max occupancy of this car is " + allCars.get(i).getOccNo());
                String occNoAsString = JOptionPane.showInputDialog("Enter the new max occupancy of this car: ");
                int occNo = Integer.parseInt(occNoAsString);
                allCars.get(i).setOccNo(occNo);



                JOptionPane.showMessageDialog(null,"Displaying updated state of this Car \n" + allCars.get(i));

                break;

            }
    }
}

















