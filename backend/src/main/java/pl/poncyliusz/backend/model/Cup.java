package pl.poncyliusz.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cups")
@Data
public class Cup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    private String description;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "status")
    private boolean isActive;

    private String solution;

}
