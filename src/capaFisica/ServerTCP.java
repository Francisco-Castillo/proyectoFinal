/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaFisica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import app.dao.DeptDAO;
import app.dao.EmpDAO;
import app.dao.EmpDAOMySqlDBImple;
import app.dto.DeptDTO;
import app.dto.EmpDTO;
import app.factory.UFactory;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class ServerTCP extends Thread
{

    public static final int OBTENER_DEPARTAMENTOS = 1;
    public static final int OBTENER_EMPLEADOS = 2;
    public static final int OBTENER_TODOS_LOS_EMPLEADOS = 3;

    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public ServerTCP(Socket s)
    {
        this.socket = s;
    }

    public static void main(String[] args) throws Exception
    {
        ServerSocket ss = new ServerSocket(5432);
        Socket s;
        while (true) {
            s = ss.accept();
            //informacion en consola
            System.out.println("Se conectaron desde la IP : "
                    + s.getInetAddress());
            new ServerTCP(s).start();

        }
    }

    @Override
    public void run()
    {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            //leo el codigo de servicio
            int codSvr = dis.readInt();
            switch (codSvr) {
                case OBTENER_DEPARTAMENTOS:
                    _obtenerDepartamentos(dis, dos);
                    break;
                case OBTENER_EMPLEADOS:
                    _obtenerEmpleados(dis, dos);
                    break;
                case OBTENER_TODOS_LOS_EMPLEADOS:
                    _obtenertodosLosEmpleados(dis, dos);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (dis != null) {
                    dis.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    private void _obtenerDepartamentos(DataInputStream dis, DataOutputStream dos)
    {
        try {
            DeptDAO dao = (DeptDAO) UFactory.getInstancia("dept");

            //Obtengo la coleccion de departamentos
            Collection<DeptDTO> coll = dao.buscarTodos();

            //envio el size al cliente
            int size = coll.size();
            dos.writeInt(size);

            //envio la coleccion de departamentos
            for (DeptDTO dto : coll) {
                //REDEFINIR EL METODO TO STRING
                dos.writeUTF(dto.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void _obtenerEmpleados(DataInputStream dis, DataOutputStream dos)
    {
        try {
            EmpDAO dao = (EmpDAO) UFactory.getInstancia("emp");

            //leo el depto
            int deptno = dis.readInt();

            //Obtengo la coleccion de empleados
            Collection<EmpDTO> coll = dao.buscarXDepto(deptno);

            //envio el size al cliente
            int size = coll.size();
            dos.writeInt(size);

            //envio la coleccion de departamentos
            for (EmpDTO dto : coll) {
                //REDEFINIR EL METODO TO STRING
                dos.writeUTF(dto.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
    private void _obtenertodosLosEmpleados(DataInputStream dis, DataOutputStream dos)
    {
        try {
            EmpDAO dao = (EmpDAO) UFactory.getInstancia("emp");

            //leo el depto
            int deptno = dis.readInt();

            //Obtengo la coleccion de empleados
            Collection<EmpDTO> coll = dao.buscarTodos();

            //envio el size al cliente
            int size = coll.size();
            dos.writeInt(size);

            //envio la coleccion de departamentos
            for (EmpDTO dto : coll) {
                //REDEFINIR EL METODO TO STRING
                dos.writeUTF(dto.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
