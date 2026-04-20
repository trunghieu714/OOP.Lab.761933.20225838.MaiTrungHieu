public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Animation", "The Lion King", 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Animation", "Aladdin", 18.99f);
        cart.addDigitalVideoDisc(dvd3);
        cart.print();

        System.out.println("Search by ID (2):");
        cart.searchById(2);
        
        System.out.println("Search by Title ('Aladdin'):");
        cart.searchByTitle("Aladdin");
    }
}
