package br.univel.danielafreire.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.univel.danielafreire.model.UsuarioModel;
import br.univel.danielafreire.repository.entity.UsuarioEntity;
import br.univel.danielafreire.uteis.Uteis;

//Daniela 26/11 19:42
public class UsuarioRepository implements Serializable {
	 
	private static final long serialVersionUID = 1L;
 
	//Metodo para validação de usuário
	//query e seus parametros a serem usados e o retorno do usuario caso seja encontrado
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
 
		try {

			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			return (UsuarioEntity)query.getSingleResult();
 
		} catch (Exception e) {
 
			return null;
		}
	}
}
