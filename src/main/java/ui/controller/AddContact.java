package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = new Contact();
        ArrayList<String> errors = new ArrayList<>();
        setFirstName(request, contact, errors);
        setLastName(request, contact, errors);
        setDate(request, contact, errors);
        setHour(request, contact, errors);
        setGsm(request, contact, errors);
        setEmail(request, contact, errors);

        if (errors.size() == 0) {
            try {
                service.addContact(contact);
                request.removeAttribute("firstNamePreviousValue");
                request.removeAttribute("lastNamePreviousValue");
                request.removeAttribute("datePreviousValue");
                request.removeAttribute("hourPreviousValue");
                request.removeAttribute("gsmPreviousValue");
                request.removeAttribute("emailPreviousValue");
                return "Controller?action=ContactOverview";
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?action=ContactOverview";
    }

    public void setFirstName(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String firstName = request.getParameter("firstName");
            contact.setFirstname(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    public void setLastName(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String lastName = request.getParameter("lastName");
            contact.setLastname(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    public void setDate(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String date = request.getParameter("date");
            if (date.isEmpty()) {
                throw new IllegalArgumentException("No valid date given");
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            contact.setDate(localDate);
            request.setAttribute("datePreviousValue", date);
        } catch (Exception e) {
            errors.add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void setHour(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String hour = request.getParameter("hour");
            if (hour.isEmpty()) {
                throw new IllegalArgumentException("No valid time given");
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(hour, dateTimeFormatter);
            contact.setHour(localTime);
            System.out.println(hour);
            request.setAttribute("hourPreviousValue", hour);
        } catch (Exception e) {
            errors.add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void setGsm(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String gsm = request.getParameter("gsm");
            contact.setGsm(gsm);
            request.setAttribute("gsmPreviousValue", gsm);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    public void setEmail(HttpServletRequest request, Contact contact, ArrayList<String> errors) {
        try {
            String email = request.getParameter("email");
            contact.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
