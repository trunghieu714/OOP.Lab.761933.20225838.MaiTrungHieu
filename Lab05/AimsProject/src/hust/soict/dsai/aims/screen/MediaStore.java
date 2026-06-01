package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.exception.PlayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(null, 
                    "Added \"" + media.getTitle() + "\" to cart.", 
                    "Add to Cart", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        container.add(btnAddToCart);

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ((Playable) media).play();
                        
                        // Show JDialog for successful play
                        JDialog playDialog = new JDialog();
                        playDialog.setTitle("Playing " + media.getTitle());
                        playDialog.setSize(350, 200);
                        playDialog.setLayout(new GridBagLayout());
                        playDialog.setLocationRelativeTo(null);
                        
                        String textContent = "<html><center><h3 style='color:blue;'>Playing: " + media.getTitle() + "</h3>";
                        if (media instanceof DigitalVideoDisc) {
                            textContent += "<p>Length: " + ((DigitalVideoDisc) media).getLength() + " mins</p>";
                        } else if (media instanceof CompactDisc) {
                            CompactDisc cd = (CompactDisc) media;
                            textContent += "<p>Artist: " + cd.getArtist() + "</p>";
                            textContent += "<p>Total Length: " + cd.getLength() + " mins</p>";
                        }
                        textContent += "<br/><p style='color:green;'>Playing media content successfully!</p></center></html>";
                        
                        JLabel label = new JLabel(textContent);
                        playDialog.add(label);
                        playDialog.setVisible(true);
                    } catch (PlayerException ex) {
                        JOptionPane.showMessageDialog(null, 
                            ex.getMessage(), 
                            "Illegal Media Length", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            container.add(btnPlay);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
