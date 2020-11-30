import java.io.Serializable;

public class Car implements Serializable {

    private String regNo;
    private String carClass;
    private String make;
    private String model;
    private String transmission;
    private String fuelType;
    private int occNo;

    public Car(String regNo, String carClass, String make, String model, String transmission, String fuelType, int occNo) {
        setRegNo(regNo);
        setCarClass(carClass);
        setMake(make);
        setModel(model);
        setTransmission(transmission);
        setFuelType(fuelType);
        setOccNo(occNo);
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getOccNo() {
        return occNo;
    }

    public void setOccNo(int occNo) {
        this.occNo = occNo;
    }


    public String toString() {
        return "\nregNo:" + getRegNo() + "\ncar class:" + getCarClass() + "\nmake:" + getMake() +
                "\nmodel:" + getModel() + "\ntransmission:" + getTransmission() +
                "\nfuel type:" + getFuelType() + "\noccNo:" + getOccNo();
    }
}
