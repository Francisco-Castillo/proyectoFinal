/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;


import app.dto.UserDTO;
import app.sql.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class UserDAO
{

    public Collection<UserDTO> autenticarUsuario(String user, String pass)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getPool().getConnection();
            String sql = "SELECT * FROM usuario WHERE user = ? AND password = ?";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, user);
            pstm.setString(2, pass);

            rs = pstm.executeQuery();

            ArrayList<UserDTO> ret = new ArrayList<>();
            UserDTO dto = null;
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getInt("id"));
                dto.setUser(rs.getString("user"));
                dto.setPassword(rs.getString("password"));
                dto.setRol(rs.getString("rol"));

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
                if (con != null) {
                    ConnectionPool.getPool().releaseConnection(con);

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public Collection<UserDTO> buscarTodos()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            // Tomo una conexion del pool
            con = ConnectionPool.getPool().getConnection();
            //con = UConnection.getConnection();
            String sql = "SELECT * FROM usuario ";
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            ArrayList<UserDTO> ret = new ArrayList<>();
            UserDTO dto = null;

            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getInt("id"));
                dto.setUser(rs.getString("user"));
                dto.setPassword(rs.getString("password"));
                dto.setRol(rs.getString("rol"));

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
                if (con != null) {
                    ConnectionPool.getPool().releaseConnection(con);

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}
