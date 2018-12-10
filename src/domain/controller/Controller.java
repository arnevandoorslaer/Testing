package domain.controller;// Import required java libraries

import domain.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Controller")
// Extend HttpServlet class
public class Controller extends HttpServlet {
private ArrayList<String> errors;
private ShopService service;
private HandlerFactory factory;

    public void init() throws  ServletException {
        super.init();
        ServletContext context = getServletContext();
        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }
        service = new ShopService(properties);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = "home";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        HandlerFactory factory = new HandlerFactory(service);
        RequestHandler handler = factory.getHandler(action);
        String dest = handler.handleRequest(request, response);
        request.getRequestDispatcher(dest).forward(request, response);
    }




/*


    private String check(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Person p = service.getPerson(id);
        String pw = request.getParameter("password");
        if(p.isCorrectPassword(pw)){
            request.setAttribute("out", "The given password was correct");
        }else {
            request.setAttribute("out", "The passwords dont match");
        }
        request.setAttribute("id", id);
        return "checkPassword.jsp";
    }
    private String checkRedirect(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "checkPassword.jsp";
    }
    private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        service.deletePerson(id);
        return overview(request, response);
    }

    private String deletePers(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("person", service.getPerson(id));
        return "deletePerson.jsp";
    }

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        service.deleteProduct(id);
        return products(request, response);
    }

    private String deleteProd(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("product", service.getProduct(id));
        return "deleteProduct.jsp";
    }

    private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
        errors = new ArrayList<>();
        Product product = new Product();
        product.setProductId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("id", product.getProductId());
        product = setName(request, response, product);
        product = setDescription(request, response, product);
        product = setPrice(request, response, product);

        if(errors.size() == 0) {
            try {
                service.updateProduct(product);
            } catch(DbException e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "update.jsp";
            }
            return products(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "update.jsp";
        }
    }

    private String update(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product p = service.getProduct(id);
        request.setAttribute("id", p.getProductId());
        request.setAttribute("name", p.getName());
        request.setAttribute("description", p.getDescription());
        request.setAttribute("price", p.getPrice());
        return "update.jsp";
    }

    private String addProduct(HttpServletRequest request, HttpServletResponse response) {
        errors = new ArrayList<>();
        Product product = new Product();
        product = setName(request, response, product);
        product = setDescription(request, response, product);
        product = setPrice(request, response, product);
        product = setProdID(request,response, product);

        if(errors.size() == 0) {
            try {
                service.addProduct(product);
            } catch(DbException e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "addProduct.jsp";
            }
            return products(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    private Product setProdID(HttpServletRequest request, HttpServletResponse response, Product product){
        try{
            int id = Integer.parseInt(request.getParameter("ide"));
            product.setProductId(id);
            request.setAttribute("ide", id);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
        return product;
    }
    private Product setPrice(HttpServletRequest request, HttpServletResponse response, Product product) {
        try {
            String price = request.getParameter("price");
            product.setPrice(price);
            request.setAttribute("price", price);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
        return product;
    }

    private Product setDescription(HttpServletRequest request, HttpServletResponse response, Product product) {
        try {
            String des = request.getParameter("description");
            product.setDescription(des);
            request.setAttribute("description", des);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
        return product;
    }

    private Product setName(HttpServletRequest request, HttpServletResponse response, Product product) {
        try {
            String name = request.getParameter("name");
            product.setName(name);
            request.setAttribute("name", name);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
        return product;
    }

    private String products(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("products", service.getProducts());
        return "products.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response) {
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
            return overview(request, response);
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
        } catch(IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setLastName(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String lastName = request.getParameter("lastName");
            p.setLastName(lastName);
            request.setAttribute("lastname", lastName);
        } catch(IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setFirstName(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String firstName = request.getParameter("firstName");
            p.setFirstName(firstName);
            request.setAttribute("firstname", firstName);
        } catch(IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setEmail(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String email = request.getParameter("email");
            p.setEmail(email);
            request.setAttribute("email", email);
        } catch(IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        return p;
    }

    public Person setPassword(HttpServletRequest request, HttpServletResponse response, Person p){
        try {
            String pw = request.getParameter("password");
            p.setPasswordHashed(pw);
            request.setAttribute("password", pw);
        } catch(IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        return p;
    }
    public String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("persons", service.getPersons());
        return "personoverview.jsp";
    }


*/

    public void destroy() {
        // do nothing.
    }
}