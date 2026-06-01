package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.exception.PlayerException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.SwingUtilities;

public class CartScreenController {
    private Cart cart;
    private Store store;
    private CartScreen cartScreen;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblTotalCost;

    @FXML
    private Button btnPlaceOrder;

    public CartScreenController(Store store, Cart cart, CartScreen cartScreen) {
        super();
        this.store = store;
        this.cart = cart;
        this.cartScreen = cartScreen;
    }

    @FXML
    private void initialize() {
        System.out.println("DEBUG: CartScreenController initialize() started.");
        try {
            colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
            colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
            colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

            // Set up FilteredList for filtering
            FilteredList<Media> filteredList = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
            tblMedia.setItems(filteredList);

            btnPlay.setVisible(false);
            btnRemove.setVisible(false);

            // Track selected items to update buttons
            tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    } else {
                        btnPlay.setVisible(false);
                        btnRemove.setVisible(false);
                    }
                }
            });

            // Set up Filter Listener
            tfFilter.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    showFilteredMedia(newValue, filteredList);
                }
            });

            updateTotalCost();
            System.out.println("DEBUG: CartScreenController initialize() completed successfully.");
        } catch (Throwable t) {
            System.err.println("DEBUG ERROR in CartScreenController initialize():");
            t.printStackTrace();
            throw t;
        }
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    private void showFilteredMedia(String filterString, FilteredList<Media> filteredList) {
        filteredList.setPredicate(media -> {
            if (filterString == null || filterString.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = filterString.toLowerCase().trim();
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerCaseFilter);
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            }
            return true;
        });
    }

    private void updateTotalCost() {
        lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText("Playing: " + media.getTitle());
                String content = "Type: " + media.getClass().getSimpleName() + "\n";
                if (media instanceof DigitalVideoDisc) {
                    content += "Length: " + ((DigitalVideoDisc) media).getLength() + " mins";
                } else if (media instanceof CompactDisc) {
                    content += "Artist: " + ((CompactDisc) media).getArtist() + "\nLength: " + ((CompactDisc) media).getLength() + " mins";
                }
                alert.setContentText(content);
                alert.showAndWait();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText("Illegal Media Length");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            updateTotalCost();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Cart");
            alert.setContentText("Your cart is empty. Cannot place order.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Success");
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();

        cart.emptyCart();
        updateTotalCost();
    }

    @FXML
    void menuAddBookPressed(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            new AddBookToStoreScreen(store, cart);
            cartScreen.dispose();
        });
    }

    @FXML
    void menuAddCDPressed(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            new AddCompactDiscToStoreScreen(store, cart);
            cartScreen.dispose();
        });
    }

    @FXML
    void menuAddDVDPressed(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            new AddDigitalVideoDiscToStoreScreen(store, cart);
            cartScreen.dispose();
        });
    }

    @FXML
    void menuViewStorePressed(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            new StoreScreen(store, cart);
            cartScreen.dispose();
        });
    }

    @FXML
    void menuViewCartPressed(ActionEvent event) {
        // Do nothing, already on Cart screen
    }
}
