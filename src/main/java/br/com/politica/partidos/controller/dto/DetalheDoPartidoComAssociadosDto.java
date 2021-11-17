package br.com.politica.partidos.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.politica.partidos.modelo.Associado;
import br.com.politica.partidos.modelo.Partido;

public class DetalheDoPartidoComAssociadosDto {
	
	private Long id;
	private String nome;
	private String sigla;
	private String ideologia;
	private String dataFundacao;
	private List<AssociadoDto> associados;
	
	public DetalheDoPartidoComAssociadosDto(Partido partido) {
		this.id = partido.getId();
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataformatadaBR = (partido.getDataFundacao().format(formatter));		
		System.out.println("Data com formato BR: "+dataformatadaBR);
		this.dataFundacao = dataformatadaBR;
						
		//AUN NO ANDA ESTA PARTE DE MOSTRAR LOS ASOCIADOS DEL PARTIDO ----------------------------------------------
		this.associados = new ArrayList<>();
		this.associados.addAll(partido.getAssociados().stream().map(AssociadoDto::new).collect(Collectors.toList()));
		
		//----------------------------------------------------------------------------------------------------------
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSigla() {
		return sigla;
	}
	public String getIdeologia() {
		return ideologia;
	}
	public String getDataFundacao() {
		return dataFundacao;
	}
	public List<AssociadoDto> getAsociados() {
		return associados;
	}
	

}
