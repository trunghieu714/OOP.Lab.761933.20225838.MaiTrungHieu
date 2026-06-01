package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Book;

import javax.swing.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add Book");
    }

    @Override
    protected void addSubclassFields(JPanel panel) {
        panel.add(new JLabel("Authors (comma separated):"));
        tfAuthors = new JTextField();
        panel.add(tfAuthors);
    }

    @Override
    protected void handleAdd(int id, String title, String category, float cost) {
        String authorsText = tfAuthors.getText().trim();
        Book book = new Book(id, title, category, cost);
        if (!authorsText.isEmpty()) {
            String[] authorsArray = authorsText.split(",");
            for (String author : authorsArray) {
                if (!author.trim().isEmpty()) {
                    book.addAuthor(author.trim());
                }
            }
        }
        store.addMedia(book);
        JOptionPane.showMessageDialog(null, "Book added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        new StoreScreen(store, cart);
        dispose();
    }
}
