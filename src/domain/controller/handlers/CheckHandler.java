package domain.controller.handlers;

import domain.model.DomainException;
import domain.model.Person;
import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckHandler extends RequestHandler {
    private ShopService service;

    public CheckHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Person p = service.getPerson(id);
        String pw = request.getParameter("password");

        try {
            if(pw.matches(".*[<|>].*") || id.matches(".*[<|>].*")){
                throw new DomainException();
            }
            if (p.isCorrectPassword(pw)) {
                request.setAttribute("out", "The given password was correct");
            } else {
                request.setAttribute("out", "The passwords dont match");
            }
        }catch (Exception e){
            request.setAttribute("check", "Jama, ge meot wel iets ingeven he");
        }
        request.setAttribute("id", id);
        return "checkPassword.jsp";
    }
}
