/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.DataBase.Core;

import java.util.ArrayList;

/**
 * @since 1.4.8
 * @version 1.4.8
 * @author Nekszer
 */
public interface ITableClicked {
    
    /**
     * Este método devuelve todos los datos de una tupla, y el numero de la tupla a la cual se le ha dado click
     * @param tupla int
     * @param rowPos ArrayList
     */
    public void data(ArrayList tupla, int rowPos);
    
}
