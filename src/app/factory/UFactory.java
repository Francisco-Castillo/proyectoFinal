/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.factory;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 *
 * @author fcastillo
 */
public class UFactory
{
    //Lo primero será definir una variable contenedor para instanciar 
    //la clase Hashtable Java:

    //Hashtable<String,String> contenedor=new Hashtable<String,String>();
    //private static Hashtable<String, Object> instancias = new Hashtable<String, Object>();
    private static HashMap<String, Object> instancias = new HashMap<String, Object>();
   
    /**
     * 
     * @param objName
     * @return 
     */
    public static Object getInstancia(String objName)
    {
        try {
            //Verifico si existe un objeto relacionado a objName
            //en la hashtable
            Object obj = instancias.get(objName);

            //si no existe lo instancio y lo agrego
            if (obj == null) {
                ResourceBundle rb = ResourceBundle.getBundle("factory");
                String sClassName = rb.getString(objName);
                obj = Class.forName(sClassName).newInstance();
                //agregar el objeto a la hashtable
                instancias.put(objName, obj);

            }

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
