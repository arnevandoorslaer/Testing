package domain.controller.handlers;

import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckRedirectHandler extends RequestHandler {

    public CheckRedirectHandler(){

    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "checkPassword.jsp";
    }
}
