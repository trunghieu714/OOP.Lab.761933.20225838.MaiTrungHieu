package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.Iterator;
import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (track == null) return;
        if (tracks.contains(track)) {
            System.out.println("Track \"" + track.getTitle() + "\" is already in the CD.");
        } else {
            tracks.add(track);
            System.out.println("Track \"" + track.getTitle() + "\" added successfully to CD \"" + this.getTitle() + "\".");
        }
    }

    public void removeTrack(Track track) {
        if (track == null) return;
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track \"" + track.getTitle() + "\" removed successfully from CD \"" + this.getTitle() + "\".");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" does not exist in the CD.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle() + " by artist " + this.artist);
            System.out.println("CD director: " + this.getDirector());
            System.out.println("CD total length: " + this.getLength() + " mins");
            System.out.println("Playing tracks:");
            
            java.util.Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while(iter.hasNext()) {
                nextTrack = iter.next();
                try {
                    nextTrack.play();
                } catch(PlayerException e) {
                    throw e;
                }
            }
        } else {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + artist + " - " + getLength() + ": " + getCost() + " $";
    }
}
