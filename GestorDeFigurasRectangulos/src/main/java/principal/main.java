/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import vista.VistaRectangulo;


/**
 *
 * @author Daniel
 */
public class main {
  
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            VistaRectangulo vista = new VistaRectangulo();
            vista.setVisible(true);
        });
    }
}

