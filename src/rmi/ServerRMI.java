/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author fcastillo
 */
public class ServerRMI
{
    public static void main(String[] args) throws Exception
    {
        FacadeRemotoImple f = new FacadeRemotoImple();
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.rebind("FacadeRemoto", f);
    }
}
