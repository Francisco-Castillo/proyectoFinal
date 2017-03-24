package capaFisica;

import app.dto.DeptDTO;
import app.dto.EmpDTO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.ArrayList;

/**
 * La clase ServiceLocatorTCP , se encarga de encapsular todo el codigo que
 * implica conectarse con el servidor, enviar y recibir informacion y
 * desconectarse. Tendra tantos metodos estaticos como servicios ofrece el
 * servidor. Como en todos los casos, el server envia strings que son 
 * representaciones de DTOs, en los metodos de ServiceLocatorTCP convertiremos
 * cada string a su correspondiente DTO para abstraer tambien de este problema
 * al cliente. Para ello se utilizara la clase utilitaria UDto.
 *
 * @author Francisco Castillo
 * @version 23/03/2017
 */
public class ServiceLocatorTCP
{

    //  Definimos dos constantes para harcodear lo menos posible los parametros
    //  de conexion. Estos valores se pueden definir en archivo de propiedades
    //  o en archivos XML.
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 5432;

    /**
     * Metodo que retorna una coleccion de departamentos. Se establece la
     * conexion con el servidor enviando el codigo de servicio que corresponda
     * en este caso 1 y luego recibimos los datos que envia el servidor.
     * @return 
     */
    public static Collection<DeptDTO> obtenerDepartamentos()
    {
        Socket s = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            //  me conecto
            s = new Socket(SERVER_IP, SERVER_PORT);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());

            //  solicito servicio codigo 1 (obtenerDepartamentos)
            dos.writeInt(1);

            //  El server me indica cuantos departamentos va a enviar
            int n = dis.readInt();

            //Vector<DeptDTO> ret = new Vector<DeptDTO>();
            ArrayList<DeptDTO> ret = new ArrayList<>();

            String aux;

            for (int i = 0; i < n; i++) {
                //leo el i-esimo String
                aux = dis.readUTF();
                // Cada String que recibo lo convierto a DeptDTO
                //  y lo agrego a la coleccion de retorno
                ret.add(UDto.stringToDeptDTO(aux));

            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                // cerramos los recursos
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    
    public static Collection<EmpDTO> obtenerEmpleados(int deptno)
    {
        Socket s = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            //  me conecto
            s = new Socket(SERVER_IP, SERVER_PORT);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());

            //  solicito servicio codigo 2 (obtenerEmpleados)
            dos.writeInt(2);
            
            // envio el numero de departamento
            dos.writeInt(deptno);

            //  El server me indica cuantos departamentos va a enviar
            int n = dis.readInt();

            //Vector<DeptDTO> ret = new Vector<DeptDTO>();
            ArrayList<EmpDTO> ret = new ArrayList<>(n);

            String aux;

            for (int i = 0; i < n; i++) {
                //leo el i-esimo String
                aux = dis.readUTF();
                // Cada String que recibo lo convierto a DeptDTO
                //  y lo agrego a la coleccion de retorno
                ret.add(UDto.stringToEmpDTO(aux));

            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                // cerramos los recursos
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    
}
