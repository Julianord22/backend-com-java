package com.projeto.projeto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

@Entity
@Table(name = "tb_voluntario_projeto")
public class VoluntarioProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto_id;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario_id;

    @Column(nullable = false)
    private Date dataInicio;

    private Date dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projeto getProjeto_id() {
		return projeto_id;
	}

	public void setProjeto_id(Projeto projeto_id) {
		this.projeto_id = projeto_id;
	}

	public Voluntario getVoluntario_id() {
		return voluntario_id;
	}

	public void setVoluntario_id(Voluntario voluntario_id) {
		this.voluntario_id = voluntario_id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}

