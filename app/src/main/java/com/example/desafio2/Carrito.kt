class Carrito {
    private val productosEnCarrito = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productosEnCarrito.add(producto)
    }

    fun obtenerProductos(): List<Producto> = productosEnCarrito

    fun calcularTotal(): Double {
        return productosEnCarrito.sumOf { it.precio }
    }
}
