package co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity;

public class Libro {
    private static String id;
    private static String codigo;
    private static String titulo;
    private static int paginas;
    private static String imagenPortada;

    public Libro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getCodigo() {
        return  codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public static String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }
}
