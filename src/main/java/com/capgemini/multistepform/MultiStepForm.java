package com.capgemini.multistepform;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/form")
public class MultiStepForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String step = request.getParameter("step");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("2".equals(step)) {
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");

            out.println("<html><body>");
            out.println("<p>First Name: " + firstName + "</p>");
            out.println("<p>Last Name: " + lastName + "</p>");
            out.println("<form action='form' method='post'>");
            out.println("<input type='hidden' name='step' value='2'>");
            out.println("Email: <input type='email' name='email'><br>");
            out.println("Phone: <input type='text' name='phone'><br>");
            out.println("<input type='submit' value='Next'>");
            out.println("</form></body></html>");

        } else if ("3".equals(step)) {
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");
            String email = (String) session.getAttribute("email");
            String phone = (String) session.getAttribute("phone");

            out.println("<html><body>");
            out.println("<p>First Name: " + firstName + "</p>");
            out.println("<p>Last Name: " + lastName + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Phone: " + phone + "</p>");
            out.println("<form action='form' method='post'>");
            out.println("<input type='hidden' name='step' value='3'>");
            out.println("City: <input type='text' name='city'><br>");
            out.println("Country: <input type='text' name='country'><br>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form></body></html>");
        } else {
            response.sendRedirect("step1.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String step = request.getParameter("step");

        switch (step) {
            case "1":
                session.setAttribute("firstName", request.getParameter("firstName"));
                session.setAttribute("lastName", request.getParameter("lastName"));
                response.sendRedirect("form?step=2");
                break;
            case "2":
                session.setAttribute("email", request.getParameter("email"));
                session.setAttribute("phone", request.getParameter("phone"));
                response.sendRedirect("form?step=3");
                break;
            case "3":
                session.setAttribute("city", request.getParameter("city"));
                session.setAttribute("country", request.getParameter("country"));

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("First Name: " + session.getAttribute("firstName") + "<br>");
                out.println("Last Name: " + session.getAttribute("lastName") + "<br>");
                out.println("Email: " + session.getAttribute("email") + "<br>");
                out.println("Phone: " + session.getAttribute("phone") + "<br>");
                out.println("City: " + session.getAttribute("city") + "<br>");
                out.println("Country: " + session.getAttribute("country") + "<br>");
                out.println("</body></html>");
                break;
            default:
                response.sendRedirect("step1.html");
        }
    }
}
