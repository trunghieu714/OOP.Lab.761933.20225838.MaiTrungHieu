package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.swing.JFrame;

public class CartScreen extends JFrame {
    private Store store;
    private Cart cart;

    public CartScreen(Store store, Cart cart) {
        super();
        this.store = store;
        this.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        Platform.setImplicitExit(false);
        this.setLayout(new java.awt.BorderLayout());
        this.add(fxPanel, java.awt.BorderLayout.CENTER);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    CartScreenController controller = new CartScreenController(store, cart, CartScreen.this);
                    loader.setController(controller);
                    Parent root = (Parent) loader.load();
                    fxPanel.setScene(new javafx.scene.Scene(root));
                    
                    // Trigger Swing thread to validate and repaint layout
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        CartScreen.this.revalidate();
                        CartScreen.this.repaint();
                    });
                } catch (Throwable t) {
                    System.err.println("Error loading cart.fxml:");
                    t.printStackTrace();
                }
            }
        });
    }
}
