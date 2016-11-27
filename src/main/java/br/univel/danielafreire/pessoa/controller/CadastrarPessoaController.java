/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 12:53:31
 */
package br.univel.danielafreire.pessoa.controller;


import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.repository.PessoaRepository;
import br.univel.danielafreire.usuario.controller.UsuarioController;
import br.univel.danielafreire.uteis.Uteis;

 
@Named(value="cadastrarPessoaController")
@RequestScoped
/*
 * Define que o registro será gravado através dos dados passados na interface de cadastro
 */
public class CadastrarPessoaController {
 
	@Inject
	PessoaModel pessoaModel;
	@Inject
	UsuarioController usuarioController;
	@Inject
	PessoaRepository pessoaRepository;
	
	/*
	 * Parte para arquivos xml, getters e setters
	 */
	private UploadedFile file;
	 
	public UploadedFile getFile() {
		return file;
	}
 
	public void setFile(UploadedFile file) {
		this.file = file;
	}
 
	/*
	 * Getter e setter do modelo pessoa
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
/*
 * Salva a pessoa cadastrada na interface ~input~
 */
	public void SalvarNovaPessoa(){
 
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		//Estabelece que a fonte dos dados do cadastro, é o input
		pessoaModel.setOrigemCadastro("I");
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
		this.pessoaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
	
/*
 * Metodo para fazer o upload do arquivo
 */
	 public void UploadRegistros() {

		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 try {
			 if(this.file.getFileName().equals("")){ //identifica se um arquivo foi selecionado para upload
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }
			 DocumentBuilder builder = factory.newDocumentBuilder();

	                 Document doc = builder.parse(this.file.getInputstream());
	                 Element element = doc.getDocumentElement();
	                 NodeList nodes = element.getChildNodes();
 
	                for (int i = 0; i < nodes.getLength(); i++) {
	        	     Node node  = nodes.item(i);
	        	     
	        	    if(node.getNodeType() == Node.ELEMENT_NODE){
	        		 Element elementPessoa =(Element) node;
 
	        		 //Recupera os dados do arquivo xml, pela tag usada em cada atributo
	        		 String nome  = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();
 
	        		 //Instancia de uma nova pessoa, e adição de cada dado passado, para essa instancia
	        		 PessoaModel newPessoaModel = new PessoaModel();
	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);
 
	        		 //Registra o que foi extraido do xml
	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel);
	        	   }
	              }

		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");
 
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
 
	 }
 
}