package co.simplon.formation.modele;

import co.simplon.formation.service.LettreConvocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itextpdf.text.DocumentException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seance")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Seance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    @Column
    private Integer nbrePersonne;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;

    @Column(columnDefinition="BOOLEAN DEFAULT true")
    private Boolean validation;

    @Column(nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;


    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "seance_id", referencedColumnName = "id", nullable = true)
    @JsonIgnoreProperties("seance")
    private Set<Agent> agent = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties(value = {"seance", "formateur", "habilitation", "salle"})
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonIgnoreProperties(value = {"seance", "formation", "habilitation", "salle"})
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonIgnoreProperties(value = {"seance", "formation", "formateur", "habilitation"})
    private Salle salle;

}
