package edu.umg.programacion2.examen01.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umg.programacion2.examen01.excepciones.LibroNoDisponibleException;
import edu.umg.programacion2.examen01.modelo.Libro;

/**
 * Igual que RepositorioUsuarios/CatalogoProductos de clases anteriores: el
 * mismo catálogo de libros guardado en tres colecciones distintas, cada una
 * optimizada para una operación distinta.
 * <p>
 * - ArrayList&lt;Libro&gt;: catálogo completo, en orden de llegada.
 * - HashMap&lt;String, Libro&gt;: encontrar un libro por ISBN al instante.
 * - HashSet&lt;String&gt;: saber qué categorías existen, sin repetidas.
 */
public class Biblioteca {

	private final List<Libro> libros = new ArrayList<>();
	private final Map<String, Libro> librosPorIsbn = new HashMap<>();
	private final Set<String> categorias = new HashSet<>();

	public void agregar(Libro libro) {
		libros.add(libro);
		librosPorIsbn.put(libro.getIsbn(), libro);
		categorias.add(libro.getCategoria());
	}

	public void cargarTodos(List<Libro> librosACargar) {
		for (Libro libro : librosACargar) {
			agregar(libro);
		}
	}

	public List<Libro> listarTodos() {
		return libros;
	}

	public Libro buscarPorIsbn(String isbn) {
		return librosPorIsbn.get(isbn);
	}

	public Set<String> listarCategorias() {
		return categorias;
	}

	public int total() {
		return libros.size();
	}

	/**
	 * Ya resuelto: sirve de referencia para las 3 preguntas prácticas de
	 * abajo. Presta un libro por ISBN. Si no existe o ya está prestado,
	 * propaga la excepción para que quien llame decida qué mostrar.
	 */
	public void prestarPorIsbn(String isbn) throws LibroNoDisponibleException {
		Libro libro = buscarPorIsbn(isbn);
		if (libro == null) {
			throw new LibroNoDisponibleException("No existe ningún libro con ISBN " + isbn);
		}
		libro.prestar();
	}

	/**
	 * PREGUNTA PRÁCTICA 1 (20 pts): completar contarLibrosPorCategoria().
	 * <p>
	 * Enunciado: recorrer el catálogo y devolver un Map donde la clave es la
	 * categoría y el valor es cuántos libros hay de esa categoría.
	 * <p>
	 * Entrada de ejemplo: [Cien años...-Novela, El Principito-Infantil,
	 * Rayuela-Novela].
	 * Salida esperada: {Novela=2, Infantil=1} (el orden puede variar, HashMap
	 * no garantiza orden).
	 * <p>
	 * Pista: recorre libros con un for-each. Para cada libro, usa
	 * conteo.getOrDefault(categoria, 0) para saber el valor actual (0 si es
	 * la primera vez que aparece esa categoría) y guarda ese valor + 1 con
	 * put().
	 * <p>
	 * Criterios de evaluación:
	 * - No usa streams ni lambdas, solo un for-each y el Map.
	 * - Usa Map.getOrDefault(), no un HashSet auxiliar ni containsKey().
	 * - Si el catálogo está vacío, retorna un Map vacío (no null).
	 */
	public Map<String, Integer> contarLibrosPorCategoria(List<Libro> libros) {
		
		Map<String, Integer> conteo = new HashMap<>();
		
		for (Libro libro : libros) {
			String categori = libro.getCategoria();
			int cantidadactal = conteo.getOrDefault(categori, 0);
			conteo.put(categori, cantidadactal + 1);
			
			
		}
		
		
		
		return conteo;
		
		
	}

