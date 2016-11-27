/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 20:55:13
 */
package br.univel.danielafreire.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.repository.PessoaRepository;
 
@Named(value="exportarRegistrosXmlController")
@RequestScoped

/**
 * Classe de exportação/backup dos registros via arquivo xml
 */
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = 1L;
 
	@Inject transient
	PessoaRepository pessoaRepository; 
	private StreamedContent arquivoDownload;
 
	/***
	 *Baixa o arquivo xml
	 */
	public StreamedContent getArquivoDownload() {
		this.DownlaodArquivoXmlPessoa();
		return arquivoDownload;
	}
 
	/***
	 * Prepara o arquivo para ser exportado
	 */
	private File GerarXmlPessoas(){
		//Formatação da data e hora
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		List<PessoaModel> pessoasModel = pessoaRepository.GetPessoas();
		Element elementPessoas = new Element("Pessoas");
		Document documentoPessoas = new Document(elementPessoas);
		pessoasModel.forEach(pessoa -> {
 
 
			//Montagem do xml de acordo com os seus campos
			Element elementPessoa = new Element("Pessoa");			
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));
			//Formatação da data para o arquivo
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
			elementPessoas.addContent(elementPessoa);
		});
 
 
		XMLOutputter xmlGerado = new XMLOutputter();
		try {
			//Geração do nome do arquivo, seleção da pasta de envio
			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
			
			//Alterar a pasta para que funcione ao realizar a exportação
			File arquivo = new File("C:\\Users\\Danii\\git\\Trab4Bim148257\\src\\main\\resources\\META-INF\\".concat(nomeArquivo));
			FileWriter fileWriter =  new FileWriter(arquivo);
			xmlGerado.output(documentoPessoas, fileWriter);
			return arquivo;
 
		} catch (Exception ex) {
			ex.printStackTrace();
		}		

		return null;
	}
 
	/***
	 * Gravação dos dados 
	 */
	public void DownlaodArquivoXmlPessoa(){
		
		File arquivoXml = this.GerarXmlPessoas();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(arquivoXml.getPath());
			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
 
	}
}
