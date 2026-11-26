/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Fernis
 */
//clase madre Página
public abstract class Pagina
{
    //Estos son los atributos de la clase madre que
    //heredarán las clases hijas
    private String titulo;
    private int numeroPagina;
    private String contenido;

    public Pagina(String titulo, int numeroPagina, String contenido)
    {
        //El constructor permite inicializar una página cuando creamos un
        //objeto de alguna de sus clases hijas.
        this.titulo = titulo;
        this.numeroPagina = numeroPagina;
        this.contenido = contenido;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public int getNumeroPagina()
    {
        return numeroPagina;
    }

    public String getContenido()
    {
        return contenido;
    }

    //El método abstracto permite mostrar polimorfismo
    public abstract String mostrarContenido();

}//Fin de la clase Pagina
