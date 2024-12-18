package controller;


import product.Product;
import product.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteProduct")
public class deleteProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html");
        if(ProductRepository.deleteProduct(new Product(name,0))) {
            response.getWriter().println("<h1>Product Deleted Successfully</h1>");
            response.getWriter().println("<p>Name: " + name + "</p>");
            response.getWriter().println("<a href='deleteProduct'>delete Another Product</a>");
        }
        else {
            response.getWriter().println("<h1>Product Not Found</h1>");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Delete Product</title></head>");
        out.println("<body>");
        out.println("<h1>Delete Product</h1>");
        out.println("<form action='deleteProduct' method='post'>");
        out.println("<label for='name'>Product Name:</label>");
        out.println("<input type='text' name='name' id='name' required><br><br>");
        out.println("<input type='submit' value='Delete Product'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
