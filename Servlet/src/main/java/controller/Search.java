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
import java.util.Optional;

@WebServlet("/search")
public class Search extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html");
        Optional<Product> product=ProductRepository.findProduct(name);
        if(product.isPresent()) {

            response.getWriter().println("<p>Name: " + name + "</p>");
            response.getWriter().println("<p>Price: " + product.get().getPrice() + "</p>");
            response.getWriter().println("<a href='search'>find Another Product</a>");
        }
        else {
            response.getWriter().println("<h1>Product Not Found</h1>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Find Product</title></head>");
        out.println("<body>");
        out.println("<h1>Find Product</h1>");
        out.println("<form action='search' method='post'>");
        out.println("<label for='name'>Product Name:</label>");
        out.println("<input type='text' name='name' id='name' required><br><br>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
