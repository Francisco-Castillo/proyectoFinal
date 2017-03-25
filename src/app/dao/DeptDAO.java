
package app.dao;

import app.conexion.UConnection;
import app.dto.DeptDTO;
import app.sql.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author fcastillo
 */
public class DeptDAO
{

    public Collection<DeptDTO> buscarTodos()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            // Tomo una conexion del pool
            con = ConnectionPool.getPool().getConnection();
            //con = UConnection.getConnection();
            String sql = "SELECT deptno, dname, loc FROM dept ";
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            Vector<DeptDTO> ret = new Vector<DeptDTO>();
            DeptDTO dto = null;

            while (rs.next()) {
                dto = new DeptDTO();
                dto.setDeptno(rs.getInt("deptno"));
                dto.setDname(rs.getString("dname"));
                dto.setLoc(rs.getString("loc"));

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
                //Devuelvo la conexion al pool
                if (con!=null) {
                    ConnectionPool.getPool().releaseConnection(con);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}
