package pl.poncyliusz.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(name = "status")
    private boolean isActive;
}
