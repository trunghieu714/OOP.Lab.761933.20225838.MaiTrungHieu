package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;

import javax.swing.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfDirector;
    private JTextField tfTracks;

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD");
    }

    @Override
    protected void addSubclassFields(JPanel panel) {
        panel.add(new JLabel("Artist:"));
        tfArtist = new JTextField();
        panel.add(tfArtist);

        panel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        panel.add(tfDirector);

        panel.add(new JLabel("Tracks (format: title1:len1;title2:len2):"));
        tfTracks = new JTextField();
        panel.add(tfTracks);
    }

    @Override
    protected void handleAdd(int id, String title, String category, float cost) {
        String artist = tfArtist.getText().trim();
        String director = tfDirector.getText().trim();
        String tracksText = tfTracks.getText().trim();

        if (artist.isEmpty() || director.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Artist and Director cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CompactDisc cd = new CompactDisc(id, title, category, cost, director, artist);
        if (!tracksText.isEmpty()) {
            try {
                String[] trackItems = tracksText.split(";");
                for (String item : trackItems) {
                    if (item.trim().isEmpty()) continue;
                    String[] parts = item.split(":");
                    if (parts.length == 2) {
                        String trackTitle = parts[0].trim();
                        int trackLength = Integer.parseInt(parts[1].trim());
                        cd.addTrack(new Track(trackTitle, trackLength));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid track format. Use 'title:length' separated by ';'", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Track length must be an integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        store.addMedia(cd);
        JOptionPane.showMessageDialog(null, "CD added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        new StoreScreen(store, cart);
        dispose();
    }
}
