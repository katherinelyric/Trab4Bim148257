/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 17:52:49
 */
package br.univel.danielafreire.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value= LocalDateTimeConverter.ID)
/**
 * Classe para a conversao do LocalDateTime
 *
 */
public class LocalDateTimeConverter extends DateTimeConverter{

	public static final String ID="br.univel.danielafreire.uteis.LocalDateTimeConverter"; 
	 
	/*
	 * anula os valores de date e date time
	 * pega os valores passados na chamada do metodo e converte p�e em s� objeto,
	 * que � convertido e retornado no formado de localDateTime
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
 
		Date date = null;
		LocalDateTime localDateTime = null;
		Object object = super.getAsObject(facesContext, uiComponent, value);
 
		if(object instanceof Date){
 
			date = (Date) object;
			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	  		return localDateTime;
 
		}else{
 
			throw new IllegalArgumentException(String.format("value=%s N�o foi poss�vel converter LocalDateTime, resultado super.getAsObject=%s",value,object));			
		}			 
 
	}
 
	/*
	 * recebe os valores como string, e formata levando em considera��o
	 * a zona de horario em que estamos
	 */
	  @Override
	  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
 
		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);

		  if(value instanceof LocalDateTime){
 
			  LocalDateTime localDateTime = (LocalDateTime) value;
			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			  Date date =  Date.from(instant);
			  return super.getAsString(facesContext, uiComponent, date);
			  
		  }else{
 
			  throw new IllegalArgumentException(String.format("value=%s n�o � um LocalDateTime",value));  
		  }
 
	  }
}