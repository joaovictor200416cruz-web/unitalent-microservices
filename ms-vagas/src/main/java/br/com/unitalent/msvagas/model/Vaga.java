package br.com.unitalent.msvagas.model;
    import jakarta.persistence.*;
    @Entity
    @Table(name="vagas")
    public class Vaga {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
    private String titulo;
@Column(nullable=false)
    private String descricao;
private String requisitos;
@Column(nullable=false)
    private Long empresaId;
@Column(nullable=false)
    private String status;
        public Vaga() {}
        public Vaga(String titulo, String descricao, String requisitos, Long empresaId, String status) { this.titulo = titulo; this.descricao = descricao; this.requisitos = requisitos; this.empresaId = empresaId; this.status = status; }
        public Long getId() { return id; }
        public String getTitulo() { return titulo; }
public String getDescricao() { return descricao; }
public String getRequisitos() { return requisitos; }
public Long getEmpresaId() { return empresaId; }
public String getStatus() { return status; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
public void setDescricao(String descricao) { this.descricao = descricao; }
public void setRequisitos(String requisitos) { this.requisitos = requisitos; }
public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
public void setStatus(String status) { this.status = status; }
    }
