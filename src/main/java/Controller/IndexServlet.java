package Controller;

import Dao.*;
import Model.*;
import Exception.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet{

    ItemDao items;
    UserDao users;

    @Override
    public void init() throws ServletException {
            items = new ItemDaoJdbc();
            users = new UserDaoJdbc();
    }

    public enum Actions {
        ADDITEM,
        AUTHORIZATION,
        REGISTRATION,
        DELETE
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Actions currentAction = Actions.valueOf(action.toUpperCase());
        switch (currentAction) {
            case ADDITEM:
                addItem(req);
                break;
            case AUTHORIZATION:
                toAuthorizationPage(req, resp);
                break;
            case REGISTRATION:
                toRegistrationPage(req, resp);
                break;
            case DELETE:
                deleteItem(req, resp);
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    private void addItem(HttpServletRequest req){
        String naimenovanie = req.getParameter("naimenovanie");
        String price = req.getParameter("price");
        try {
            items.insert(new Item(1L, naimenovanie, Integer.parseInt(price)));
            ItemsBeans itemsBeans = new ItemsBeans();
            itemsBeans.setItems(items.selectAll());
            req.setAttribute("model", itemsBeans);
        } catch (DBSystemException e) {
            //TODO DBSystemException in addItem
        }
    }

    private void toAuthorizationPage(HttpServletRequest req, HttpServletResponse resp){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (users.identification(login, password)) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                PrintWriter pr = resp.getWriter();
                pr.println("Login/password incorrect");
            }
        } catch (DBSystemException e) {
            //TODO DBSystemException in toAuthorizationPage
        } catch (Exception e){
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
        try {
            users.insert(user);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (DBSystemException e) {
            //TODO DBSystemException in toRegistrationPage
        } catch (Exception e) {
            //TODO Exception in toRegistrationPage
        }
    }

    private void deleteItem(HttpServletRequest req, HttpServletResponse resp){
        try {
            items.deleteById(Long.parseLong(req.getParameter("id")));
            ItemsBeans itemsBeans = new ItemsBeans();
            itemsBeans.setItems(items.selectAll());
            req.setAttribute("moldele", itemsBeans);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (DBSystemException e){
            //TODO  DBSystemException in deleteItem
        } catch (Exception e){
            //TODO Exception in deleteItem
        }
    }
}
