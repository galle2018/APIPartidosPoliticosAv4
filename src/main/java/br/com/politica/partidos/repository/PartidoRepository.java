package br.com.politica.partidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.politica.partidos.modelo.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long>{

	List<Partido> findByIdeologia(String ideologia);
}
