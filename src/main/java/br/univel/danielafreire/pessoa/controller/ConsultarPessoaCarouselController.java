/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 20:08:41
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
 
@Named(value="consultarPessoaCarouselController")
@ViewScoped

/**
 *Classe para consulta de pessoas pelo carousel 
 */
public class ConsultarPessoaCarouselController implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private PessoaRepository pessoaRepository;
 
	@Produces 
	private List<PessoaModel> pessoas;
 
	/**
	 * Recuperação do List criado com os dados de PessoaModel
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
 
	/**
	 * Metodo que carrega os dados de pessoa na inicialização
	 */
	@PostConstruct
	private void init(){
		this.pessoas = pessoaRepository.GetPessoas();
	}
	
}
