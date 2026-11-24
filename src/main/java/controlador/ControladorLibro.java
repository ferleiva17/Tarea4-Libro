/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Libro;
import modelo.Pregunta;
import modelo.Pagina;
import vista.GUI_VistaPrincipal;
import vista.GUI_VistaContenido1;
import vista.GUI_VistaContenido2;
import vista.GUI_VistaContenido3;
import vista.GUI_ActividadInteractiva;
import vista.GUI_Resultados;

/**
 *
 * @author Fernis
 */

// Controlador encargado de coordinar el modelo y las vistas del libro educativo.
public class ControladorLibro implements ActionListener {

    private Libro modelo;
    private GUI_VistaPrincipal vista;

    private GUI_VistaContenido1 vistaContenido1;
    private GUI_VistaContenido2 vistaContenido2;
    private GUI_VistaContenido3 vistaContenido3;
    private GUI_ActividadInteractiva vistaActividad;
    private GUI_Resultados vistaResultados;

    private int paginaActual = 0;
    private int intentos = 0;
    private int[] respuestas = new int[3];

    private static final int MAX_INTENTOS = 3;

    // Constructor que recibe el modelo y la vista principal e inicializa las demás vistas.
    public ControladorLibro(Libro modelo, GUI_VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        vistaContenido1 = new GUI_VistaContenido1();
        vistaContenido2 = new GUI_VistaContenido2();
        vistaContenido3 = new GUI_VistaContenido3();
        vistaActividad = new GUI_ActividadInteractiva();
        vistaResultados = new GUI_Resultados();

        this.vista.agregarListenerIniciar(this);
        this.vista.agregarListenerSalir(this);

        vistaContenido1.agregarListenerAnterior(this);
        vistaContenido1.agregarListenerSiguiente(this);

        vistaContenido2.agregarListenerAnterior(this);
        vistaContenido2.agregarListenerSiguiente(this);

        vistaContenido3.agregarListenerAnterior(this);
        vistaContenido3.agregarListenerActividad(this);

        vistaActividad.agregarListenerPreguntas(this);
        vistaActividad.agregarListenerVolver(this);

        vistaResultados.agregarListenerAnterior(this);
        vistaResultados.agregarListenerVolverMenu(this);
    }

    // Atiende los eventos generados por los botones de las vistas.
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

            case "Ir a la actividad":
                mostrarActividad();
                break;

            case "Mostrar Preguntas":
                evaluarRespuestas();
                break;

            case "Actividad":
                volverActividad();
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

    // Inicia el libro mostrando la primera página informativa.
    private void iniciarLibro() {
        paginaActual = 0;
        vista.setVisible(false);
        vistaContenido1.setVisible(true);
        obtenerPaginaActual();
    }

    // Navega hacia la página anterior según la página actual.
    private void paginaAnterior() {
        if (paginaActual == 1) {
            paginaActual = 0;
            vistaContenido1.setVisible(true);
            vistaContenido2.setVisible(false);
        } else if (paginaActual == 2) {
            paginaActual = 1;
            vistaContenido2.setVisible(true);
            vistaContenido3.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Ya estás en la primera página.");
        }
    }

    // Navega hacia la página siguiente del libro.
    private void paginaSiguiente() {
        if (paginaActual == 0) {
            paginaActual = 1;
            vistaContenido1.setVisible(false);
            vistaContenido2.setVisible(true);
        } else if (paginaActual == 1) {
            paginaActual = 2;
            vistaContenido2.setVisible(false);
            vistaContenido3.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Ya estás en la última página informativa.");
        }
    }

    // Consulta la página actual al modelo para validar su existencia.
    private Pagina obtenerPaginaActual() {
        try {
            return modelo.obtenerPagina(paginaActual);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo encontrar la página.");
            return null;
        }
    }

    // Muestra la ventana de la actividad interactiva.
    private void mostrarActividad() {
        vistaContenido3.setVisible(false);
        vistaActividad.setVisible(true);
    }

    // Solicita las respuestas al usuario, las registra en el modelo y muestra los resultados.
    private void evaluarRespuestas() {
        if (modelo.getIntentosRestantes() <= 0) {
            JOptionPane.showMessageDialog(vistaActividad,
                    "Has alcanzado el máximo de intentos.");
            return;
        }

        Pregunta[] preguntas = new Pregunta[3];

        for (int i = 0; i < preguntas.length; i++) {
            preguntas[i] = modelo.obtenerPregunta(i);
        }

        int[] respuestasUsuario =
                vistaActividad.solicitarRespuestas(preguntas);

        // Si el usuario cancela, no se registra el intento.
        if (respuestasUsuario == null) {
            return;
        }

        try {
            guardarRespuestas(respuestasUsuario);

            int puntos = modelo.registrarRespuestas(respuestas);

            intentos = modelo.getIntentos();

            String textoResultados =
                    "Resultado del intento: " + puntos + " de 3 puntos.\n"
                    + "Intento: " + intentos + " de " + MAX_INTENTOS
                    + "\nIntentos restantes: "
                    + modelo.getIntentosRestantes();

            vistaResultados.mostrarResultados(textoResultados);

            vistaActividad.setVisible(false);
            vistaResultados.setVisible(true);

        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(vistaActividad,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Permite regresar a la actividad desde la ventana de resultados.
    private void volverActividad() {
        vistaResultados.setVisible(false);
        vistaActividad.setVisible(true);
    }

    // Regresa al menú principal y reinicia la navegación.
    private void volverMenu() {
        vistaResultados.setVisible(false);
        vistaActividad.setVisible(false);
        vistaContenido1.setVisible(false);
        vistaContenido2.setVisible(false);
        vistaContenido3.setVisible(false);

        paginaActual = 0;
        vista.setVisible(true);
    }

    // Confirma si el usuario desea salir del programa.
    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Deseas salir del libro?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Obtiene el número de página actual.
    public int getPaginaActual() {
        return paginaActual;
    }

    // Obtiene el número de intentos realizados.
    public int getIntentos() {
        return intentos;
    }

    // Guarda una copia de las respuestas del usuario y valida que sean tres.
    public void guardarRespuestas(int[] respuestasUsuario) {
        if (respuestasUsuario == null
                || respuestasUsuario.length != 3) {
            throw new IllegalArgumentException(
                    "Debe ingresar tres respuestas.");
        }

        respuestas = respuestasUsuario.clone();
    }

    // Devuelve una copia del arreglo de respuestas.
    public int[] getRespuestas() {
        return respuestas.clone();
    }

    // Reinicia el contador local de intentos.
    public void reiniciarIntentos() {
        intentos = 0;
    }

}