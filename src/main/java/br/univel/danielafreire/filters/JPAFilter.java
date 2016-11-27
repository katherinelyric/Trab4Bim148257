package br.univel.danielafreire.filters;
 
import java.io.IOException;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
 
/***
 * Daniela - 26/11  17:58
 * Filtro que ser� utilizado nas requisi��es para o faces servlet
 * */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {
 
 
	private EntityManagerFactory entityManagerFactory;
 
	private String persistence_unit_name = "unit_app";
 
    public JPAFilter() {
 
    }
 
    //Chamado ao encerrar a execu��o
	public void destroy() {
 
		this.entityManagerFactory.close();
	}
 
	//Processamento da requisi��o na transa��o
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
 
		request.setAttribute("entityManager", entityManager);
 
		entityManager.getTransaction().begin();
 
		chain.doFilter(request, response);
 
		try {
 
			// Faz commit caso n�o tenha erro na opera��o
			entityManager.getTransaction().commit();
 
		} catch (Exception e) {
 
			//se tiver erro, faz rollback
			entityManager.getTransaction().rollback();
		}
		finally{
 
			//Finaliza��o do entity
			entityManager.close();
		}
	}
 
	//Cria��o do entity de acordo com os parametros passados no arquivo de persistencia
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
	}
 
}