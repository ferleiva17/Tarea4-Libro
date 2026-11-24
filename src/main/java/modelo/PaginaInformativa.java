/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Fernis
 */
// Clase hija que hereda de Pagina
public class PaginaInformativa extends Pagina
{
    // Constructor
    public PaginaInformativa(String titulo, int numeroPagina, String contenido)
    {
        super(titulo, numeroPagina, contenido);
    }

    // Implementacion del metodo abstracto de la clase madre
    @Override
    public String mostrarContenido()
    {
        return "Pagina " + getNumeroPagina() + ": " + getTitulo() + "\n\n" + getContenido();
    }
}