package domain.controller.handlers;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;
import domain.model.ShopService;
import domain.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductUpdateHandler extends RequestHandler {
    private ShopService service;
    private ArrayList<String> errors;

    public ProductUpdateHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
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
            return new ProductOverviewHandler(service).handleRequest(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "update.jsp";
        }
    }

    private Product setPrice(HttpServletRequest request, HttpServletResponse response, Product product) {
        try {
            String price = request.getParameter("price");
            product.setPrice(price);
            request.setAttribute("price", price);
        } catch(Exception e){
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
        } catch(Exception e){
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
        } catch(Exception e){
            errors.add(e.getMessage());
        }
        return product;
    }

}
