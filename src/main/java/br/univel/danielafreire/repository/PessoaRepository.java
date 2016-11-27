/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 12:30:52
 */
package br.univel.danielafreire.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.model.UsuarioModel;
import br.univel.danielafreire.repository.entity.PessoaEntity;
import br.univel.danielafreire.repository.entity.UsuarioEntity;
import br.univel.danielafreire.uteis.Uteis;
 
//Classe para gravar a entidade pessoa 
public class PessoaRepository {
 
	@Inject
	PessoaEntity pessoaEntity;
	EntityManager entityManager;
 
	/*
	 * Metodo para salvar a entidade, a partir do pessoa model
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());
 
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); 
 
		pessoaEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(pessoaEntity);
 
	}
	
	/***
	 * M�todo para recupera��o dos dados de pessoa
	 */
	
	public List<PessoaModel> GetPessoas(){
 
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
		entityManager =  Uteis.JpaEntityManager();
		//Envio da query para encontrar todos os registros
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		//Cria��o da collection de PessoaEntity com os resultados da pesquisa
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
		PessoaModel pessoaModel = null;
		for (PessoaEntity pessoaEntity : pessoasEntity) {
 
			//Instancia do item a ser retornado e recupera��o dos dados
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
 
			//Verifica se a origem do cadastro foi via xml ou input
			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");
 
			//Verifica sexo da pessoa cadastrada e removendo a abrevia��o de M e F
			if(pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");
 
			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();			
			
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
 
			pessoaModel.setUsuarioModel(usuarioModel);
			pessoasModel.add(pessoaModel);
		}
 
		return pessoasModel;
 
	}
}
