package mx.reinamadre.citas.utilerias;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*creador: MMA*/
@Slf4j
public class Formateador {

    public static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
    public static final String FORMATO_DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String MENSJE_ERROR_FORMATEAR = "Ocurrio un error al formatear ";

    private Formateador() {
    }

    public static String dateAFechaddmmyyyy(Date date){
        if(date != null){
            DateFormat dateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY);
            return dateFormat.format(date);
        }
        return null;
    }

    /*creador: MMA*/
    public static String dateAFechaddmmyyyyhhmmss(Date date){
        if(date != null){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
            return dateFormat.format(date);
        }
        return null;
    }

    public static String dateAFechayyyymmddhhmmss(Date date){
        if(date != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            return dateFormat.format(date);
        }
        return null;
    }

    /*creador: MMA*/
    public static String localDateTimeAFechaddmmyyyyhhmmss(LocalDateTime localDateTime){
        if(localDateTime != null){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(FORMATO_DDMMYYYY_HHMMSS);
            return dateFormat.format(localDateTime);
        }
        return null;
    }

    /*creador: MMA*/
    public static String timestampAFechaddmmyyyyhhmmss(Timestamp timestamp){
        if(timestamp != null){
            DateFormat dateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY_HHMMSS);
            return dateFormat.format(timestamp);
        }
        return null;
    }

    /*creador: MMA*/
    public static Date stringADate(String fechaString){
        Date date = null;
        if(fechaString != null){
            try {
                if(fechaString.length()<11){
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaString);
                } else if(fechaString.length()>11){
                    date = new SimpleDateFormat(FORMATO_DDMMYYYY_HHMMSS).parse(fechaString);
                }
            } catch (ParseException e) {
                log.error(MENSJE_ERROR_FORMATEAR, e);
            }
        }
        return date;
    }

    /*creador: MMA*/
    public static LocalDate stringALocalDate(String fechaString){
        LocalDate localDate = null;
        if(fechaString != null){
            try {
                if(fechaString.length()<11){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    localDate = LocalDate.parse(fechaString, formatter);
                } else if(fechaString.length()>11){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DDMMYYYY_HHMMSS);
                    localDate = LocalDate.parse(fechaString, formatter);
                }
            } catch (Exception e) {
                log.error(MENSJE_ERROR_FORMATEAR, e);
            }
        }
        return localDate;
    }

    /*creador: MMA*/
    public static LocalDateTime stringALocalDateTime(String fechaString){
        LocalDateTime dateTime = null;
        if(fechaString != null){
            try {
                if(fechaString.length()<11){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dateTime = LocalDateTime.parse(fechaString, formatter);
                } else if(fechaString.length()>11){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DDMMYYYY_HHMMSS);
                    dateTime = LocalDateTime.parse(fechaString, formatter);
                }
            } catch (Exception e) {
                log.error(MENSJE_ERROR_FORMATEAR, e);
            }
        }
        return dateTime;
    }
    
    public static LocalDateTime stringALocalDateTimeWithDash(String fechaString){
        LocalDateTime fechaFirmado = null;
       
        if(fechaString != null){
            try {
            	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		Date date = dateFormatter.parse(fechaString);
        		SimpleDateFormat outputDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY_HHMMSS);
        		String cadenaFormatoCorrecto = outputDateFormat.format(date);
        		fechaFirmado = stringALocalDateTime(cadenaFormatoCorrecto);
            } catch (Exception e) {
                log.error(MENSJE_ERROR_FORMATEAR, e);
            }
        }
        return fechaFirmado;
    }

    /*creador: MMA*/
    public static Date localDateTimeADate(LocalDateTime localDateTime){
        Date date = null;
        if(localDateTime != null){
            try {
                date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                log.error(MENSJE_ERROR_FORMATEAR, e);
            }
        }
        return date;
    }

    /*creador: MMA*/
    //crea el metodo para obtener la fecha UTC actual
    public static OffsetDateTime obtenerFechaDeOffsetDateTime(){
        OffsetDateTime fechaHora;
        fechaHora = OffsetDateTime.now(ZoneOffset.UTC);
        return fechaHora;
    }

    /*creador: MMA*/
    //crea el metodo para obtener la fecha UTC actual
    public static LocalDateTime obtenerFechaActualLocalDateTime(){
        ZonedDateTime  zoned  = obtenerFechaDeOffsetDateTime().atZoneSameInstant(ZoneId.of("GMT-6"));
        return zoned.toLocalDateTime();
    }


    public static ZonedDateTime getZonedDateTime(){  // handling ParseException
        Instant now = Instant.now();
        return now.atZone(ZoneId.of("UTC"));
    }
}
