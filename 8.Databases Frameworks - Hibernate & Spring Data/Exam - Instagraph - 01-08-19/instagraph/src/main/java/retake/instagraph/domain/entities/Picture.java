package retake.instagraph.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "pictures")
public class Picture extends BaseEntity{

    private String path;
    private Double size;

    public Picture() {
    }

    @Column(name = "path", nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "size", nullable = false)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
