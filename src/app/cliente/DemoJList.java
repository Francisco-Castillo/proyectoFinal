/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.cliente;

import app.dto.DeptDTO;
import app.facade.Facade;
import app.factory.UFactory;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author fcastillo
 */
public class DemoJList extends JFrame
{

    public DemoJList()
    {
        super("Demo");
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        Facade facade = (Facade) UFactory.getInstancia("FACADE");
        Collection<DeptDTO> col = facade.obtenerDepartamentos();
        
        JList lista = new JList(col.toArray());
        
        JScrollPane scrollLista = new JScrollPane(lista);
        content.add(scrollLista,BorderLayout.CENTER);
        setSize(300,300);
        setVisible(true);
    }

    

    public static void main(String[] args)
    {
        new DemoJList();
    }
}
