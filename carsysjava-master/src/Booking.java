import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Booking implements Serializable {

    private String name;
    private String email;
    private String carClass;
    private int noOfDays;
    private GregorianCalendar collectDate;


    public Booking(String name, String email, String carClass, int noOfDays, GregorianCalendar collectDate) {
        setName(name);
        setEmail(email);
        setCarClass(carClass);
        setNoOfDays(noOfDays);
        setCollectDate(collectDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public GregorianCalendar getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(GregorianCalendar collectDate) {
        this.collectDate = collectDate;
    }

    public String toString() {
        String str = "\nName: " + getName() + "  \nEmail: " + getEmail() + " \nCar Class: " + getCarClass() + "\nNo Of Days: " + getNoOfDays() + "\n Collect Date: ";

        if (collectDate == null)
            str += "No collect date specified";
        else
            str += getCollectDate().get(Calendar.DATE) + "-" + (getCollectDate().get(Calendar.MONTH) + 1) +
                    "-" + getCollectDate().get(Calendar.YEAR);

        return str;
    }
}
