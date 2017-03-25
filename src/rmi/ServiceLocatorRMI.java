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
public class ServiceLocatorRMI
{

    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 1099;

    public static Object lookup(String objRemotoName)
    {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_IP, SERVER_PORT);
            return registry.lookup(objRemotoName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
