package domain.controller.handlers;

import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductOverviewHandler extends RequestHandler {
    private ShopService service;

    public ProductOverviewHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("products", service.getProducts());
        return "products.jsp";
    }
}
