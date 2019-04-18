package groceryListJava;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.List;
import javax.servlet.*;

import groceryListJava.Grocery;
import groceryListJava.GroceryDAO;

//web traffic handler - handles all requests from the user
public class GroceryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroceryDAO groceryDAO;

    public void init() {
        String jdbcURL = System.getenv("jdbcURL");
        String jdbcUsername = System.getenv("jdbcUsername");
        String jdbcPassword = System.getenv("jdbcPassword");

        groceryDAO = new GroceryDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override //get data
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override //send data - operations that change data
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertGrocery(request, response);
                    break;
                case "/delete":
                    deleteGrocery(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateGrocery(request, response);
                    break;
                default:
                    listGrocery(request, response);
                    break;

            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listGrocery(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Grocery> groceryList = groceryDAO.listAllGroceryItems();
        request.setAttribute("groceryList", groceryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        System.out.println("Servlet: " + groceryList);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addItem.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Grocery existingItem = groceryDAO.getGrocery(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addItem.jsp");
        request.setAttribute("grocery", existingItem);
        dispatcher.forward(request, response);
    }

    private void insertGrocery(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        String item = request.getParameter("item");
        String amount = request.getParameter("amount");
        String isle = request.getParameter("isle");
        boolean hasCoupon = Boolean.parseBoolean(request.getParameter("hasCoupon"));
        boolean onSale = Boolean.parseBoolean(request.getParameter("onSale"));

        Grocery grocery = new Grocery(item, amount, isle, hasCoupon, onSale);
        groceryDAO.insertGrocery(grocery);
        response.sendRedirect("list");
    }

    private void updateGrocery(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        boolean hasCoupon;
        boolean onSale;

        int id = Integer.parseInt(request.getParameter("id"));
        String item = request.getParameter("item");
        String amount = request.getParameter("amount");
        String isle = request.getParameter("isle");
        String coupon = request.getParameter("hasCoupon");
        if (coupon.equals("yes")) {
            hasCoupon = true;
        } else {
            hasCoupon = false;
        }
        String sale = request.getParameter("onSale");
        if (sale.equals("yes")) {
            onSale = true;
        } else {
            onSale = false;
        }
        Grocery grocery = new Grocery(id, item, amount, isle, hasCoupon, onSale);
        groceryDAO.updateGrocery(grocery);
        response.sendRedirect("list");
    }

    private void deleteGrocery(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Grocery grocery = new Grocery(id);
        groceryDAO.deleteGrocery(grocery);
        response.sendRedirect("list");
    }
}
