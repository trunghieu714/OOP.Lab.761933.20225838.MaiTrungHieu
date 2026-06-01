package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable {

    private String title;
    private int length;

    public Track() {
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (this.length <= 0) {
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        } else {
            System.out.println("Playing track: " + this.title + " - Track length: " + this.length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Track)) return false;
        Track track = (Track) o;
        if (this.length != track.length) return false;
        if (this.title == null) return track.title == null;
        return this.title.equalsIgnoreCase(track.title);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.toLowerCase().hashCode() : 0;
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        return "Track: " + title + " (" + length + " mins)";
    }
}
