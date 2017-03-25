/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import app.dao.DeptDAO;
import app.dao.EmpDAO;
import app.dto.DeptDTO;
import app.dto.EmpDTO;
import app.factory.UFactory;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class FacadeRemotoImple extends UnicastRemoteObject implements FacadeRemoto
{

    public FacadeRemotoImple() throws RemoteException
    {
        super();
    }

    @Override
    public Collection<String> obtenerDepartamentos() throws RemoteException
    {
        DeptDAO dao = (DeptDAO) UFactory.getInstancia("dept");
        Collection<DeptDTO> coll = dao.buscarTodos();

        ArrayList<String> ret = new ArrayList<>();
        for (DeptDTO dto : coll) {
            ret.add(dto.toString());
        }
        return ret;

    }

    @Override
    public Collection<String> obtenerEmpleados(int deptno) throws RemoteException
    {
        EmpDAO dao = (EmpDAO) UFactory.getInstancia("emp");
        Collection<EmpDTO> coll = dao.buscarXDepto(deptno);

        ArrayList<String> ret = new ArrayList<>();
        for (EmpDTO dto : coll) {
            ret.add(dto.toString());
        }
        return ret;

    }

}
