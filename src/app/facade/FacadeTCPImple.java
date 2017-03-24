
package app.facade;

import app.dto.DeptDTO;
import app.dto.EmpDTO;
import java.util.Collection;
import capaFisica.ServiceLocatorTCP;

/**
 *
 * @author fcastillo
 */
public class FacadeTCPImple implements Facade
{

    @Override
    public Collection<DeptDTO> obtenerDepartamentos()
    {
        return ServiceLocatorTCP.obtenerDepartamentos();
    }

    @Override
    public Collection<EmpDTO> obtenerEmpleados(int deptno)
    {
        return ServiceLocatorTCP.obtenerEmpleados(deptno);
    }
    
}