package domain.controller.handlers;

import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProdHandler extends RequestHandler {
    private ShopService service;

    public DeleteProdHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        service.deleteProduct(id);
        return new ProductOverviewHandler(service).handleRequest(request, response);
    }
}
