package Controller;

import Dao.*;
import Model.*;
import Exception.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class IndexServlet extends HttpServlet{

    ItemDao items;
    UserDao users;
    HttpSession session;
    RequestDispatcher rd;
    ArrayList products_in_cart_list = new ArrayList();
    ArrayList prices_in_cart = new ArrayList();
    ArrayList total_prices_in_cart = new ArrayList();
    ArrayList quantities_in_cart = new ArrayList();
    ArrayList user_product_name = new ArrayList();
    ArrayList user_product_price = new ArrayList();
    int total_cart_items = 0;

    @Override
    public void init() throws ServletException {
            items = new ItemDaoJdbc();
            users = new UserDaoJdbc();
    }

    public enum Actions {
        AUTHORIZATION,
        REGISTRATION,
        GETGROUP,
        ADDTOCART,
        SIGNOUT
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession(true);
        String action = req.getParameter("action");
        Actions currentAction = Actions.valueOf(action.toUpperCase());
        switch (currentAction) {
            case AUTHORIZATION:
                toAuthorizationPage(req, resp);
                break;
            case REGISTRATION:
                toRegistrationPage(req, resp);
                break;
            case GETGROUP:
                getGroup(req, resp);
                break;
            case ADDTOCART:
                addToCart(req, resp);
                break;
            case SIGNOUT:
                signOut(req, resp);
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp){
        String[] itemnames = req.getParameterValues("quantity");
        String logIn = (String) session.getAttribute("authentication");
        try {
            if (logIn != null) {
                user_product_name = (ArrayList) session.getAttribute("product_name");
                user_product_price = (ArrayList) session.getAttribute("product_price");
                for (int i = 0; i < itemnames.length; i++) {
                     total_cart_items = total_cart_items + (Integer.parseInt(itemnames[i]));
                     quantities_in_cart.add(itemnames[i]);
                     products_in_cart_list.add(user_product_name.get(i));
                     Integer price =(Integer) user_product_price.get(i);
                     prices_in_cart.add(price);
                     Integer total = price * Integer.parseInt(itemnames[i]);
                     total_prices_in_cart.add(total);
                }
                session.setAttribute("total_cart_items", total_cart_items);
                session.setAttribute("product_in_cart", products_in_cart_list);
                session.setAttribute("quantity_in_cart", quantities_in_cart);
                session.setAttribute("prices_in_cart", prices_in_cart);
                session.setAttribute("total_prices_in_cart", total_prices_in_cart);
                rd = req.getRequestDispatcher("/items.jsp");
            }
            else {
                session.setAttribute("info",  "To order login, please!");
                rd = req.getRequestDispatcher("/authorization.jsp");
            }
               rd.forward(req, resp);
        }  catch (Exception e) {
            //TODO Exception in addToCart
        }
    }

    private void getGroup(HttpServletRequest req, HttpServletResponse resp){
        Integer groupItem = Integer.parseInt(req.getParameter("GroupItems"));
        session.setAttribute("groupID", groupItem);
        try {
            req.getRequestDispatcher("items.jsp").forward(req, resp);
        }  catch (Exception e) {
            //TODO Exception in getGroup
        }
    }

    private void toAuthorizationPage(HttpServletRequest req, HttpServletResponse resp){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (users.identification(login, password).equals("Success")) {
                session.setAttribute("authentication", "Success");
                session.setAttribute("userlogin", login);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                session.setAttribute("authentication", "Error");
                rd = req.getRequestDispatcher("/authorization.jsp");
                rd.forward(req, resp);
            }
        } catch (DBSystemException e) {
            //TODO DBSystemException in toAuthorizationPage
        } catch (Exception e){
            //TODO Exception in toAuthorizationPage
        }
    }

    private void signOut(HttpServletRequest req, HttpServletResponse resp){
        try {
            session.setAttribute("authentication", null);
            session.removeAttribute("authentication");
            session.setAttribute("userlogin", null);
            session.removeAttribute("product_name");
            session.removeAttribute("product_price");
            session.removeAttribute("quantity_in_cart");
            session.setAttribute("product_in_cart",null);
            session.removeAttribute("product_in_cart");
            session.setAttribute("prices_in_cart",null);
            session.removeAttribute("prices_in_cart");
            session.setAttribute("total_prices_in_cart",null);
            rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }  catch (Exception e){
            //TODO Exception in toAuthorizationPage
        }
    }

    private void toRegistrationPage(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setNumber(req.getParameter("number"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        System.out.println("to register");
        try {
            users.insert(user);
            System.out.println("after insert");
            session.setAttribute("authentication", "Success");
            session.setAttribute("info",  "Registration successful. Please login!");
            req.getRequestDispatcher("/authorization.jsp").forward(req, resp);
        } catch (DBSystemException e) {
            //TODO DBSystemException in toRegistrationPage
        } catch (Exception e) {
            //TODO Exception in toRegistrationPage
        }
    }

}
