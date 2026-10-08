package br.com.unitalent.mscandidaturas.model;
import jakarta.persistence.*;
@Entity
@Table(name="candidaturas")
public class Candidatura {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private Long estudanteId;
    @Column(nullable=false) private Long vagaId;
    @Column(nullable=false) private String status = "ENVIADA";
    public Candidatura() {}
    public Candidatura(Long estudanteId, Long vagaId, String status) {
        this.estudanteId=estudanteId; this.vagaId=vagaId; this.status=status;
    }
    public Long getId(){return id;}
    public Long getEstudanteId(){return estudanteId;}
    public void setEstudanteId(Long v){estudanteId=v;}
    public Long getVagaId(){return vagaId;}
    public void setVagaId(Long v){vagaId=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
}
