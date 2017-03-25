
package app.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * En el constructor instanciaremos minsize conexiones y las vamos a mantener en 
 * un ArrayList que llamaremos libres (ya que al inicio estas conexiones estaran
 * libres). 
 * 
 * Se definen 2 metodos : getConnection y releaseConnection. El primero
 * para "pedir prestada" una conexion y el segundo para devolverla. 
 * 
 * En el metodo getConnection, tomamos la primera conexion del ArrayList libres
 * (siempre y cuando este tenga elementos),la pasamos a otro ArrayList llamado 
 * usadas y luego retornamos una referencia a esta conexion.
 * 
 * Si se invoca al metodo getConnection y el ArrayList libres no tiene elementos
 * entonces instanciaremos n nuevas conexiones siendo n el minimo valor entre 
 * steep y m donde m es la diferencia entre maxsixe y usadas.size().
 * 
 * Notemos que los metodos getConnection y releaseConnection son sincronizados.
 * 
 * Por otro lado, el pool tiene que ser unico para toda la aplicacion (unico por
 * maquina virtual) por eso se implementa un singleton pattern para garantizar
 * que asi sea.
 * 
 * minsize - Al inicio el pool creara minsize conexiones con la base de datos.
 * 
 * maxsize - De ser necesario, durante la ejecucion del programa la cantidad 
 * anterior se podra incrementar hasta un maximo de maxsize conexiones.
 * 
 * steep - Cada vez que se agoten las conexiones disponibles, si la cantidad 
 * actual de conexiones no supera a maxsize entonces el pool creara steep nuevas
 * conexiones.
 * 
 * @author Francisco Castillo
 * @version Sabado 25 de marzo de 2017.
 */
public class ConnectionPool
{

    private ArrayList<Connection> libres;
    private ArrayList<Connection> usadas;

    private String url;
    private String driver;
    private String usr;
    private String pwd;

    private int minsize;
    private int maxsize;
    private int steep;

    private static ConnectionPool pool = null;

    private ConnectionPool()
    {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("connectionpool");

            //Obtengo los parametros de la conexion
            url = rb.getString("url");
            driver = rb.getString("driver");
            usr = rb.getString("usr");
            pwd = rb.getString("pwd");

            // levanto el driver
            Class.forName(driver);

            // Obtengo los parametros del pool
            minsize = Integer.parseInt(rb.getString("minsize"));
            maxsize = Integer.parseInt(rb.getString("maxsize"));
            steep = Integer.parseInt(rb.getString("steep"));

            libres = new ArrayList<>();
            usadas = new ArrayList<>();

            // instancio las n primeras conexiones
            _instanciar(minsize);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que retorna un String informando sobre el estado actual de las
     * conexiones.
     * @return 
     */
    public String toString()
    {
        return "libres: " + libres.size()
                + ", usadas: " + usadas.size();
    }

    /**
     * Como el constructor es privado la unica manera de acceder a una instancia
     * de ConnectionPool es a través del método estático getPool. En este metodo
     * instanciamos al pool invocando al constructor dentro del cual
     * establecimos la cantidad inicial de conexiones. Dado que pueden haber
     * varios hilos compitiendo por obtener el pool(es decir, invocando a este
     * metodo ) resulta que el metodo getPool tambien tiene que ser sincro
     * nizado
     *
     * @return
     */
    public synchronized static ConnectionPool getPool()
    {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * En este metodo lo primero es saber si se acabaron todas las conexiones 
     * libres y no podemos crear nuevas conexiones, entonces se arroja una
     * excepcion indicando al cliente que no podra obtener lo que busca.
     * Si este no es el caso entonces tomamos la primera conexion del ArrayList
     * libres, la pasamos al ArrayList usadas y retornamos la referencia al
     * llamador.
     * @return 
     */
    public synchronized Connection getConnection()
    {

        if (libres.size() == 0) {
            if (!_crearMasConexiones()) {
                throw new RuntimeException("No hay conexiones disponibles");
            }
        }
        Connection con = libres.remove(0);
        usadas.add(con);
        return con;
    }

    private boolean _crearMasConexiones()
    {
        int actuales = libres.size() + usadas.size();
        int n = Math.min(maxsize - actuales, steep);

        if (n > 0) {
            System.out.println("Creando " + n + " conexiones nuevas...");
            _instanciar(n);
        }
        return n > 0;

    }

    /**
     * Metodo privado que recibe el parametro n, instancia n conexiones y las
     * agrega al ArrayList de conexiones libres.
     * @param n 
     */
    private void _instanciar(int n)
    {
        try {
            Connection con;
            for (int i = 0; i < n; i++) {
                con = DriverManager.getConnection(url, usr, pwd);
                con.setAutoCommit(false);
                libres.add(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /**
     * En este metodo recibimos la conexion que el cliente (el hilo) quiere
     * devolver. La removemos del ArrayList de conexiones usadas y la agregamos
     * al ArrayList de conexiones libres. Si la conexion no existe en el array
     * de conexiones usadas, sera por que se trata de un error.
     * @param con 
     */
    public synchronized void releaseConnection(Connection con)
    {
        boolean ok = usadas.remove(con);
        if (ok) {
            libres.add(con);
            
        }else{
            throw new RuntimeException("Me devuelve una conexion que no es mia..");
        }
    }
    /**
     * Este metodo cierra todas las conexiones abiertas.
     */
    public synchronized void close()
    {
        try {
            //Cierro las conexiones libres
            for(Connection con: libres)
            {
             con.close();
            }
            //Cierro las conexiones usadas
            for(Connection con: usadas)
            {
             con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
