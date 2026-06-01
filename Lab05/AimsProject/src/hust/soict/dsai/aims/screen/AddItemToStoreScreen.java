package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;

    protected JTextField tfId;
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store, Cart cart, String titleName) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // North Panel (Menu bar and Header)
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader(titleName));
        cp.add(north, BorderLayout.NORTH);

        // Center Panel (Form)
        JPanel center = new JPanel(new GridLayout(0, 2, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        center.add(new JLabel("ID:"));
        tfId = new JTextField();
        center.add(tfId);

        center.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        center.add(tfCategory);

        center.add(new JLabel("Cost ($):"));
        tfCost = new JTextField();
        center.add(tfCost);

        addSubclassFields(center);
        cp.add(center, BorderLayout.CENTER);

        // South Panel (Buttons)
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("Add Item");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = tfId.getText().trim();
                    String title = tfTitle.getText().trim();
                    String category = tfCategory.getText().trim();
                    String costText = tfCost.getText().trim();

                    if (idText.isEmpty() || title.isEmpty() || category.isEmpty() || costText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all common fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int id = Integer.parseInt(idText);
                    float cost = Float.parseFloat(costText);

                    if (cost < 0) {
                        JOptionPane.showMessageDialog(null, "Cost cannot be negative!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    handleAdd(id, title, category, cost);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID and Cost must be valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        south.add(btnAdd);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreScreen(store, cart);
                dispose();
            }
        });
        south.add(btnCancel);
        cp.add(south, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(titleName);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected abstract void addSubclassFields(JPanel panel);
    protected abstract void handleAdd(int id, String title, String category, float cost);

    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(AddItemToStoreScreen.this instanceof AddBookToStoreScreen)) {
                    new AddBookToStoreScreen(store, cart);
                    dispose();
                }
            }
        });
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(AddItemToStoreScreen.this instanceof AddCompactDiscToStoreScreen)) {
                    new AddCompactDiscToStoreScreen(store, cart);
                    dispose();
                }
            }
        });
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(AddItemToStoreScreen.this instanceof AddDigitalVideoDiscToStoreScreen)) {
                    new AddDigitalVideoDiscToStoreScreen(store, cart);
                    dispose();
                }
            }
        });
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreScreen(store, cart);
                dispose();
            }
        });
        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(store, cart);
                dispose();
            }
        });
        menu.add(viewCart);

        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader(String headerTitleText) {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JLabel headerTitle = new JLabel(" - " + headerTitleText);
        headerTitle.setFont(new Font(headerTitle.getFont().getName(), Font.PLAIN, 30));

        JButton cartBtn = new JButton("View cart");
        cartBtn.setPreferredSize(new Dimension(100, 50));
        cartBtn.setMaximumSize(new Dimension(100, 50));
        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(store, cart);
                dispose();
            }
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(headerTitle);
        header.add(Box.createHorizontalGlue());
        header.add(cartBtn);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }
}
