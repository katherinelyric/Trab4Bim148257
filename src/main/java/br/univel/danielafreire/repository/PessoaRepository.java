/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 12:30:52
 */
package br.univel.danielafreire.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.univel.danielafreire.model.PessoaModel;
import br.univel.danielafreire.repository.entity.PessoaEntity;
import br.univel.danielafreire.repository.entity.UsuarioEntity;
import br.univel.danielafreire.uteis.Uteis;
 
//Classe para gravar a entidade pessoa 
public class PessoaRepository {
 
	@Inject
	PessoaEntity pessoaEntity;
	EntityManager entityManager;
 
	//Metodo para salvar a entidade, a partir do pessoa model
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
}
