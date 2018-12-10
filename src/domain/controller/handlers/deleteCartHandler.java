package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class deleteCartHandler extends RequestHandler {
    private ShopService service;
    public deleteCartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> prods;
        int t =Integer.valueOf(request.getParameter("id"));
        prods = (HashMap<Integer, Integer>) session.getAttribute("list");
        session.setAttribute("tot",
                (Integer)session.getAttribute("tot")-prods.get(t));
        prods.remove(t);
        session.setAttribute("list", prods);
        return new cartHandler(service).handleRequest(request, response);
    }
}
