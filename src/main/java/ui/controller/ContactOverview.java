package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContactOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String showUniqueContacts = request.getParameter("value");
        List<Contact> contacts = null;

        if (showUniqueContacts == null || showUniqueContacts.equals("no")) {
            request.setAttribute("showUniqueContacts", "no");
            contacts = service.getAllContacts();
        } else {
            request.setAttribute("showUniqueContacts", "yes");
            contacts = service.getUniqueContact();
        }

        request.setAttribute("contacts", contacts);
        return "contactoverview.jsp";
    }

}
