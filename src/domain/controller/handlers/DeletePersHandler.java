package domain.controller.handlers;

import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePersHandler extends RequestHandler {
    private ShopService service;

    public DeletePersHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        service.deletePerson(id);
        return new PersonOverviewHandler(service).handleRequest(request, response);
    }
}
