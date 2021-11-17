package br.com.politica.partidos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.politica.partidos.controller.dto.DetalheDoPartidoComAssociadosDto;
import br.com.politica.partidos.controller.dto.DetalheDoPartidoDto;
import br.com.politica.partidos.controller.dto.PartidoDto;
import br.com.politica.partidos.controller.form.AtualizacaoPartidoForm;
import br.com.politica.partidos.controller.form.PartidoForm;
import br.com.politica.partidos.modelo.Partido;
import br.com.politica.partidos.repository.AssociadoRepository;
import br.com.politica.partidos.repository.PartidoRepository;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/partidos")
public class PartidosController {
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	
	// CADASTRAR -------------------------------------------------------------------------------------------------------
	@Operation(summary = "Cadastro de Partidos Politicos", 
			   description = "<b>A data deve ser ingressada no formato AAAA-MM-DD e sera armazenada no BD assim. "
			   		       + "Sera visualizado seu retorno no formato BR DD-MM-AAAA")	
	@PostMapping
	@Transactional
	public ResponseEntity<PartidoDto> cadastrar(@RequestBody @Valid PartidoForm form, UriComponentsBuilder uriBuilder) {
		
		Partido partido = form.converter(partidoRepository);
		partidoRepository.save(partido);
		
		URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDto(partido));
		
	}
	// CADASTRAR -------------------------------------------------------------------------------------------------------
	
		
	// LISTAR ------------------------------------------------------------------------------------------------------------------------
	@Operation(summary = "Listado de Partidos Politicos (Todos ou por Ideologia)", 
			   description = "<b>Se enviar o parametro * seram listados todos os partidos, "
			   		       + "se nao sera tratado de listar pela ideologia considerando a palavra que colocar. "
			   		       + "Se diferencia entre minuscula e maiuscula. Teste de ambas formas caso nao tenha resultado de primeira.")
	@GetMapping
	public List<PartidoDto> lista(String ideologia){		
		
	//if(ideologia == null) {
	  if(ideologia.equals("*")) {
		List<Partido> partidos = partidoRepository.findAll();
		return PartidoDto.converter(partidos);		
	}else {		
		List<Partido> partidos = partidoRepository.findByIdeologia(ideologia);
		return PartidoDto.converter(partidos);
		}		
	}
	// LISTAR ------------------------------------------------------------------------------------------
	
	
	// DETALHAR ----------------------------------------------------------------------------------------
	@Operation(summary = "Detalhe de Partido Politico por id sem seus associados")
	@GetMapping("/{id}")
	public ResponseEntity<DetalheDoPartidoDto> detalhar(@PathVariable Long id) {
		
		Optional<Partido> partido = partidoRepository.findById(id);
		
		if(partido.isPresent()) {
			return ResponseEntity.ok(new DetalheDoPartidoDto(partido.get()));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	// DETALHAR ----------------------------------------------------------------------------------------
	
		
	
	// DETALHAR ASOCIADOS DO PARTIDO -------------------------------------------------------------------
	@Operation(summary = "Detalhe de Partido Politico por id visualizando seus associados", 
			   description = "<b>Listado de Partidos Politicos incluindo seus associados e seus dados.")
	@GetMapping("/{id}/associados")
	public ResponseEntity<DetalheDoPartidoComAssociadosDto> detalharPartido(@PathVariable Long id) {
		
		Optional<Partido> partido = partidoRepository.findById(id);
		
		if(partido.isPresent()) {
			return ResponseEntity.ok(new DetalheDoPartidoComAssociadosDto(partido.get()));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	// DETALHAR ASOCIADOS DO PARTIDO -------------------------------------------------------------------
	
	
	// ATUALIZAR -------------------------------------------------------------------------------------------------------
	@Operation(summary = "Atualizacao de Partido Politico por id e envio de dados", 
			   description = "<b>A data deve ser ingressada no formato AAAA-MM-DD e sera armazenada no BD assim. "
			   		       + "Sera visualizado seu retorno no formato BR DD-MM-AAAA")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPartidoForm form){
		
		Optional<Partido> optional = partidoRepository.findById(id);
		
		if(optional.isPresent()) {
			Partido partido = form.atualizar(id, partidoRepository);
			return ResponseEntity.ok(new PartidoDto(partido));
		}
		return ResponseEntity.notFound().build();
		
	}
	// ATUALIZAR -------------------------------------------------------------------------------------------------------
	
	
	// DELETE -------------------------------------------------------------------
	@Operation(summary = "Eliminacao de Partido Politico por id",
			   description = "<b>Remove um partido desde que este nao contenha associados. "
			   		       + "Se conter associados, primeiramente devesse eliminarlos e ai sim podera ser removido. Caso contrario sera retornado um erro 500 pois ainda nao foi tratado este erro.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		
		Optional<Partido> optional = partidoRepository.findById(id);
				
			if(optional.isPresent()) { 
				partidoRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}		
		
		return ResponseEntity.notFound().build();
	}
	// DELETE -------------------------------------------------------------------
	
	
	
	
	
}
