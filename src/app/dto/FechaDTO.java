package app.dto;

/**
 *
 * @author Cesar Castillo
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FechaDTO
{

    private int mes;
    private int dia;
    private int año;
    private int hora;
    private int minuto;
    private int segundo;

    GregorianCalendar calendario = new GregorianCalendar();

    public FechaDTO()
    {
        this.mes = (calendario.get(Calendar.MONTH));
        this.dia = (calendario.get(Calendar.DAY_OF_MONTH));
        this.año = (calendario.get(Calendar.YEAR));
        this.hora = (calendario.get(Calendar.HOUR_OF_DAY));
        this.minuto = (calendario.get(Calendar.MINUTE));
        this.segundo = (calendario.get(Calendar.SECOND));
    }

    public int getMes()
    {
        return mes;
    }

    public int getDia()
    {
        return dia;
    }

    public int getAño()
    {
        return año;
    }

    public void setMes(int mes)
    {
        this.mes = mes;
    }

    public void setDia(int dia)
    {
        this.dia = dia;
    }

    public void setAño(int año)
    {
        this.año = año;
    }

    public int getHora()
    {
        return hora;
    }

    public int getMinuto()
    {
        return minuto;
    }

    public int getSegundo()
    {
        return segundo;
    }

    public void setHora(int hora)
    {
        this.hora = hora;
    }

    public void setMinuto(int minuto)
    {
        this.minuto = minuto;
    }

    public void setSegundo(int segundo)
    {
        this.segundo = segundo;
    }

    @Override
    public String toString()
    {
        String mensaje = "Fecha Revision :" + getDia() + "/" + (getMes() + 1) + "/" + getAño() + "\t" + "Hora: " + getHora() + ":" + getMinuto() + ":" + getSegundo();
        return mensaje;
    }

    public String toString2()
    {
        String mensaje = "Fecha: " +"" + getDia() + "/" + (getMes() + 1) + "/" + getAño() + "";
        return mensaje;
    }

}
