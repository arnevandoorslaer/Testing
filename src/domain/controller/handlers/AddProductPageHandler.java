package domain.controller.handlers;

import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductPageHandler extends RequestHandler {
    public AddProductPageHandler(){

    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        return "addProduct.jsp";
    }
}
