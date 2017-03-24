
package capaFisica;

import app.dto.DeptDTO;
import java.util.Collection;

/**
 *
 * @author fcastillo
 */
public class TestSrv1
{
    public static void main(String[] args) throws Exception
    {
        Collection<DeptDTO> coll;
        coll = ServiceLocatorTCP.obtenerDepartamentos();
        for (DeptDTO dto:coll) {
            System.out.println(dto);
            
        }
    }
}
