package domain.controller.handlers;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;
import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProductHandler extends RequestHandler {
    private ShopService service;
    private ArrayList<String> errors;
    public AddProductHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
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
            return new ProductOverviewHandler(service).handleRequest(request, response);
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
            if(des.matches(".*[<|>].*")){
                throw new DomainException();
            }
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
            if(name.matches(".*[<|>].*")){
                throw new DomainException();
            }
            product.setName(name);
            request.setAttribute("name", name);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
        return product;
    }
}
