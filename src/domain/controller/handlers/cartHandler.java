package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class cartHandler extends RequestHandler {
    ShopService service;
    public cartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("service", service);
        return "cart.jsp";
    }

    }
