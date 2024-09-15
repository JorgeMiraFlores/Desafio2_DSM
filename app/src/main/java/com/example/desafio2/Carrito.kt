import android.os.Parcel
import android.os.Parcelable

data class Carrito(
    private val productosEnCarrito: MutableList<Producto> = mutableListOf()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Producto.CREATOR) ?: mutableListOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(productosEnCarrito)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun agregarProducto(producto: Producto) {
        productosEnCarrito.add(producto)
    }

    fun obtenerProductos(): List<Producto> = productosEnCarrito

    fun calcularTotal(): Double {
        return productosEnCarrito.sumOf { it.precio }
    }

    companion object CREATOR : Parcelable.Creator<Carrito> {
        override fun createFromParcel(parcel: Parcel): Carrito {
            return Carrito(parcel)
        }

        override fun newArray(size: Int): Array<Carrito?> {
            return arrayOfNulls(size)
        }
    }
    fun vaciarCarrito() {
        productosEnCarrito.clear()
    }

}
