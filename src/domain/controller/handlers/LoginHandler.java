package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler extends RequestHandler {
    private ShopService service;

    public LoginHandler(ShopService serv){
        service = serv;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pw = request.getParameter("pwd");
        Person p;

        try{
            if(pw.matches(".*[<|>].*") || id.matches(".*[<|>].*")){
                throw new DomainException();
            }
            System.out.println(id + pw);
            p = service.getPerson(id);
            if(!p.isCorrectPassword(pw)){
                System.out.println("fout pw");
                return new HomeHandler().handleRequest(request, response);
            }else{
                request.getSession().setAttribute("person", p.getFirstName());
                if(p.getRole() == Role.ADMIN){
                    request.getSession().setAttribute("role", p.getRole().toString());
                }
            }
        }catch(Exception e){
            System.out.println("foutje in loginhandler");
            return new HomeHandler().handleRequest(request, response);
        }



        return new HomeHandler().handleRequest(request, response);
    }
}
