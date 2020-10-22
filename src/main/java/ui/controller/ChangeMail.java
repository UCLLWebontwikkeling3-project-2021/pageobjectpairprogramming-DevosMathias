package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMail extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String newMail = request.getParameter("newMail");
        String error;
        try {
            System.out.println("test5554");
            this.service.changeMail(userid, newMail);
            return "index.jsp";
        } catch (Exception e) {
            error = e.getMessage();
        }

        request.setAttribute("error", error);
        request.setAttribute("userid", userid);
        return "changeMailForm.jsp";
    }
}
