
package app.facade;

import app.dao.DeptDAO;
import app.dao.EmpDAO;
import app.dto.DeptDTO;
import app.dto.EmpDTO;
import app.factory.UFactory;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class FacadeLocalImple implements Facade
{

    public Collection<EmpDTO> obtenerUltimosEmpleados(int n)
    {
        EmpDAO empDao = (EmpDAO) UFactory.getInstancia("emp");
        return empDao.buscarUltimosEmpleados(n);
    }
    
    @Override
    public Collection<DeptDTO> obtenerDepartamentos()
    {
        DeptDAO deptDao = new DeptDAO();
        return deptDao.buscarTodos();
    }
    
    @Override
    public Collection<EmpDTO> obtenerEmpleados(int deptno)
    {
        //la clase EmpDAO es abstracta , no se pueden obtener instancias de
        //ella

        /*EmpDAO empDao = new EmpDAO();
        return empDao.buscarXDepto(deptno);*/
        EmpDAO empDao = (EmpDAO) UFactory.getInstancia("emp");
        return empDao.buscarXDepto(deptno);
    }
}
