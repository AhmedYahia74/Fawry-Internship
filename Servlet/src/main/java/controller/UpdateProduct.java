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
@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldName = request.getParameter("oldName");
        String newName = request.getParameter("newName");
        int price = Integer.parseInt(request.getParameter("price"));
        response.setContentType("text/html");
        if(ProductRepository.updateProduct(oldName,new Product(newName,price))) {
            response.getWriter().println("<h1>Product Updated Successfully</h1>");
            response.getWriter().println("<p>Name: " + newName + "</p>");
            response.getWriter().println("<p>Price: " + price + "</p>");
            response.getWriter().println("<a href='updateProduct'>update Another Product</a>");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Update Product</title></head>");
        out.println("<body>");
        out.println("<h1>Update Product</h1>");
        out.println("<form action='updateProduct' method='post'>");
        out.println("<label for='oldName'>Product Name:</label>");
        out.println("<input type='text' name='oldName' id='oldName' required><br><br>");
        out.println("<label for='newName'>Product New Name:</label>");
        out.println("<input type='text' name='newName' id='newName' required><br><br>");
        out.println("<label for='price'>Product Name:</label>");
        out.println("<input type='text' name='price' id='price' required><br><br>");
        out.println("<input type='submit' value='Update Product'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
