public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Animation", "The Lion King", 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);

        store.addDVD(dvd1);
        store.addDVD(dvd2);

        store.removeDVD(dvd1);
        store.removeDVD(new DigitalVideoDisc("Test"));
    }
}
