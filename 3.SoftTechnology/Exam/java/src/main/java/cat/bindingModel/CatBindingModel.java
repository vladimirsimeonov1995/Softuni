package cat.bindingModel;

public class CatBindingModel {

    private String name;

    private String nickname;

    private double price;


    public CatBindingModel() {
    }

    public CatBindingModel(String name, String nickname, double price) {
        this.name = name;
        this.nickname = nickname;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
