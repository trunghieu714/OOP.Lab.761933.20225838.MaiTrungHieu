package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) return;
        if (itemsInStore.contains(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the store.");
        } else {
            itemsInStore.add(media);
            System.out.println("Added \"" + media.getTitle() + "\" to the store. Total items in store: " + itemsInStore.size());
        }
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("Removed \"" + media.getTitle() + "\" from the store. Remaining items: " + itemsInStore.size());
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the store.");
        }
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void print() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in Store:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("***************************************************");
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}
