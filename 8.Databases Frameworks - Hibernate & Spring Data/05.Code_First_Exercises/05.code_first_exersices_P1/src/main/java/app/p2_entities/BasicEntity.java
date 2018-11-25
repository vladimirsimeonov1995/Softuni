package app.p2_entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BasicEntity {

    private int id;

    public BasicEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
