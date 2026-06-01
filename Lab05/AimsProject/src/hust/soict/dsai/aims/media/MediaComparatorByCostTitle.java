package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {

    @Override
    public int compare(Media m1, Media m2) {
        if (m1 == null && m2 == null) return 0;
        if (m1 == null) return 1;
        if (m2 == null) return -1;
        
        // Higher cost first (descending)
        int costCompare = Float.compare(m2.getCost(), m1.getCost());
        if (costCompare != 0) {
            return costCompare;
        }
        
        String t1 = m1.getTitle() == null ? "" : m1.getTitle();
        String t2 = m2.getTitle() == null ? "" : m2.getTitle();
        return t1.compareToIgnoreCase(t2);
    }
}
