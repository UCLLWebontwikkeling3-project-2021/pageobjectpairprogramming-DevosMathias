package domain.model;

import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private LocalDate date;
    private LocalTime hour;
    private String gsm;
    private String email;

    public Contact(String firstName, String lastName, LocalDate date, LocalTime hour, String gsm, String email) {
        setFirstname(firstName);
        setLastname(lastName);
        setDate(date);
        setHour(hour);
        setGsm(gsm);
        setEmail(email);
    }

    public Contact() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No first name given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastname(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        if (this.date.equals(LocalDate.now() )&& hour.isAfter(LocalTime.now())) {
            throw new IllegalArgumentException("Time cannot be in the future");
        }
        this.hour = hour;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        if (gsm.isEmpty()) {
            throw new IllegalArgumentException("No gsm given");
        }
        String USERID_PATTERN = "^(\\+32|\\+31)+[0-9]{10}";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(gsm);
        if (!m.matches()) {
            throw new IllegalArgumentException("Gsm not valid");
        }

        System.out.println("test");
        this.gsm = gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }
}
