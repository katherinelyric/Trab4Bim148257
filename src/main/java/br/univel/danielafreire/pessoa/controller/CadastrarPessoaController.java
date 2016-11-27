/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 12:53:31
 */
package br.univel.danielafreire.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.repository.PessoaRepository;
import br.univel.danielafreire.usuario.controller.UsuarioController;
import br.univel.danielafreire.uteis.Uteis;
 
@Named(value="cadastrarPessoaController")
@RequestScoped
//Define que o registro será gravado através dos dados passados na interface de cadastro
public class CadastrarPessoaController {
 
	@Inject
	PessoaModel pessoaModel;
	@Inject
	UsuarioController usuarioController;
	@Inject
	PessoaRepository pessoaRepository;
 
	//getter e setter do modelo pessoa
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
//Salva a pessoa cadastrada na interface ~input~
	public void SalvarNovaPessoa(){
 
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		//estabelece que a fonte dos dados do cadastro, é o input
		pessoaModel.setOrigemCadastro("I");
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
		this.pessoaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}