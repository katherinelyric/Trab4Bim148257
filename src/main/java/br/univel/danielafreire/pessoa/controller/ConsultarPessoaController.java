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
	
	/***
	 * Recupera as informa��es da pessoa que ser� alterada
	 * Utiliza somente o M ou o F do sexo para utilizar na persistencia
	 */
	public void Editar(PessoaModel pessoaModel){
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
		this.pessoaModel = pessoaModel;
	}
	/***
	 * Atualiza��o dos registros alterados
	 */
	public void AlterarRegistro(){
		this.pessoaRepository.AlterarRegistro(this.pessoaModel);
		this.init();
	}
	
	/***
	 * M�todo que chama a exclus�o do registro e atualiza��o da lista
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){
		//chama a exclus�o passando o codigo
		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());
		//remo��o do item da exibi��o
		this.pessoas.remove(pessoaModel);
	}
 
 
}
