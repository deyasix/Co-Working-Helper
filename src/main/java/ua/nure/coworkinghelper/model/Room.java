package ua.nure.coworkinghelper.model;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coworkingid", referencedColumnName = "id")
    private CoWorking coWorking;

    @Column(name="name")
    private String name;

    @Column(name="humanity")
    private Integer humanity;

    @Column(name="temperature")
    private Integer temperature;

    @Column(name="lighting")
    private Integer lighting;

    @Column(name="square")
    private Integer square;

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoWorking getCoWorking() {
        return coWorking;
    }

    public void setCoWorking(CoWorking coWorking) {
        this.coWorking = coWorking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHumanity() {
        return humanity;
    }

    public void setHumanity(Integer humanity) {
        this.humanity = humanity;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getLighting() {
        return lighting;
    }

    public void setLighting(Integer lighting) {
        this.lighting = lighting;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room() {}
    public Room(String name, Integer humanity, Integer temperature, Integer lighting, Integer square,
                String description, CoWorking coWorking) {
        this.name = name;
        this.humanity = humanity;
        this.temperature = temperature;
        this.lighting = lighting;
        this.square = square;
        this.description = description;
        this.coWorking = coWorking;
    }
}
