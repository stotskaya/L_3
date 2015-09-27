package controller;

import dao.*;
import model.*;
import exception.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IndexServlet extends HttpServlet{

    static ItemDao items;
    static UserDao users;
    static HttpSession session;
    static List productsInCartList = new ArrayList();
    static List pricesInCart = new ArrayList();
    static List totalPricesInCart = new ArrayList();
    static List quantitiesInCart = new ArrayList();
    static List userProductName = new ArrayList();
    static List userProductPrice = new ArrayList();
    static int totalCartItems = 0;
    static private String AUTHENTICATION = "authentication";
    static private String TOTAL_CART_ITEMS = "total_cart_items";
    static private String PRODUCT_IN_CART = "product_in_cart";
    static private String SUCCESS = "Success";
    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class.getName());

    @Override
    public void init() throws ServletException {
            items = new ItemDaoJdbc();
            users = new UserDaoJdbc();
        LOGGER.info("Initialization of database finished..");
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
        LOGGER.debug("Incoming action is " + action);
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
                LOGGER.error("Invalid incoming action:" + action);
                throw new IllegalArgumentException("Invalid action: " + action);
            }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] itemnames = req.getParameterValues("quantity");
        String logIn = (String) session.getAttribute(AUTHENTICATION);
        if (logIn != null) {
            userProductName = (ArrayList) session.getAttribute("product_name");
            userProductPrice = (ArrayList) session.getAttribute("product_price");
            for (int i = 0; i < itemnames.length; i++) {
                if (!"".equals(itemnames[i])){
                totalCartItems = totalCartItems + (Integer.parseInt(itemnames[i]));
                quantitiesInCart.add(itemnames[i]);
                productsInCartList.add(userProductName.get(i));
                Integer price =(Integer) userProductPrice.get(i);
                pricesInCart.add(price);
                Integer total = price * Integer.parseInt(itemnames[i]);
                totalPricesInCart.add(total);
                session.setAttribute(TOTAL_CART_ITEMS , totalCartItems);
                session.setAttribute(PRODUCT_IN_CART, productsInCartList);
                session.setAttribute("quantity_in_cart", quantitiesInCart);
                session.setAttribute("prices_in_cart", pricesInCart);
                session.setAttribute("total_prices_in_cart", totalPricesInCart);
                req.getRequestDispatcher("items.jsp").forward(req,resp);
                } else {
                    session.setAttribute("info_product",  "Please, set a quantity for the product!");
                    req.getRequestDispatcher("items.jsp").forward(req,resp);
                }
            }
        }
        else {
            session.setAttribute("info",  "To order login, please!");
            req.getRequestDispatcher("/authorization.jsp").forward(req,resp);
        }
    }

    private void getGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer groupItem = Integer.parseInt(req.getParameter("GroupItems"));
        session.setAttribute("groupID", groupItem);
        req.getRequestDispatcher("items.jsp").forward(req, resp);
    }

    private void toAuthorizationPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (SUCCESS.equals(users.identification(login, password))) {
                session.setAttribute(AUTHENTICATION, SUCCESS);
                session.setAttribute("userlogin", login);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                session.setAttribute(AUTHENTICATION, "Error");
                req.getRequestDispatcher("/authorization.jsp").forward(req, resp);
            }
        } catch (DBSystemException e) {
            LOGGER.error("Unable to initialize database.", e);
            throw new ServletException(e);
        }
    }

    private void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            session.setAttribute(AUTHENTICATION, null);
            session.removeAttribute(AUTHENTICATION);
            session.setAttribute("userlogin", null);
            session.removeAttribute("product_name");
            session.removeAttribute("product_price");
            session.removeAttribute("quantity_in_cart");
            session.setAttribute(PRODUCT_IN_CART,null);
            session.removeAttribute(PRODUCT_IN_CART);
            session.setAttribute("prices_in_cart",null);
            session.removeAttribute("prices_in_cart");
            session.setAttribute("total_prices_in_cart",null);
            session.removeAttribute(TOTAL_CART_ITEMS );
            session.setAttribute(TOTAL_CART_ITEMS ,null);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void toRegistrationPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setNumber(req.getParameter("number"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        try {
            users.insert(user);
            session.setAttribute(AUTHENTICATION, SUCCESS);
            session.setAttribute("info", "Registration successful. Please login!");
            req.getRequestDispatcher("/authorization.jsp").forward(req, resp);
        } catch (DBSystemException e) {
            LOGGER.error("Unable to initialize database.", e);
            throw new ServletException(e);
        }
    }
}
