package domain.controller.handlers;
import domain.controller.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuoteHandler extends RequestHandler {

    public QuoteHandler(){

    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String choice = request.getParameter("choice");
        Cookie cookie = new Cookie("quote", choice);
        response.addCookie(cookie);
        return new HomeHandler().handleRequest(request, response);
    }


}