	/**
	 * PREGUNTA PRÁCTICA 2 (20 pts): completar buscarPorTituloParcial().
	 * <p>
	 * Enunciado: recorrer el catálogo y devolver solo los libros cuyo título
	 * contenga el texto buscado, sin importar mayúsculas o minúsculas.
	 * <p>
	 * Entrada de ejemplo: "Cien años de soledad", "El Principito", "Rayuela";
	 * buscarPorTituloParcial("el").
	 * Salida esperada: una lista con "El Principito" (contiene "el" al
	 * inicio, ignorando mayúsculas/minúsculas).
	 * <p>
	 * Pista: usa libro.getTitulo().toLowerCase().contains(texto.toLowerCase())
	 * dentro de un for-each, y agrega las coincidencias a una lista nueva.
	 * <p>
	 * Criterios de evaluación:
	 * - No modifica la lista original (libros).
	 * - La búsqueda ignora mayúsculas/minúsculas.
	 * - Si no hay coincidencias, retorna una lista vacía (no null).
	 */
	public List<Libro> buscarPorTituloParcial(String texto) {
		// TODO: reemplazar esta línea por la lógica descrita arriba.
		throw new UnsupportedOperationException("TODO: completar buscarPorTituloParcial() en Biblioteca");
	}

	/**
	 * PREGUNTA PRÁCTICA 3 (20 pts): completar libroMasAntiguoDeCategoria().
	 * <p>
	 * Enunciado: dado el nombre de una categoría, encontrar el libro con
	 * MENOR año de publicación (el más antiguo) dentro de esa categoría. Si
	 * la categoría no tiene ningún libro, retornar null.
	 * <p>
	 * Entrada de ejemplo: libroMasAntiguoDeCategoria("Novela") con el
	 * catálogo de DatosIniciales.
	 * Salida esperada: el libro de categoría Novela con menor año.
	 * <p>
	 * Pista: primero filtra los libros de esa categoría con un for-each
	 * (misma idea que buscarPorTituloParcial, pero comparando categoría con
	 * equals() en vez de usar contains()). Con esa lista más corta, recorre
	 * guardando en una variable el libro "más antiguo visto hasta ahora" y
	 * compara año contra año.
	 * <p>
	 * Criterios de evaluación:
	 * - Recorrido manual: no usa Collections.sort() ni Comparator.
	 * - Compara categorías con equals(), nunca con ==.
	 * - Compara años con &lt;, nunca con ==.
	 * - Si la categoría no existe o no tiene libros, retorna null, no lanza
	 *   excepción.
	 */
	public Libro libroMasAntiguoDeCategoria(String categoria) {
		// TODO: reemplazar esta línea por la lógica descrita arriba.
		throw new UnsupportedOperationException("TODO: completar libroMasAntiguoDeCategoria() en Biblioteca");
	}

	/**
	 * RETO OPCIONAL (10 pts extra): completar prestarPrimerDisponibleDeCategoria().
	 * <p>
	 * Enunciado: dado el nombre de una categoría, prestar el PRIMER libro
	 * disponible que se encuentre de esa categoría (recorriendo en el orden
	 * del catálogo) y retornarlo. Si no hay ninguno disponible en esa
	 * categoría, lanzar LibroNoDisponibleException con un mensaje claro.
	 * <p>
	 * Pista: reutiliza libro.prestar() (ya lanza la excepción si ESE libro
	 * puntual está prestado), pero aquí el punto es encontrar uno que SÍ
	 * esté disponible antes de intentar prestarlo (usa estaDisponible()).
	 * <p>
	 * Criterios de evaluación:
	 * - Compara categoría con equals().
	 * - Usa estaDisponible() antes de llamar prestar().
	 * - Si ninguno está disponible en esa categoría, lanza la excepción (no
	 *   retorna null).
	 */
	public Libro prestarPrimerDisponibleDeCategoria(String categoria) throws LibroNoDisponibleException {
		// TODO (opcional): reemplazar esta línea por la lógica descrita arriba.
		throw new UnsupportedOperationException(
				"TODO opcional: completar prestarPrimerDisponibleDeCategoria() en Biblioteca");
	}
}
