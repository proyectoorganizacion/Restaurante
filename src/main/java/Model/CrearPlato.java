package Model;

public class CrearPlato {
    private Long id;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String urlImagen;
    private String categoria;
    private String nitRestaurante;
    private Boolean activo;

    public CrearPlato(Long id, String nombre, Integer precio, String descripcion, String urlImagen, String categoria, String nitRestaurante) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.categoria = categoria;
        this.nitRestaurante = nitRestaurante;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getnitRestaurante() {
        return nitRestaurante;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setnitRestaurante(String nitRestaurante) {
        this.nitRestaurante = nitRestaurante;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "CrearPlato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idRestaurante=" + nitRestaurante +
                ", activo=" + activo +
                '}';
    }       /**
     * Modifica el precio y la descripción del plato.
     *
     * @param nuevoPrecio   Nuevo precio a asignar.
     * @param nuevaDescripcion  Nueva descripción a asignar.
     * @param nitRestauranteUsuario ID del restaurante del usuario que intenta realizar la modificación.
     * @throws IllegalArgumentException Si el ID del restaurante no coincide con el del plato.
     */
    public void modificarPlato(Integer nuevoPrecio, String nuevaDescripcion, String nitRestauranteUsuario) {
        if (!this.nitRestaurante.equals(nitRestauranteUsuario)) {
            throw new IllegalArgumentException("No tienes permiso para modificar un plato de otro restaurante.");
        }

        if (nuevoPrecio != null && nuevoPrecio > 0) {
            this.precio = nuevoPrecio;
        }

        if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
            this.descripcion = nuevaDescripcion;
        }
    }
}


