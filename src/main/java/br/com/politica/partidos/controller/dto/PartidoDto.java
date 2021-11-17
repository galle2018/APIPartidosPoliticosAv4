package br.com.politica.partidos.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.politica.partidos.modelo.Partido;

public class PartidoDto {
	
	private Long id;
	private String nome;
	private String sigla;
	private String ideologia;
	private String dataFundacao;
		

	public PartidoDto(Partido partido) {		
		this.id = partido.getId();
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dataformatadaBR = (partido.getDataFundacao().format(formatter));		
		System.out.println("Data com formato BR: "+dataformatadaBR);
		this.dataFundacao = dataformatadaBR;
			
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getIdeologia() {
		return ideologia;
	}
	public String getDataFundacao() {
		return dataFundacao;
	}


	public static List<PartidoDto> converter(List<Partido> partidos) {		
		return partidos.stream().map(PartidoDto::new).collect(Collectors.toList());
	}

	public String getSigla() {
		return sigla;
	}

}
