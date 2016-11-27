/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 17:30:37
 */
package br.univel.danielafreire.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.repository.PessoaRepository;
 
@Named(value="consultarPessoaController")
@ViewScoped
/*
 * Classe para recolhimento dos dados e preenchimento
 * de uma list com os resultados de Pessoa
 */
public class ConsultarPessoaController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient
	private PessoaModel pessoaModel;
	@Produces 
	private List<PessoaModel> pessoas;
	@Inject transient
	private PessoaRepository pessoaRepository;
 
	/* 
	 * Getters e setters
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}		
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/***
	 * Carrega os dados de pessoa na inicializa��o
	 * para que a pagina j� abra com os dados carregados
	 */
	@PostConstruct
	public void init(){
		this.pessoas = pessoaRepository.GetPessoas();
	}
 
 
}
