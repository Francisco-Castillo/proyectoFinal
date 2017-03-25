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
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fcastillo
 */
public class EmpDAOMySqlDBImple extends EmpDAO
{

    
    @Override
    public Collection<EmpDTO> buscarUltimosEmpleados(int n)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = UConnection.getConnection();
            String sql = "SELECT * FROM emp ORDER BY hiredate DESC LIMIT  ?";
            pstm = con.prepareStatement(sql);

            //seteo valores
            pstm.setInt(1, n);

            rs = pstm.executeQuery();

            ArrayList<EmpDTO> ret = new ArrayList<>();
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

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
