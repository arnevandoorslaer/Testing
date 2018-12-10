package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.DomainException;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class addCartHandler extends RequestHandler {
    ShopService service;
    public addCartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> prods;

        prods = (HashMap<Integer, Integer>) session.getAttribute("list");

        if(prods == null){
            prods = new HashMap<>();
        }
        String id = request.getParameter("id");
        int nu = 0;
        try{
            if(prods.containsKey(Integer.valueOf(id))){
                nu = prods.get(Integer.valueOf(id));
            }
            int t = 1;
            if(!request.getParameter("amount").equals("")) {
                t = Integer.valueOf(request.getParameter("amount"));
                if(t <1){
                    throw new DomainException();
                }
                prods.put(Integer.valueOf(id), t+nu);
            }else{
                prods.put(Integer.valueOf(id), Integer.valueOf("1")+nu);
            }
            if(session.getAttribute("tot") == null) {
                session.setAttribute("tot", t);
            }else{
                session.setAttribute("tot", t +
                        (Integer)session.getAttribute("tot"));
            }

            request.setAttribute("fout", "Da zit in da mandje eh!");
        }catch(Exception e){
            request.setAttribute("fout", "Wilde daar nu aub is een gewoon nummerke ingeven, postief ook liefst. Ge kunt da ook geen 0 keer bestellen he vrient");
        }
        session.setAttribute("list", prods);
        return new ProductOverviewHandler(service).handleRequest(request,response);
    }

    }
