package br.com.politica.partidos.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.politica.partidos.modelo.Associado;
import br.com.politica.partidos.repository.AssociadoRepository;

public class AssociadoForm {

	@NotNull @NotEmpty @Length(min = 3)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 7)
	@Pattern(regexp = "Vereador|Prefeito|Deputado Estadual|Deputado Federal|Senador|Governador|Presidente|Nehum",
    message="So e permitido os cargos: Vereador, Prefeito, Deputado Estadual, Deputado Federal, Senador, Governador, Presidente e Nehum")
	private String cargoPolitico;
	
    @NotNull
	private LocalDate dataNascimento;
	
	@NotNull @NotEmpty @Length(min = 8)
	@Pattern(regexp = "Masculino|Femenino|masculino|femenino",
    message="So e permitido as opcoes: masculino e femenino com o sem a primeira letra maiuscula")
	private String sexo;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargoPolitico() {
		return cargoPolitico;
	}
	public void setCargoPolitico(String cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}
	public LocalDate getDataNacimento() {
		return dataNascimento;
	}
	public void setDataNacimento(LocalDate dataNacimento) {
		this.dataNascimento = dataNacimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	public Associado converter(AssociadoRepository associadoRepository) {				
		return new Associado(nome, cargoPolitico, dataNascimento, sexo);
	}
	

}
