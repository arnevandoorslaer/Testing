package domain.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateHandler extends RequestHandler {
    private ShopService service;

    public UpdateHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Product p = service.getProduct(id);
        request.setAttribute("id", p.getProductId());
        request.setAttribute("name", p.getName());
        request.setAttribute("description", p.getDescription());
        request.setAttribute("price", p.getPrice());
        return "update.jsp";
    }
}
