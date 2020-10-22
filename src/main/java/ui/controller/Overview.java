package ui.controller;

import domain.db.PersonService;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = service.getAll();
        //System.out.println("test88881");
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }
}
