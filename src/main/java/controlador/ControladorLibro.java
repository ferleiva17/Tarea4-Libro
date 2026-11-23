/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Libro;
import vista.GUI_VistaPrincipal;

/**
 *
 * @author Fernis
 */
public class ControladorLibro implements ActionListener {
    private Libro modelo;
    private GUI_VistaPrincipal vista;

    private int paginaActual = 0;
    private int intentos = 0;
    private int[] respuestas = new int[3];

    private static final int MAX_INTENTOS = 3;

    // Constructor con parámetros
    public ControladorLibro(Libro modelo, GUI_VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando) {
            case "Iniciar":
                iniciarLibro();
                break;

            case "Anterior":
                paginaAnterior();
                break;

            case "Siguiente":
                paginaSiguiente();
                break;

            case "Actividad":
                mostrarActividad();
                break;

            case "Responder":
                evaluarRespuestas();
                break;

            case "Resultados":
                mostrarResultados();
                break;

            case "Menu":
                volverMenu();
                break;

            case "Salir":
                salir();
                break;

            default:
                System.out.println("Acción no reconocida: " + comando);
        }
    }

    // Iniciar lectura
    private void iniciarLibro() {
        paginaActual = 0;
    }

    // Navegar a la página anterior
    private void paginaAnterior() {
        if (paginaActual > 0) {
            paginaActual--;
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Ya estás en la primera página.");
        }
    }

    // Navegar a la siguiente página
    private void paginaSiguiente() {
        paginaActual++;
    }

    // Mostrar actividad
    private void mostrarActividad() {
        JOptionPane.showMessageDialog(vista,
                "Iniciando la actividad.");
    }

    // Evaluar respuestas
    private void evaluarRespuestas() {

        if (intentos >= MAX_INTENTOS) {
            JOptionPane.showMessageDialog(vista,
                    "Has alcanzado el máximo de intentos.");
            return;
        }

        intentos++;

        JOptionPane.showMessageDialog(vista,
                "Intento " + intentos + " de " + MAX_INTENTOS);
    }

    // Mostrar resultados
    private void mostrarResultados() {
        JOptionPane.showMessageDialog(vista,
                "Los resultados se conectarán con el modelo.");
    }

    // Regresar al menú
    private void volverMenu() {
        paginaActual = 0;
        intentos = 0;
    }

    // Salir del programa
    private void salir() {

        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Deseas salir del libro?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Obtener página actual
    public int getPaginaActual() {
        return paginaActual;
    }

    // Obtener intentos
    public int getIntentos() {
        return intentos;
    }

    // Guardar respuestas en arreglo unidimensional
    public void guardarRespuestas(int[] respuestasUsuario) {

        if (respuestasUsuario == null
                || respuestasUsuario.length != 3) {
            throw new IllegalArgumentException(
                    "Debe ingresar tres respuestas.");
        }

        respuestas = respuestasUsuario.clone();
    }

    // Obtener copia de las respuestas
    public int[] getRespuestas() {
        return respuestas.clone();
    }

    // Reiniciar intentos
    public void reiniciarIntentos() {
        intentos = 0;
    }
} //Fin clase ControladorLibro