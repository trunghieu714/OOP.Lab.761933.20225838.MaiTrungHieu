package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book {

    // Private fields
    private int id;
    private String title;
    private String category;
    private float cost;
    private ArrayList<String> authors = new ArrayList<String>();

    // Constructor from superclass (Object)
    public Book() {
        super();
    }

    // ── Getters & Setters (authors field excluded per instructions) ──────────

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    // ── Author management methods ────────────────────────────────────────────

    /**
     * Adds an author to the book's author list.
     * The author is only added if they are not already present in the list.
     *
     * @param authorName the name of the author to add
     */
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author \"" + authorName + "\" added successfully.");
        } else {
            System.out.println("Author \"" + authorName + "\" is already in the list.");
        }
    }

    /**
     * Removes an author from the book's author list.
     * The author is only removed if they are present in the list.
     *
     * @param authorName the name of the author to remove
     */
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Author \"" + authorName + "\" removed successfully.");
        } else {
            System.out.println("Author \"" + authorName + "\" is not in the list.");
        }
    }
}
