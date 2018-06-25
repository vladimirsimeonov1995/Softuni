package p02_BookShop;

public class GoldenEditionBook extends Book {

    public GoldenEditionBook(String title,String author,double price){
        super(title,author,price);
        this.setPrice(price);
    }

    @Override
    protected void setPrice(double price){
        price *= 1.3;
        super.setPrice(price);
    }



}
