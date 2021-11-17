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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.politica.partidos.controller.dto.AssociadoDto;
import br.com.politica.partidos.controller.dto.DetalheDoAssociadoDto;
import br.com.politica.partidos.controller.form.AssociadoForm;
import br.com.politica.partidos.modelo.Associado;
import br.com.politica.partidos.repository.AssociadoRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/associados")
public class AssociadosController {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	
	
	// LISTAR -----------------------------------------------------------------------------------------
	@Operation(summary = "Listado de Associados (Todos ou por cargo politico)", 
			   description = "<b>Se enviar o parametro * seram listados todos os associados, "
			   		       + "se nao sera tratado de listar pelo cargo politico considerando a palavra que colocar. "
			   		       + "Se diferencia entre minuscula e maiuscula. Teste de ambas formas caso nao tenha resultado de primeira.")
	@GetMapping
	public List<AssociadoDto> lista(String cargoPolitico){
		//if(cargoPolitico == null) {
		if(cargoPolitico.equals("*")) {
			List<Associado> associados = associadoRepository.findAll();
			return AssociadoDto.converter(associados);		
	}else {
		List<Associado> associados = associadoRepository.findByCargoPolitico(cargoPolitico);
		return AssociadoDto.converter(associados);
		}		
	}
	
	// LISTAR ------------------------------------------------------------------------------------------
	
	
	// DETALHAR ----------------------------------------------------------------------------------------
	@Operation(summary = "Detalhe de Associado por id", 
			   description = "<b>Detalhes do Associado considerando seu id")
	@GetMapping("/{id}")
	public ResponseEntity<DetalheDoAssociadoDto> detalhar(@PathVariable Long id) {
		
		Optional<Associado> associado = associadoRepository.findById(id);
		
		if(associado.isPresent()) {
			return ResponseEntity.ok(new DetalheDoAssociadoDto(associado.get()));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	// DETALHAR ----------------------------------------------------------------------------------------
	
	
	// DELETE -------------------------------------------------------------------
	@Operation(summary = "Remover Associado por id", 
			   description = "<b>Remove um Associado considerando seu id se existente no BD retornando codigo 200 OK")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		
		Optional<Associado> optional = associadoRepository.findById(id);
		
		if(optional.isPresent()) {
			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	// DELETE -------------------------------------------------------------------
	

}
