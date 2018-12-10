package domain.controller.handlers;

import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpHandler extends RequestHandler {
    public SignUpHandler(){

    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        return "signUp.jsp";
    }
}
