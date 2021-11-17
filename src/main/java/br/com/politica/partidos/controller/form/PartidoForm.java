package br.com.politica.partidos.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.politica.partidos.modelo.Partido;
import br.com.politica.partidos.repository.PartidoRepository;

public class PartidoForm {
	
	@NotNull @NotEmpty @Length(min = 6)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 2)
	private String sigla;
	
	@NotNull @NotEmpty @Length(min = 6)
	@Pattern(regexp = "Direita|Centro|Esquerda|direita|centro|esquerda",
    message="So e permitido as ideologias: direita, centro e esquerda com o sem a primeira letra maiuscula")
	private String ideologia;
	
	@NotNull
	private LocalDate dataFundacao;	

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getIdeologia() {
		return ideologia;
	}
	public void setIdeologia(String ideologia) {
		this.ideologia = ideologia;
	}
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Partido converter(PartidoRepository partidoRepository) {
		return new Partido(nome, sigla, ideologia, dataFundacao);
	}

}
