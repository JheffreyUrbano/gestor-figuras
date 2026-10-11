/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Rectangulo;
import modelo.Punto;


/**
 *
 * @author Daniel
 */
public class ControladorRectangulo {
    private Rectangulo rectangulo;

    
    public ControladorRectangulo(Rectangulo rectangulo) {
        this.rectangulo = rectangulo;
    }

  
    public double obtenerArea() {
        return rectangulo.calcularArea();
    }
    
    public void escalar(double factor) {
        Punto p1 = rectangulo.getP1();
        Punto p2 = rectangulo.getP2();
        p2.setX(p1.getX() + (p2.getX() - p1.getX()) * factor);
        p2.setY(p1.getY() + (p2.getY() - p1.getY()) * factor);
    }
}

