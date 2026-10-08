package br.com.unitalent.msnotificacoes.repository;
import br.com.unitalent.msnotificacoes.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {}
