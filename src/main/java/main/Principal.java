/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controlador.ControladorLibro;
import modelo.Libro;
import vista.GUI_VistaPrincipal;

/**
 *
 * @author Fernis
 */
public class Principal {

    public static void main(String[] args) {

        Libro modelo = new Libro();
        GUI_VistaPrincipal vista = new GUI_VistaPrincipal();
        ControladorLibro controlador = new ControladorLibro(modelo, vista);

        vista.setVisible(true);
    }
} //Fin clase Principal
