package br.com.politica.partidos.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
public class Associado {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cargoPolitico;
	private LocalDate dataNascimento;
	private String sexo;
	
	
	@ManyToOne
	private Partido partido;
			
	public Associado() {
		
	}
		
	public Associado(String nome, String cargoPolitico, LocalDate dataNascimento, String sexo) {
		super();
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
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

	
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	

}
