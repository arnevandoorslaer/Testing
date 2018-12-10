package domain.controller.handlers;

import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonOverviewHandler extends RequestHandler {
    private ShopService service;

    public PersonOverviewHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String order = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("order")){
                    order = cookie.getValue();
                }
            }
        }
        request.setAttribute("persons", service.getPersons(order));
        request.setAttribute("headers", service.getHeaders());
        request.setAttribute("orderrr", order);
        return "personoverview.jsp";
    }
}
