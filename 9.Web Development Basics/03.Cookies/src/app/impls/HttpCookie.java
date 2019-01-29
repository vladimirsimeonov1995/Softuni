package app.impls;

public class HttpCookie {

    private String key;
    private String value;

    public HttpCookie(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s <-> %s", key, value);
    }
}
