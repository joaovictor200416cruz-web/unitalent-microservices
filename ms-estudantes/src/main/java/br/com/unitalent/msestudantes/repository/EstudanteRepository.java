package br.com.unitalent.msestudantes.repository;
import br.com.unitalent.msestudantes.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {}
