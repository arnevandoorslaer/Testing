package domain.controller.handlers;

import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutHandler extends RequestHandler {

    public LogoutHandler(){

    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("person");
        request.getSession().removeAttribute("role");
        return new HomeHandler().handleRequest(request,response);
    }
}
