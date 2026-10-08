package br.com.unitalent.msestudantes.model;
    import jakarta.persistence.*;
    @Entity
    @Table(name="estudantes")
    public class Estudante {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
    private String nome;
@Column(nullable=false)
    private String email;
@Column(nullable=false)
    private String curso;
private String habilidades;
private String curriculoUrl;
        public Estudante() {}
        public Estudante(String nome, String email, String curso, String habilidades, String curriculoUrl) { this.nome = nome; this.email = email; this.curso = curso; this.habilidades = habilidades; this.curriculoUrl = curriculoUrl; }
        public Long getId() { return id; }
        public String getNome() { return nome; }
public String getEmail() { return email; }
public String getCurso() { return curso; }
public String getHabilidades() { return habilidades; }
public String getCurriculoUrl() { return curriculoUrl; }
        public void setNome(String nome) { this.nome = nome; }
public void setEmail(String email) { this.email = email; }
public void setCurso(String curso) { this.curso = curso; }
public void setHabilidades(String habilidades) { this.habilidades = habilidades; }
public void setCurriculoUrl(String curriculoUrl) { this.curriculoUrl = curriculoUrl; }
    }
