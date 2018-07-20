import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Comparable<Book> {

    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year,String... authors){

        this.setTitle(title);
        this.setYear(year);
        this.authors = new ArrayList<>();

        this.setAuthors(authors);

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(this.authors);
    }

    public void setAuthors(String... authors) {
        Collections.addAll(this.authors,authors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.getTitle())
                .append(" year: ").append(this.getYear())
                .append(" authors: ").append(String.join(", ",this.getAuthors()));

        return sb.toString();
    }

    @Override
    public int compareTo(Book comparingToBook) {
        if(this.getTitle().compareTo(comparingToBook.getTitle()) == 0)
            return Integer.compare(this.getYear(),comparingToBook.getYear());

        return this.getTitle().compareTo(comparingToBook.getTitle());
    }
}
