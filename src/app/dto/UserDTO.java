/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dto;

/**
 *
 * @author fcastillo
 */
public class UserDTO
{

    private int id;
    private String user;
    private String password;
    private String rol;

    public UserDTO()
    {
    }

    public UserDTO(int id, String user, String password, String rol)
    {
        this.id = id;
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRol()
    {
        return rol;
    }

    public void setRol(String rol)
    {
        this.rol = rol;
    }

    @Override
    public String toString()
    {
        return id + ", " + user + ", " + password + ", " + rol;
    }

}
