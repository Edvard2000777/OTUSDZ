import java.io.IOException;

class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}

class Product {
    private InventoryService inventoryService;
    private String productId;

    public Product(String productId) {
        this.productId = productId;
        this.inventoryService = new InventoryService();
    }

    public int getStockQuantity() throws IOException {
        try (InventoryResource inventoryResource = inventoryService.checkStock(productId)) {
            return inventoryResource.getQuantity();
        }
    }
}

class Order {
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void placeOrder() {
        try {
            int availableStock = product.getStockQuantity();
            if (availableStock >= quantity) {
                System.out.println("Order placed successfully.");
            } else {
                throw new InsufficientStockException("Insufficient stock.");
            }
        } catch (InsufficientStockException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error accessing inventory: " + e.getMessage());
        }
    }
}

class InventoryService {
    public InventoryResource checkStock(String productId) throws IOException {
        // Emulate resource access error
        throw new IOException("Error accessing inventory service.");
    }
}

class InventoryResource implements AutoCloseable {
    private int quantity;

    public InventoryResource(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public void close() {
        // Release any allocated resources
        System.out.println("Inventory resource closed.");
    }
}

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        Product product = new Product("P123");
        Order order = new Order(product, 10);
        order.placeOrder();
    }
}