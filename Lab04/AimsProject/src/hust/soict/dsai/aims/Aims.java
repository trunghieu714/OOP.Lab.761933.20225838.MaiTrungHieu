package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Aims {

    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        bootstrapStore();
        mainMenuLoop();
    }

    private static void bootstrapStore() {
        // Boostrap store with some items
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 88, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);
        
        CompactDisc cd1 = new CompactDisc(4, "Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson");
        cd1.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd1.addTrack(new Track("Thriller", 6));
        cd1.addTrack(new Track("Beat It", 4));
        
        CompactDisc cd2 = new CompactDisc(5, "Abbey Road", "Rock", 20.0f, "George Martin", "The Beatles");
        cd2.addTrack(new Track("Come Together", 4));
        cd2.addTrack(new Track("Something", 3));

        Book book1 = new Book(6, "Java Programming", "Education", 45.0f);
        book1.addAuthor("Herbert Schildt");

        Book book2 = new Book(7, "Design Patterns", "Computer Science", 55.0f, new ArrayList<String>(Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides")));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(book1);
        store.addMedia(book2);

        System.out.println("\n--- Store bootstrapped with 7 media items ---\n");
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    private static void mainMenuLoop() {
        int choice = -1;
        while (choice != 0) {
            showMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewStoreFlow();
                    break;
                case 2:
                    updateStoreFlow();
                    break;
                case 3:
                    seeCurrentCartFlow();
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private static void viewStoreFlow() {
        int choice = -1;
        while (choice != 0) {
            store.print();
            storeMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    seeMediaDetailsFlow();
                    break;
                case 2:
                    addMediaToCartFlow();
                    break;
                case 3:
                    playMediaFlow();
                    break;
                case 4:
                    seeCurrentCartFlow();
                    break;
                case 0:
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private static void seeMediaDetailsFlow() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine().trim();
        Media media = store.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in the store.");
            return;
        }

        System.out.println("\nMedia Details:");
        System.out.println(media.toString());

        int choice = -1;
        while (choice != 0) {
            mediaDetailsMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    if (media instanceof DigitalVideoDisc) {
                        System.out.println("Number of DVDs currently in cart: " + cart.getDvdCount());
                    }
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("Error: Books cannot be played.");
                    }
                    break;
                case 0:
                    // Back to store menu
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private static void addMediaToCartFlow() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine().trim();
        Media media = store.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in the store.");
            return;
        }
        cart.addMedia(media);
        if (media instanceof DigitalVideoDisc) {
            System.out.println("Number of DVDs currently in cart: " + cart.getDvdCount());
        }
    }

    private static void playMediaFlow() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine().trim();
        Media media = store.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in the store.");
            return;
        }
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Error: This media type (" + media.getClass().getSimpleName() + ") cannot be played.");
        }
    }

    private static void updateStoreFlow() {
        System.out.println("\nUpdate Store:");
        System.out.println("1. Add a media to the store");
        System.out.println("2. Remove a media from the store");
        System.out.print("Please choose (1-2): ");
        int action = -1;
        try {
            action = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (action == 1) {
            System.out.println("Select media type: ");
            System.out.println("1. DVD");
            System.out.println("2. CD");
            System.out.println("3. Book");
            System.out.print("Choose (1-3): ");
            int type = -1;
            try {
                type = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Enter Category: ");
            String category = scanner.nextLine().trim();
            System.out.print("Enter Cost: ");
            float cost = Float.parseFloat(scanner.nextLine().trim());

            if (type == 1) {
                System.out.print("Enter Director: ");
                String director = scanner.nextLine().trim();
                System.out.print("Enter Length: ");
                int length = Integer.parseInt(scanner.nextLine().trim());
                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                dvd.setId(id);
                store.addMedia(dvd);
            } else if (type == 2) {
                System.out.print("Enter Director: ");
                String director = scanner.nextLine().trim();
                System.out.print("Enter Artist: ");
                String artist = scanner.nextLine().trim();
                CompactDisc cd = new CompactDisc(id, title, category, cost, director, artist);
                
                System.out.print("How many tracks to add? ");
                int numTracks = Integer.parseInt(scanner.nextLine().trim());
                for (int i = 0; i < numTracks; i++) {
                    System.out.println("Track #" + (i + 1) + ":");
                    System.out.print("  Title: ");
                    String trackTitle = scanner.nextLine().trim();
                    System.out.print("  Length: ");
                    int trackLength = Integer.parseInt(scanner.nextLine().trim());
                    cd.addTrack(new Track(trackTitle, trackLength));
                }
                store.addMedia(cd);
            } else if (type == 3) {
                Book book = new Book(id, title, category, cost);
                System.out.print("Enter authors (separated by commas): ");
                String authorsInput = scanner.nextLine().trim();
                if (!authorsInput.isEmpty()) {
                    String[] authorsArray = authorsInput.split(",");
                    for (String author : authorsArray) {
                        book.addAuthor(author.trim());
                    }
                }
                store.addMedia(book);
            } else {
                System.out.println("Invalid media type.");
            }
        } else if (action == 2) {
            System.out.print("Enter the title of the media to remove: ");
            String title = scanner.nextLine().trim();
            Media media = store.searchByTitle(title);
            if (media == null) {
                System.out.println("Media not found in the store.");
            } else {
                store.removeMedia(media);
            }
        } else {
            System.out.println("Invalid update option.");
        }
    }

    private static void seeCurrentCartFlow() {
        int choice = -1;
        while (choice != 0) {
            cart.print();
            cartMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    filterCartFlow();
                    break;
                case 2:
                    sortCartFlow();
                    break;
                case 3:
                    removeMediaFromCartFlow();
                    break;
                case 4:
                    playMediaFromCartFlow();
                    break;
                case 5:
                    System.out.println("Placing order...");
                    cart.emptyCart();
                    System.out.println("An order has been created successfully!");
                    choice = 0; // Go back to store menu/main menu after placing order
                    break;
                case 0:
                    // Back to previous menu
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private static void filterCartFlow() {
        System.out.println("Filter options: ");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        System.out.print("Choose (1-2): ");
        int opt = -1;
        try {
            opt = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (opt == 1) {
            System.out.print("Enter ID to filter: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            cart.filterById(id);
        } else if (opt == 2) {
            System.out.print("Enter Title containing to filter: ");
            String sub = scanner.nextLine().trim();
            cart.filterByTitle(sub);
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void sortCartFlow() {
        System.out.println("Sort options: ");
        System.out.println("1. Sort by Title then Cost");
        System.out.println("2. Sort by Cost then Title");
        System.out.print("Choose (1-2): ");
        int opt = -1;
        try {
            opt = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (opt == 1) {
            cart.sortByTitle();
        } else if (opt == 2) {
            cart.sortByCost();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void removeMediaFromCartFlow() {
        System.out.print("Enter the title of the media to remove from cart: ");
        String title = scanner.nextLine().trim();
        Media media = cart.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in the cart.");
        } else {
            cart.removeMedia(media);
        }
    }

    private static void playMediaFromCartFlow() {
        System.out.print("Enter the title of the media in cart to play: ");
        String title = scanner.nextLine().trim();
        Media media = cart.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in the cart.");
            return;
        }
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Error: This media type (" + media.getClass().getSimpleName() + ") cannot be played.");
        }
    }
}
