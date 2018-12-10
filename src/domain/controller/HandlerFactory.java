package domain.controller;

import domain.model.ShopService;
import domain.controller.handlers.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {

    private Map<String, RequestHandler> handlers = new HashMap<>();
    public HandlerFactory(ShopService service) {
        handlers.put("home", new HomeHandler());
        handlers.put("overview", new PersonOverviewHandler(service));
        handlers.put("signUp", new SignUpHandler());
        handlers.put("register", new RegisterHandler(service));
        handlers.put("products", new ProductOverviewHandler(service));
        handlers.put("addProduct", new AddProductPageHandler());
        handlers.put("Add", new AddProductHandler(service));
        handlers.put("update", new UpdateHandler(service));
        handlers.put("updateProduct", new ProductUpdateHandler(service));
        handlers.put("deleteProd", new ConfDeleteProdHandler(service));
        handlers.put("deleteProduct", new DeleteProdHandler(service));
        handlers.put("deletePers", new ConfDeletePersHandler(service));
        handlers.put("deletePerson", new DeletePersHandler(service));
        handlers.put("checkRedirect", new CheckRedirectHandler());
        handlers.put("check", new CheckHandler(service));
        handlers.put("setQuote", new QuoteHandler());
        handlers.put("SetPersonOrder", new PersonOrderHandler(service));
        handlers.put("cart", new cartHandler(service));
        handlers.put("addCart", new addCartHandler(service));
        handlers.put("deleteCart", new deleteCartHandler(service));
        handlers.put("login", new LoginHandler(service));
        handlers.put("logout", new LogoutHandler());
        handlers.put("Bestel", new BestelHandler(service));
    }
    public RequestHandler getHandler(String key) {
        return handlers.get(key);
    }
}
