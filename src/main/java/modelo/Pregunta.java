/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Fernis
 */

public class Pregunta
{
    // Atributos de la pregunta
    private String texto;
    private String[] opciones;

    // Constructor
    public Pregunta(String texto, String[] opciones)
    {
        this.texto = texto;
        this.opciones = opciones;
    }

    // Obtener el texto de la pregunta
    public String getTexto()
    {
        return texto;
    }

    // Obtener las opciones de respuesta
    public String[] getOpciones()
    {
        return opciones;
    }
}