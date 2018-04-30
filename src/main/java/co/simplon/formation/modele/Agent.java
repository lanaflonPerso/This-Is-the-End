package co.simplon.formation.modele;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "agent")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifiantRh", unique=true)
    @Size(min=6, max=6)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9]{3}$")
    private String idRh;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

    @Column(nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    @JsonIgnoreProperties( value = {"agent"})
    private Seance seance;

    @ManyToOne
    @JoinColumn(name = "flux_id")
    @JsonIgnoreProperties( value = {"agent"})
    private Flux flux;

    @ManyToOne
    @JoinColumn(name = "ilot_id")
    @JsonIgnoreProperties( value = {"agent"})
    private Ilot ilot;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    @JsonIgnoreProperties( value = {"agent"})
    private Grade grade;
}
