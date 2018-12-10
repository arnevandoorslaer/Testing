package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonOrderHandler extends RequestHandler {
    private ShopService service;
    public PersonOrderHandler(ShopService serv){
        service = serv;
    }
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String order = request.getParameter("order");
        Cookie cookie = new Cookie("order", order);
        response.addCookie(cookie);
        return new PersonOverviewHandler(service).handleRequest(request, response);
    }

}
