package br.com.unitalent.msnotificacoes.model;
    import jakarta.persistence.*;
    @Entity
    @Table(name="notificacoes")
    public class Notificacao {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
    private String destinatario;
@Column(nullable=false)
    private String mensagem;
@Column(nullable=false)
    private String tipo;
@Column(nullable=false)
    private Boolean lida;
        public Notificacao() {}
        public Notificacao(String destinatario, String mensagem, String tipo, Boolean lida) { this.destinatario = destinatario; this.mensagem = mensagem; this.tipo = tipo; this.lida = lida; }
        public Long getId() { return id; }
        public String getDestinatario() { return destinatario; }
public String getMensagem() { return mensagem; }
public String getTipo() { return tipo; }
public Boolean getLida() { return lida; }
        public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
public void setMensagem(String mensagem) { this.mensagem = mensagem; }
public void setTipo(String tipo) { this.tipo = tipo; }
public void setLida(Boolean lida) { this.lida = lida; }
    }
