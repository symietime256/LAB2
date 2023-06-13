/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.userDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "UserSignup", urlPatterns = {"/UserSignup"})
public class UserSignup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserSignup</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserSignup at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR = "error";
        private final String RETURN_PAGE = "return_page";
        private final String LOGIN_JSP = "login.jsp";
        private final String ERROR_PAGE_JSP = "ErrorPage.jsp";
        private final String USERNAME = "username";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        userDAO dao = new userDAO();
        try {
            if (dao.CheckDuplicate(request.getParameter(USERNAME))) {
                throw new NumberFormatException();
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dob;
            dob = df.parse("2003-12-10");
            User add = new User(0, request.getParameter("pass"), request.getParameter("name"), request.getParameter("username"), request.getParameter("gender"), false, dob);
            if (!dao.InsertUser(add)) {
                throw new NullPointerException();
            }
            User new_user = dao.Login(request.getParameter(USERNAME), request.getParameter("pass"));
            request.getSession().setAttribute("user", new_user);
            response.sendRedirect("MainPage");
        } catch (NumberFormatException e) {
            request.setAttribute(ERROR, "Existed username !");
            request.setAttribute(RETURN_PAGE, LOGIN_JSP);
            request.getRequestDispatcher(ERROR_PAGE_JSP).forward(request, response);
        } catch (ParseException ex) {
            request.setAttribute(ERROR, "cant add user !");
            request.setAttribute(RETURN_PAGE, LOGIN_JSP);
            request.getRequestDispatcher(ERROR_PAGE_JSP).forward(request, response);
        } catch (NullPointerException ex) {
            request.setAttribute(ERROR, "cant add user due to failed procedure !");
            request.setAttribute(RETURN_PAGE, LOGIN_JSP);
            request.getRequestDispatcher(ERROR_PAGE_JSP).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
