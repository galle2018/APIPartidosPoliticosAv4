package br.com.politica.partidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.politica.partidos.modelo.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{

	List<Associado> findByCargoPolitico(String cargoPolitico);

}
