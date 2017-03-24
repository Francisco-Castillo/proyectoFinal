/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.conexion.UConnection;
import app.dto.EmpDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author fcastillo
 */
public abstract class EmpDAO
{

    //metodo absracto, que debe ser resuelto en las subclases
    public abstract Collection<EmpDTO> buscarUltimosEmpleados(int n);

    public Collection<EmpDTO> buscarXDepto(int deptno)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = UConnection.getConnection();
            String sql = "SELECT empno,ename,hiredate,deptno FROM emp WHERE deptno = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, deptno);
            rs = pstm.executeQuery();

            Vector<EmpDTO> ret = new Vector<EmpDTO>();
            EmpDTO dto = null;
            while (rs.next()) {
                dto = new EmpDTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setHiredate(rs.getDate("hiredate"));
                dto.setDeptno(rs.getInt("deptno"));

                ret.add(dto);

            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

}
