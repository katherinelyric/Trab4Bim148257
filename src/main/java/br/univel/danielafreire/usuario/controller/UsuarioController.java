package br.univel.danielafreire.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.univel.danielafreire.model.UsuarioModel;
import br.univel.danielafreire.repository.UsuarioRepository;
import br.univel.danielafreire.repository.entity.UsuarioEntity;
import br.univel.danielafreire.uteis.Uteis;

//Daniela 26/11  19:45
@Named(value="usuarioController")
//para serializar os objetos que serão intetados no bean
@SessionScoped


public class UsuarioController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject
	private UsuarioModel usuarioModel;
	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private UsuarioEntity usuarioEntity;
 
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	
	
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	//Retorna o usuário logado
	public UsuarioModel GetUsuarioSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	//finaliza a sessão e redireciona para o index
	public String Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}
	
	//Metodo para validar as informações de login
	public String EfetuarLogin(){
		
		//caso o usuario esteja vazio, retorna mensagem
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			
			Uteis.Mensagem("Favor informar o login!");
			return null;
			
			//Caso senha esteja vazia, retorna mensagem
		}else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			Uteis.Mensagem("Favor informara senha!");
			return null;
			
		}else{	
 
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
 
			if(usuarioEntity!= null){
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());
 
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				return "sistema/home?faces-redirect=true";
				
			}else{
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
				
			}
		}
	}
}
