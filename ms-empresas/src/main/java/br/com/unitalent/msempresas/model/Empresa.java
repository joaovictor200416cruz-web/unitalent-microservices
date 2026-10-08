package br.com.unitalent.msempresas.model;
    import jakarta.persistence.*;
    @Entity
    @Table(name="empresas")
    public class Empresa {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
    private String razaoSocial;
@Column(nullable=false)
    private String cnpj;
@Column(nullable=false)
    private String email;
private String descricao;
        public Empresa() {}
        public Empresa(String razaoSocial, String cnpj, String email, String descricao) { this.razaoSocial = razaoSocial; this.cnpj = cnpj; this.email = email; this.descricao = descricao; }
        public Long getId() { return id; }
        public String getRazaoSocial() { return razaoSocial; }
public String getCnpj() { return cnpj; }
public String getEmail() { return email; }
public String getDescricao() { return descricao; }
        public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
public void setCnpj(String cnpj) { this.cnpj = cnpj; }
public void setEmail(String email) { this.email = email; }
public void setDescricao(String descricao) { this.descricao = descricao; }
    }
