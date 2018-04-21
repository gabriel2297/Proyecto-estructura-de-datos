package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Clase para manejar los cambios de fecha
 * 
 * @author gabriel
 */
public class cambiarFecha {

    /**
     * Método para darle formato a la fecha.
     */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    /**
     * Método que retorna la fecha como un string. 
     * 
     * @param date - la fecha que se quiere retornar como un string
     * @return - devuelve la fecha como String
     */
    public static String format(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

}
