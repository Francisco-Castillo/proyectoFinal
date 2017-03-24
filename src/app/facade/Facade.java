/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.facade;

import app.dto.DeptDTO;
import app.dto.EmpDTO;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public interface Facade
{
    public Collection<DeptDTO> obtenerDepartamentos();
    public Collection<EmpDTO> obtenerEmpleados(int deptno);
    
}
