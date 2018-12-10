package domain.controller.handlers;

import domain.controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class RegisterHandler extends RequestHandler {
    private ShopService service;
    private ArrayList<String> errors;

    public RegisterHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        errors = new ArrayList<>();
        Person person = new Person();
        person = setUid(request, response, person);
        person = setLastName(request, response, person);
        person = setFirstName(request, response, person);
        person = setEmail(request, response, person);
        person = setPassword(request, response, person);

        if(errors.size() == 0) {
            try {
                service.addPerson(person);
            } catch(DbException e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "signUp.jsp";
            }
            return new PersonOverviewHandler(service).handleRequest(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }

    public Person setUid(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String UID = request.getParameter("userid");
            p.setUserid(UID);
            request.setAttribute("uid", UID);
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setLastName(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String lastName = request.getParameter("lastName");
            if(lastName.matches(".*[<|>].*")){
                throw new DomainException();
            }
            p.setLastName(lastName);
            request.setAttribute("lastname", lastName);
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setFirstName(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String firstName = request.getParameter("firstName");
            if(firstName.matches(".*[<|>].*")){
                throw new DomainException();
            }
            p.setFirstName(firstName);
            request.setAttribute("firstname", firstName);
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setEmail(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String email = request.getParameter("email");
            p.setEmail(email);
            request.setAttribute("email", email);
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setPassword(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String pw = request.getParameter("password");
            if(pw.matches(".*[<|>].*")){
                throw new DomainException();
            }
            p.setPasswordHashed(pw);
            request.setAttribute("password", pw);
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return p;
    }
}
