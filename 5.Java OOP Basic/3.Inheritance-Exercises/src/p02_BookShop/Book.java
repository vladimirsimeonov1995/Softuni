package p02_BookShop;

public class Book {

    private String title;
    private String author;
    private double price;



    public Book(String title,String author,double price){
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
    }

    protected void setTitle(String title){
        if(title.length() < 3){
            throw new IllegalArgumentException("Title not valid!");
        }

        this.title = title;
    }

    protected void setAuthor(String author){
        if(author.matches("[A-z]+ \\d[A-z]+"))
            throw new IllegalArgumentException("Author not valid!");

        this.author = author;
    }

    protected void setPrice(double price){
        if(price <= 0 )
            throw new IllegalArgumentException("Price not valid!");

        this.price = price;
    }

    @Override
    public String toString(){
        return String.format(
                "Title: %s\n" +
                "Author: %s\n" +
                "Price: %.1f\n",
                this.title,
                this.author,
                this.price);
    }


}
