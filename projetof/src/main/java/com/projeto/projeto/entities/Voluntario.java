package com.projeto.projeto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "tb_voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String disponibilidade;
    private String habilidade;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public String getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

}

