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
import java.io.Serializable;
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
    private static final String AUTHENTICATION = "authentication";
    private static final String USER_LOGIN = "userlogin";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String INFO = "info";
    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    private static final String ITEMS_JSP = "items.jsp";
    private static final String INDEX_JSP = "index.jsp";
    private static final String AUTHORIZATION_JSP = "authorization.jsp";

    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE ="product_price";
    private static final String TOTAL_CART_ITEMS = "total_cart_items";
    private static final String PRODUCT_IN_CART = "product_in_cart";
    private static final String QUANTITY_IN_CART = "quantity_in_cart";
    private static final String PRICES_IN_CART = "prices_in_cart";
    private static final String TOTAL_PRICES_IN_CART = "total_prices_in_cart";

    private static final String ADD_TO_CART= "AddToCart";
    private static final String GET_GROUP = "GetGroup";
    private static final String TO_AUTHORIZATION_PAGE = "ToAuthorizationPage";
    private static final String SIGN_OUT = "SignOut";
    private static final String TO_REGISTRATION_PAGE = "ToRegistrationPage";


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
        try {
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
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        String[] itemnames = req.getParameterValues("quantity");
        String logIn = (String) session.getAttribute(AUTHENTICATION);
        try {
        if (logIn != null) {
            userProductName = (ArrayList) session.getAttribute(PRODUCT_NAME);
            userProductPrice = (ArrayList) session.getAttribute(PRODUCT_PRICE);
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
                session.setAttribute(QUANTITY_IN_CART, quantitiesInCart);
                session.setAttribute(PRICES_IN_CART , pricesInCart);
                session.setAttribute(TOTAL_PRICES_IN_CART, totalPricesInCart);
                } else {
                    session.setAttribute("info_product",  "Please, set a quantity for the product!");
                }
                req.getRequestDispatcher(ITEMS_JSP).forward(req,resp);
            }
        }
        else {
            session.setAttribute(INFO,  "To order login, please!");
            req.getRequestDispatcher(AUTHORIZATION_JSP).forward(req,resp);
        }
        } catch (ServletException e) {
            LOGGER.error(stringErrorIO(ADD_TO_CART), e);
            throw new ServletException(stringErrorIO(ADD_TO_CART),e);
        } catch (IOException e) {
            LOGGER.error(stringErrorServlet(ADD_TO_CART), e);
            throw new ServletException(stringErrorServlet(ADD_TO_CART), e);
        }
    }

    private static void getGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer groupItem = Integer.parseInt(req.getParameter("GroupItems"));
        session.setAttribute("groupID", groupItem);
        try {
            req.getRequestDispatcher(ITEMS_JSP).forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(stringErrorIO(GET_GROUP), e);
            throw new ServletException(stringErrorIO(GET_GROUP),e);
        } catch (IOException e) {
            LOGGER.error(stringErrorServlet(GET_GROUP), e);
            throw new ServletException(stringErrorServlet(GET_GROUP), e);
        }
    }

    private static void toAuthorizationPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        try {
            if (SUCCESS.equals(users.identification(login, password))) {
                session.setAttribute(AUTHENTICATION, SUCCESS);
                session.setAttribute(USER_LOGIN, login);
                req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
            } else {
                session.setAttribute(AUTHENTICATION, ERROR);
                req.getRequestDispatcher(AUTHORIZATION_JSP).forward(req, resp);
            }
        } catch (DBSystemException e) {
            LOGGER.error("Unable to initialize database", e);
            throw new ServletException("Unable to initialize database",e);
        } catch (ServletException e) {
            LOGGER.error(stringErrorIO(TO_AUTHORIZATION_PAGE), e);
            throw new ServletException(stringErrorIO(TO_AUTHORIZATION_PAGE),e);
        } catch (IOException e) {
            LOGGER.error(stringErrorServlet(TO_AUTHORIZATION_PAGE), e);
            throw new ServletException(stringErrorServlet(TO_AUTHORIZATION_PAGE), e);
        }
    }

    private static void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            session.setAttribute(AUTHENTICATION, null);
            session.removeAttribute(AUTHENTICATION);
            session.setAttribute(USER_LOGIN, null);
            session.removeAttribute(PRODUCT_NAME);
            session.removeAttribute(PRODUCT_PRICE);
            session.removeAttribute(QUANTITY_IN_CART);
            session.setAttribute(PRODUCT_IN_CART,null);
            session.removeAttribute(PRODUCT_IN_CART);
            session.setAttribute(PRICES_IN_CART ,null);
            session.removeAttribute(PRICES_IN_CART);
            session.setAttribute(TOTAL_PRICES_IN_CART,null);
            session.removeAttribute(TOTAL_CART_ITEMS );
            session.setAttribute(TOTAL_CART_ITEMS ,null);
        try {
            req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(stringErrorIO(SIGN_OUT), e);
            throw new ServletException(stringErrorIO(SIGN_OUT),e);
        } catch (IOException e) {
            LOGGER.error(stringErrorServlet(SIGN_OUT), e);
            throw new ServletException(stringErrorServlet(SIGN_OUT), e);
        }
    }

    private static void toRegistrationPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setNumber(req.getParameter("number"));
        user.setLogin(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));
        try {
            users.insert(user);
            session.setAttribute(AUTHENTICATION, SUCCESS);
            session.setAttribute(INFO, "Registration successful. Please login!");
            req.getRequestDispatcher(AUTHORIZATION_JSP).forward(req, resp);
        } catch (DBSystemException e) {
            LOGGER.error("Unable to initialize database.", e);
            throw new ServletException(e);
        } catch (ServletException e) {
            LOGGER.error(stringErrorIO(TO_REGISTRATION_PAGE), e);
            throw new ServletException(stringErrorIO(TO_REGISTRATION_PAGE),e);
        } catch (IOException e) {
            LOGGER.error(stringErrorServlet(TO_REGISTRATION_PAGE), e);
            throw new ServletException(stringErrorServlet(TO_REGISTRATION_PAGE), e);
        }
    }

    private static String stringErrorIO(String requestString){
        return "Error is detected when the servlet handles the request" + requestString;
    }

    private static String stringErrorServlet(String requestString){
        return "The request for the " + requestString + " could not be handled";
    }
}
