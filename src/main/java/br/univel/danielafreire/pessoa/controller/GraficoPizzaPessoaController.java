/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 20:34:52
 */
package br.univel.danielafreire.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.univel.danielafreire.repository.PessoaRepository;

@Named(value="graficoPizzaPessoaController")
@RequestScoped
/**
 * Classe que irá controlar o grafico pizza
 */
public class GraficoPizzaPessoaController {
		
	@Inject
	private PessoaRepository pessoaRepository;
	private PieChartModel pieChartModel;
	
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}
 
	/**
	 * Metodo carregado ao abrir a pagina para que o grafico
	 * ja esteja carregado 
	 */
	@PostConstruct
	public  void init(){
		this.pieChartModel = new PieChartModel();
		this.MontaGrafico();
	}
 
	/***
	 * Faz a consulta, retorna os valores e monta o gráfico
	 */
	private void MontaGrafico(){
 
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();
		hashtableRegistros.forEach((chave,valor) -> {
			pieChartModel.set(chave, valor);
		});
 
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
 
 
	}	
}
