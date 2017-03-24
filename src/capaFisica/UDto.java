package capaFisica;

import app.dto.DeptDTO;
import app.dto.EmpDTO;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Clase utilitaria que utilizamos para convertir a DTO las representaciones
 * alfanumericas que envia el server, lo que el server envia en realidad es el
 * toString de cada DTO y este metodo retorna los valores de sus atributos
 * separandolos con una , (coma). Asi que la manera mas facil para volver a
 * obtener el DTO original sera tokenizar el string y considerar el caracter,
 * (coma) como separador de tokens.
 *
 * @author fcastillo
 */
public class UDto
{

    public static DeptDTO stringToDeptDTO(String s)
    {
        DeptDTO dto = new DeptDTO();
        StringTokenizer st = new StringTokenizer(s, ",");

        dto.setDeptno(Integer.parseInt(st.nextToken()));
        dto.setDname(st.nextToken());
        dto.setLoc(st.nextToken());

        return dto;
    }

    /**
     * En este metodo el atributo hiredate esta tambien convertido a string y en
     * este caso cada atributo de la fecha esta separado por el caracter -
     * (Guion medio) asi que tambien habra que tokenizarlo
     *
     * @param s
     * @return
     */
    public static EmpDTO stringToEmpDTO(String s)
    {
        EmpDTO dto = new EmpDTO();
        StringTokenizer st = new StringTokenizer(s,",");

        dto.setEmpno(Integer.parseInt(st.nextToken()));
        dto.setEname(st.nextToken());

        // notacion hungara anteponer el tipo de datos de la variable
        String sHiredate = st.nextToken();

        dto.setDeptno(Integer.parseInt(st.nextToken().trim()));
     
        StringTokenizer stDate = new StringTokenizer(sHiredate,"-");
        int anio = Integer.parseInt(stDate.nextToken().trim());
        int mes = Integer.parseInt(stDate.nextToken().trim());
        int dia = Integer.parseInt(stDate.nextToken().trim());

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR,anio);
        gc.set(Calendar.MONTH,mes);
        gc.set(Calendar.DAY_OF_MONTH,dia);

        dto.setHiredate(new Date(gc.getTimeInMillis()));

        return dto;
    }
}
