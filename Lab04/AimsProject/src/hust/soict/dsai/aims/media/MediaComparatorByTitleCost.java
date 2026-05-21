package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {

    @Override
    public int compare(Media m1, Media m2) {
        if (m1 == null && m2 == null) return 0;
        if (m1 == null) return 1;
        if (m2 == null) return -1;
        
        String t1 = m1.getTitle() == null ? "" : m1.getTitle();
        String t2 = m2.getTitle() == null ? "" : m2.getTitle();
        
        int titleCompare = t1.compareToIgnoreCase(t2);
        if (titleCompare != 0) {
            return titleCompare;
        }
        // Higher cost first (descending)
        return Float.compare(m2.getCost(), m1.getCost());
    }
}
