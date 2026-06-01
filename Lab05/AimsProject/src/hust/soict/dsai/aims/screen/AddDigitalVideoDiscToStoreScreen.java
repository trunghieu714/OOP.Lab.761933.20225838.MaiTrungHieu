package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD");
    }

    @Override
    protected void addSubclassFields(JPanel panel) {
        panel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        panel.add(tfDirector);

        panel.add(new JLabel("Length:"));
        tfLength = new JTextField();
        panel.add(tfLength);
    }

    @Override
    protected void handleAdd(int id, String title, String category, float cost) {
        String director = tfDirector.getText().trim();
        String lengthText = tfLength.getText().trim();

        if (director.isEmpty() || lengthText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Director and Length cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int length = Integer.parseInt(lengthText);

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            dvd.setId(id);

            store.addMedia(dvd);
            JOptionPane.showMessageDialog(null, "DVD added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new StoreScreen(store, cart);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Length must be an integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
