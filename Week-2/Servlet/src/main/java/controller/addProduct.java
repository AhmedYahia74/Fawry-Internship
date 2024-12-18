package controller;

import product.Product;
import product.ProductRepository;

import java.io.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class addProduct extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        response.getWriter().println("<h1>Product Added Successfully</h1>");
        response.getWriter().println("<p>Name: " + name + "</p>");
        response.getWriter().println("<p>Price: " + price + "</p>");
        response.getWriter().println("<a href='addProduct'>Add Another Product</a>");
        ProductRepository.addProduct(new Product(name, Integer.parseInt(price)));
        response.getWriter().close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Add Product</title></head>");
        out.println("<body>");
        out.println("<h1>Add Product</h1>");
        out.println("<form action='addProduct' method='post'>");
        out.println("<label for='name'>Product Name:</label>");
        out.println("<input type='text' name='name' id='name' required><br><br>");
        out.println("<label for='price'>Product Price:</label>");
        out.println("<input type='text' name='price' id='price' required><br><br>");
        out.println("<input type='submit' value='Add Product'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}