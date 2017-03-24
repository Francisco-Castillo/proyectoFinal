
package app.cliente;

import app.dto.DeptDTO;
import app.dto.EmpDTO;
import app.factory.UFactory;
import java.util.Collection;
import java.util.Scanner;
import app.facade.Facade;

/**
 *
 * @author fcastillo
 */
public class Cliente
{
    // 9240714

    //Cuando ejecuto este metodo en la consola
    //me tira un warning de Ssl
    //Solucion : poner al final de la cadena de conexion &useSSL =false
    public static void main(String[] args)
    {
        // instancio el facade a traves del factory method
        Facade facade = (Facade) UFactory.getInstancia("FACADE");
        Collection<DeptDTO> collDepts = facade.obtenerDepartamentos();
        _mostrarDepartamentos(collDepts);
        
        Scanner reader = new Scanner(System.in);
        int deptno = reader.nextInt();
        
        Collection<EmpDTO> collEmps = facade.obtenerEmpleados(deptno);
        _mostrarEmpleados(collEmps, deptno);

    }

    private static void
            _mostrarDepartamentos(Collection<DeptDTO> collDepts)
    {
        System.out.println("Departamentos");
        System.out.println("------------------------------->");
        for (DeptDTO dto : collDepts) {
            System.out.println(dto.getDeptno() + "\t");
            System.out.println(dto.getDname() + "\t");
            System.out.println(dto.getLoc() + "\t");
        }
        System.out.println("<-----------------------------------------");
        System.out.print("Ingrese departamento : ");
    }

    private static void
            _mostrarEmpleados(Collection<EmpDTO> collEmps, int deptno)
    {
        System.out.println("Empleados del departamento: " + deptno);
        System.out.println("------------------------------->");
        for (EmpDTO dto : collEmps) {
            System.out.println(dto.getEmpno() + "\t");
            System.out.println(dto.getEname() + "\t");
            System.out.println(dto.getHiredate() + "\t");
        }
        System.out.println("<-----------------------------------------");

    }

    private static void _mostrarEmpleados(Collection<EmpDTO> collEmps)
    {
        System.out.println("------------------------------->");
        for (EmpDTO dto : collEmps) {
            System.out.println(dto.getEmpno() + "\t");
            System.out.println(dto.getEname() + "\t");
            System.out.println(dto.getHiredate() + "\t");
        }
        System.out.println("<-----------------------------------------");

    }
}
