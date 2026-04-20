public class Aims {
    public static void main(String[] args) {
        // Create a new cart
        Cart anOrder = new Cart();

        // Create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        // Test overloading version with array
        DigitalVideoDisc[] dvdList = {
            new DigitalVideoDisc("DVD 4"),
            new DigitalVideoDisc("DVD 5")
        };
        System.out.println("Calling with an array:");
        anOrder.addDigitalVideoDisc(dvdList);

        // Test overloading version with varargs
        System.out.println("Calling with arbitrary number of arguments:");
        anOrder.addDigitalVideoDisc(new DigitalVideoDisc("DVD 8"), new DigitalVideoDisc("DVD 9"), new DigitalVideoDisc("DVD 10"));

        // Test overloading version with 2 DVDs
        System.out.println("Adding two DVDs simultaneously...");
        anOrder.addDigitalVideoDisc(new DigitalVideoDisc("DVD 6"), new DigitalVideoDisc("DVD 7"));

        // Print total cost
        System.out.println("Total Cost is: " + anOrder.totalCost());
    }
}
