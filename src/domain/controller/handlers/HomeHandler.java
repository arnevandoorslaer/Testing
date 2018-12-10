package domain.controller.handlers;

import domain.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeHandler extends RequestHandler {

    public HomeHandler() {
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String cook = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("quote")){
                    cook = cookie.getValue();
                }
            }
        }

        if(cook.equals("Ja")){
            request.setAttribute("quote","Dit is een quote");
        }else{
            request.setAttribute("quote","");
        }
        return "index.jsp";
    }

}
