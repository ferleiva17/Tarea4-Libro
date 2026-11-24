/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author Fernis
 */

public class Libro
{
    // Coleccion de paginas del minilibro
    private ArrayList<Pagina> paginas = new ArrayList<>();

    // Arreglo unidimensional con las respuestas correctas
    private int[] respuestasCorrectas = {1, 0, 2};

    // Matriz bidimensional para almacenar los intentos
    private int[][] historial = new int[3][3];

    // Matriz de objetos para almacenar las preguntas
    private Pregunta[][] preguntas = new Pregunta[1][3];

    // Contador de intentos realizados
    private int intentos = 0;

    // Constructor
    public Libro()
    {
        // Agregar las paginas del minilibro
        paginas.add(new PaginaInformativa("Importancia del agua", 1, "El agua es un recurso indispensable para la vida. "
                + "Todos los seres vivos necesitan agua para sobrevivir. "
                + "Tambien es fundamental para la agricultura, "
                + "la higiene y el desarrollo de nuestras comunidades."));

        paginas.add(new PaginaInformativa("Contaminacion del agua", 2, "La contaminacion del agua ocurre cuando sustancias "
                + "daninas llegan a rios, lagos y oceanos. "
                + "La basura, los aceites y los productos quimicos "
                + "afectan a los ecosistemas y a los seres vivos."));

        paginas.add(new PaginaInformativa("Como ahorrar agua", 3, "Podemos ahorrar agua cerrando la llave mientras "
                + "nos cepillamos los dientes, reparando las fugas "
                + "y reutilizando el agua cuando sea posible. "
                + "Pequenas acciones ayudan a proteger este recurso."));

        paginas.add(new PaginaActividad("Actividad final", 4, "Comprueba lo aprendido sobre el cuidado del agua."));

        // Crear las tres preguntas de la actividad
        preguntas[0][0] = new Pregunta("Cual es una forma correcta de ahorrar agua?",
                new String[]
                {
                    "Dejar la llave abierta", "Cerrar la llave al cepillarse", "Lavar las aceras con manguera"
                });

        preguntas[0][1] = new Pregunta("Que accion puede contaminar el agua?",
                new String[]
                {
                    "Verter aceite por el fregadero", "Recoger basura de los rios", "Cerrar correctamente las llaves"
                });

        preguntas[0][2] = new Pregunta("Que debemos hacer cuando encontramos una fuga de agua?",
                new String[]
                {
                    "Ignorarla", "Aumentar la presion del agua", "Repararla"
                });
    }//Fin del método Libro

    // Obtener la cantidad de paginas
    public int cantidadPaginas()
    {
        return paginas.size();
    }

    // Obtener una pagina segun su posicion
    public Pagina obtenerPagina(int numero)
    {
        if (numero < 0 || numero >= paginas.size())
        {
            throw new IndexOutOfBoundsException("La pagina solicitada no existe.");
        }
        return paginas.get(numero);
    }

    // Recorrer todas las paginas del libro
    public String mostrarTodasLasPaginas()
    {
        String texto = "";
        for (Pagina pagina : paginas)
        {
            texto += pagina.mostrarContenido() + "\n\n";
        }
        return texto;
    }

    // Obtener una pregunta segun su posicion
    public Pregunta obtenerPregunta(int numero)
    {
        if (numero < 0 || numero >= preguntas[0].length)
        {
            throw new IndexOutOfBoundsException("La pregunta solicitada no existe.");
        }

        return preguntas[0][numero];
    }

    // Obtener la cantidad de intentos realizados
    public int getIntentos()
    {
        return intentos;
    }

    // Obtener la cantidad de intentos restantes
    public int getIntentosRestantes()
    {
        return historial.length - intentos;
    }

    // Registrar las respuestas y calcular los puntos
    public int registrarRespuestas(int[] respuestas)
    {
        if (intentos >= 3)
        {
            throw new IllegalStateException("Ya se realizaron los tres intentos.");
        }

        if (respuestas == null || respuestas.length != 3)
        {
            throw new IllegalArgumentException("Debe responder las tres preguntas.");
        }

        // Validar que las respuestas sean correctas en cuanto a formato
        for (int i = 0; i < respuestas.length; i++)
        {
            if (respuestas[i] < 0 || respuestas[i] >= preguntas[0][i].getOpciones().length)
            {
                throw new IllegalArgumentException("La respuesta seleccionada no es valida.");
            }
        }
        int puntos = 0;

        // Comparar respuestas y guardar el intento en la matriz
        for (int i = 0; i < respuestas.length; i++)
        {
            historial[intentos][i] = respuestas[i];
            if (respuestas[i] == respuestasCorrectas[i])
            {
                puntos++;
            }
        }
        intentos++;
        return puntos;
    }

    // Consultar los puntos obtenidos en un intento
    public int obtenerPuntosIntento(int numeroIntento)
    {
        if (numeroIntento < 1 || numeroIntento > intentos)
        {
            throw new IndexOutOfBoundsException("El intento solicitado no existe.");
        }

        int puntos = 0;
        int posicion = numeroIntento - 1;

        for (int i = 0; i < respuestasCorrectas.length; i++)
        {
            if (historial[posicion][i] == respuestasCorrectas[i])
            {
                puntos++;
            }
        }
        return puntos;
    }

    // Mostrar el historial de los intentos
    public String mostrarHistorial()
    {
        String texto = "Historial de intentos:\n";
        if (intentos == 0)
        {
            return "Todavia no se han realizado intentos.";
        }

        for (int i = 0; i < intentos; i++)
        {
            texto += "\nIntento " + (i + 1) + ": ";

            for (int j = 0; j < 3; j++)
            {
                texto += (historial[i][j] + 1) + " ";
            }

            texto += "\nPuntos obtenidos: " + obtenerPuntosIntento(i + 1) + " de 3 \n";
        }
        return texto;
    }
}//Fin de la clase Libro
