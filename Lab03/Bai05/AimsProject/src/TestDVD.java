public class TestDVD {
    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin");

        System.out.println("DVD 1 ID: " + dvd1.getId() + " - Title: " + dvd1.getTitle());
        System.out.println("DVD 2 ID: " + dvd2.getId() + " - Title: " + dvd2.getTitle());
        System.out.println("DVD 3 ID: " + dvd3.getId() + " - Title: " + dvd3.getTitle());
        
        // This proves that the static variable nbDigitalVideoDiscs is working
    }
}
