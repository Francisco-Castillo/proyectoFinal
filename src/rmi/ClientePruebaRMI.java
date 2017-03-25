/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class ClientePruebaRMI
{

    public static void main(String[] args) throws Exception
    {
        //OBTENGO UNA REFERENCIA AL OBJETO REMOTO
        FacadeRemoto f = (FacadeRemoto) ServiceLocatorRMI.lookup("FacadeRemoto");

        //invoco un metodo
        Collection<String> sDepts = f.obtenerDepartamentos();
        for (String s : sDepts) {
            System.out.println(s);
        }
    }

}
