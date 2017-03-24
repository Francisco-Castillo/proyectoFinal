/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaFisica;

import app.dto.EmpDTO;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class TestSrv2
{
     public static void main(String[] args) throws Exception
    {
        Collection<EmpDTO> coll = ServiceLocatorTCP.obtenerEmpleados(7);
       
        for (EmpDTO dto : coll) {
            System.out.println(dto);
            
        }
    }
    
}
