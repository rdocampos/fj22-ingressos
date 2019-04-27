package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sessao {
	@Id
    @GeneratedValue
	private Integer id;
	private LocalTime horario;
	private Sala sala;
	private Filme filme;
	
	

}
