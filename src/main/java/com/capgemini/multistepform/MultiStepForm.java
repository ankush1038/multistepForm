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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String step = request.getParameter("step");

        switch (step) {
            case "1":
                session.setAttribute("firstName", request.getParameter("firstName"));
                session.setAttribute("lastName", request.getParameter("lastName"));
                response.sendRedirect("step2.html");
                break;
            case "2":
                session.setAttribute("email", request.getParameter("email"));
                session.setAttribute("phone", request.getParameter("phone"));
                response.sendRedirect("step3.html");
                break;
            case "3":
                session.setAttribute("city", request.getParameter("city"));
                session.setAttribute("country", request.getParameter("country"));

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h2>Review Your Details</h2>");
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
