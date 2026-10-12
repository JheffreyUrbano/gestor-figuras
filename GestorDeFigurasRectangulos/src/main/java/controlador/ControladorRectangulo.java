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
    
    public boolean validarPuntoDentro(Punto p) {
        double minX = Math.min(rectangulo.getP1().getX(), rectangulo.getP2().getX());
        double maxX = Math.max(rectangulo.getP1().getX(), rectangulo.getP2().getX());
        double minY = Math.min(rectangulo.getP1().getY(), rectangulo.getP2().getY());
        double maxY = Math.max(rectangulo.getP1().getY(), rectangulo.getP2().getY());

        return (p.getX() >= minX && p.getX() <= maxX && p.getY() >= minY && p.getY() <= maxY);
    } 
}

