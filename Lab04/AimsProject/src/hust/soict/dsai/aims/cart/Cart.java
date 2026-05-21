package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) return;
        if (itemsOrdered.contains(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("Added \"" + media.getTitle() + "\" to the cart. Total items: " + itemsOrdered.size());
        }
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Removed \"" + media.getTitle() + "\" from the cart. Remaining items: " + itemsOrdered.size());
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void filterById(int id) {
        boolean found = false;
        System.out.println("Filtering cart by ID: " + id);
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println(media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media with ID " + id + " found in the cart.");
        }
    }

    public void filterByTitle(String title) {
        boolean found = false;
        System.out.println("Filtering cart by title containing: \"" + title + "\"");
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media with title containing \"" + title + "\" found in the cart.");
        }
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart sorted by title then cost.");
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart sorted by cost then title.");
    }

    public int getDvdCount() {
        int count = 0;
        for (Media media : itemsOrdered) {
            if (media instanceof hust.soict.dsai.aims.media.DigitalVideoDisc) {
                count++;
            }
        }
        return count;
    }

    public void emptyCart() {
        itemsOrdered.clear();
        System.out.println("The cart has been emptied.");
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}
