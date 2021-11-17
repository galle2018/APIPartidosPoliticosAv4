package br.com.politica.partidos.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.politica.partidos.modelo.Associado;

public class AssociadoDto {

	private Long id;
	private String nome;
	private String cargoPolitico;		
	private String dataNacimento;
	private String sexo;
	private Long partido;
	
	public AssociadoDto(Associado associado) {		
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dataformatadaBR = (associado.getDataNacimento().format(formatter));		
		System.out.println("Data com formato BR: "+dataformatadaBR);
		this.dataNacimento = dataformatadaBR;
				
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido().getId();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCargoPolitico() {
		return cargoPolitico;
	}

	public String getDataNacimento() {
		return dataNacimento;
	}

	public String getSexo() {
		return sexo;
	}

	public static List<AssociadoDto> converter(List<Associado> associados) {		
		return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
	}

	public Long getPartido() {
		return partido;
	}
		
	
}
