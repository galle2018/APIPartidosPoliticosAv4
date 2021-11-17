package br.com.politica.partidos.controller.dto;

import java.time.format.DateTimeFormatter;

import br.com.politica.partidos.modelo.Associado;

public class DetalheDoAssociadoDto {
	
	private Long id;
	private String nome;
	private String cargoPolitico;
	private String dataNascimento;
	private String sexo;	
	private Long partido;
	
	public DetalheDoAssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dataformatadaBR = (associado.getDataNacimento().format(formatter));		
		System.out.println("Data com formato BR: "+dataformatadaBR);
		this.dataNascimento = dataformatadaBR;
		
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public Long getPartido() {
		return partido;
	}
	
	
	
	

}
