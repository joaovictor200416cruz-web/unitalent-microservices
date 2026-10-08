package br.com.unitalent.msempresas.repository;
import br.com.unitalent.msempresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {}
