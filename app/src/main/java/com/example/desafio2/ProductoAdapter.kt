import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.desafio2.R

class ProductoAdapter(
    context: Context,
    private val productos: List<Producto>,
    private val onAgregarAlCarrito: (Producto) -> Unit
) : ArrayAdapter<Producto>(context, 0, productos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false)

        val producto = getItem(position) ?: return view

        val nombreTextView: TextView = view.findViewById(R.id.tvProductoNombre)
        val precioTextView: TextView = view.findViewById(R.id.tvProductoPrecio)
        val agregarButton: Button = view.findViewById(R.id.btnAgregarAlCarrito)

        nombreTextView.text = producto.nombre
        precioTextView.text = "$${producto.precio}"

        agregarButton.setOnClickListener { onAgregarAlCarrito(producto) }

        return view
    }
}
