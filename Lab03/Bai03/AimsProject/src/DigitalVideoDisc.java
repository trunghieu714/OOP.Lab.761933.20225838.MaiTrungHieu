public class DigitalVideoDisc {
    private String title;     
    private String category;  
    private String director;  
    private int length;       
    private float cost;     

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DigitalVideoDisc(String title) {
        super(); 
        this.title = title;
    }
}
