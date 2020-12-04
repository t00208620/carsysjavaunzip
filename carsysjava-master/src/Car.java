//Car.java

/**
 * An instantiable class which defines a Car. This one contains exception-handling code in some
 * of its mutators to prevent bad input
 * @author Sean Courtney
 */

import java.io.Serializable;

/**
 * Car no-argument constructor. Calls the 7-argument Car constructor to initialise the
 * attributes of a Car object with some default initial values, to leave the Car
 * object in a consistent initial state
 */

public class Car implements Serializable {

    private String regNo;
    private String carClass;
    private String make;
    private String model;
    private String transmission;
    private String fuelType;
    private int occNo;

    /**
     * Book 4-argument constructor. Calls the 5 mutators and the incrementCount() method  to
     * initialise the attributes of a Book object with some user-supplied values. The Book ID is
     * set internally using the value of the count attribute, to ensure unique Book ID values.
     * @param regNo the title of the book
     * @param carClass the price of the book
     * @param make the ISBN of the book
     * @param model the number of pages in the book
     * @param transmission the number of pages in the book
     * @param fuelType the number of pages in the book
     * @param occNo the number of pages in the book
     */

    public Car(String regNo, String carClass, String make, String model, String transmission, String fuelType, int occNo) {
        setRegNo(regNo);
        setCarClass(carClass);
        setMake(make);
        setModel(model);
        setTransmission(transmission);
        setFuelType(fuelType);
        setOccNo(occNo);
    }

    /**
     * Gets the regNo of a car.
     * @return this cars regNo.
     */

    public String getRegNo() {
        return regNo;
    }

    /**
     * Method to set the title of a Book object
     * @param regNo the regNo of the Car
     */

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * Gets the carClass of a car.
     * @return this cars carClass.
     */

    public String getCarClass() {
        return carClass;
    }

    /**
     * Method to set the title of a Book object
     * @param carClass the carClass of the Car
     */

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    /**
     * Gets the make of a car.
     * @return this cars make.
     */

    public String getMake() {
        return make;
    }

    /**
     * Method to set the title of a Book object
     * @param make the make of the Car
     */

    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the model of a car.
     * @return this cars model
     */

    public String getModel() {
        return model;
    }

    /**
     * Method to set the title of a Book object
     * @param model the model of the Car
     */

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the transmission type of a car.
     * @return this cars transmission.
     */

    public String getTransmission() {
        return transmission;
    }

    /**
     * Method to set the title of a Book object
     * @param transmission the transmission of the Car
     */

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Gets the fuel type of a car.
     * @return this cars fuelType.
     */

    public String getFuelType() {
        return fuelType;
    }

    /**
     * Method to set the title of a Book object
     * @param fuelType the fuelType of the Car
     */

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Gets the Max Occupancy of a car.
     * @return this cars occupancy.
     */

    public int getOccNo() {
        return occNo;
    }

    /**
     * Method to set the title of a Book object
     * @param occNo the occNo of the Car
     */


    public void setOccNo(int occNo) {
        this.occNo = occNo;
    }


    public String toString() {
        return "\nregNo:" + getRegNo() + "\ncar class:" + getCarClass() + "\nmake:" + getMake() +
                "\nmodel:" + getModel() + "\ntransmission:" + getTransmission() +
                "\nfuel type:" + getFuelType() + "\noccNo:" + getOccNo();
    }
}
