package product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    static private List<Product> products=new ArrayList<>();
    public static void addProduct(Product product) {
        products.add(product);
    }
    public static Boolean deleteProduct(Product product) {
        for (Product j : products) {
            if (j.equals(product)) {
                products.remove(j);
                return true;
            }
        }
        return false;
    }

    public static boolean updateProduct(String oldName, Product product) {
        Product temp = new Product(oldName, 0);
        for (Product j : products) {
            if (j.equals(temp)) {
                products.remove(j);
                products.add(product);
                return true;
            }
        }
        return false;
    }
    public static Optional<Product> findProduct(String name){
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
