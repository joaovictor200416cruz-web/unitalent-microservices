package br.com.unitalent.mscandidaturas.repository;
import br.com.unitalent.mscandidaturas.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    boolean existsByEstudanteIdAndVagaId(Long estudanteId, Long vagaId);
}
