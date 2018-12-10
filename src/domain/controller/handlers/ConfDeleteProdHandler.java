package domain.controller.handlers;

import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfDeleteProdHandler extends RequestHandler {
    private ShopService service;

    public ConfDeleteProdHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        request.setAttribute("product", service.getProduct(id));
        return "deleteProduct.jsp";
    }
}
