package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class BestelHandler extends RequestHandler {
    private ShopService service;
    public BestelHandler(ShopService serv){
        service = serv;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> prods;

        prods = (HashMap<Integer, Integer>) session.getAttribute("list");
        if(prods == null){
            request.setAttribute("foet", "ne grappemaker? gij wilt een lege kar bestelle???");
            return new cartHandler(service).handleRequest(request, response);
        }
        else if(request.getSession().getAttribute("person") == null){
            request.setAttribute("foet", "Eerst wel inloggen he");
            return new cartHandler(service).handleRequest(request, response);
        }else{
            request.setAttribute("foet", "Dat is in de sjakos he, das besteld");
            request.getSession().removeAttribute("list");
            request.getSession().removeAttribute("tot");
            return new cartHandler(service).handleRequest(request,response);
        }
    }
}
