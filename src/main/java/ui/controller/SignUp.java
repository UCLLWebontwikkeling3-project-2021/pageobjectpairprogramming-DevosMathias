package ui.controller;

import domain.model.Person;
import sun.applet.AppletResourceLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        ArrayList<String> errors = new ArrayList<>();
        setUserId(request, person, errors);
        setEmail(request, person, errors);
        setPassword(request, person, errors);
        setFirstName(request, person, errors);
        setLastName(request, person, errors);

        if (errors.size() == 0) {
            try {
                service.addPerson(person);
                return "index.jsp";
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "register.jsp";
    }

    private void setUserId(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            String userId = request.getParameter("userid").toLowerCase();
            person.setUserid(userId);
            request.setAttribute("userIdPreviousValue", userId);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setEmail(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            String email = request.getParameter("email");
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPassword(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            String password = request.getParameter("password");
            //password zal hier gehashed worden zodat het onleesbaar in de databank komt
            person.setPasswordHashed(password);
            request.setAttribute("passwordPreviousValue", password);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setFirstName(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            String firstName = request.getParameter("firstName");
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setLastName(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            String lastName = request.getParameter("lastName");
            person.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
