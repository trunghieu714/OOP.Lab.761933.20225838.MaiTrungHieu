package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestCartScreen {
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        cart.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 88, 19.95f));
        cart.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));

        new CartScreen(store, cart);
    }
}
