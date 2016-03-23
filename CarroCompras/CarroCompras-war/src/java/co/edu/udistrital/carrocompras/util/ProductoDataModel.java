/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.util;

import co.edu.udistrital.carro.compras.entity.Producto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Leonardo
 */
public class ProductoDataModel extends ListDataModel<Producto> implements SelectableDataModel<Producto>{
    
    public ProductoDataModel(List<Producto> data) {  
        super(data);  
    } 

    @Override
    public Object getRowKey(Producto producto) {
        return producto.getProdId();
    }

    @Override
    public Producto getRowData(String rowKey) {
         List<Producto> productos = (List<Producto>) getWrappedData();  
          
        for(Producto producto : productos) {  
            if(producto.getProdId().toString().equals(rowKey))  
                return producto;  
        }  
        return null; 
    }
    
}
