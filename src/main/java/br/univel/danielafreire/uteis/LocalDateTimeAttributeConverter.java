/**
 * @author Daniela
 *
 * 27 de nov de 2016 - 13:14:29
 */
package br.univel.danielafreire.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
 
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
/**
 * Classe para corrigir o erro "Data truncation", convertendo local date time para timestamp
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	//Muda para timestamp ao enviar para o banco
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);
    	return null;
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    	if(timestamp != null)
    		return timestamp.toLocalDateTime();
    	return null;
    }
}