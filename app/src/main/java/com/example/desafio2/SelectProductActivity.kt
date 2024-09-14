import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio2.R

class SelectProductActivity : AppCompatActivity() {
    private lateinit var carrito: Carrito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_product)

        carrito = Carrito()
        val listView: ListView = findViewById(R.id.listViewProducts)

        val productos = listOf(
            Producto(1, "Paracetamol", 5.0),
            Producto(2, "Ibuprofeno", 7.0),
            Producto(3, "Amoxicilina", 12.0),
            Producto(4, "Cetirizina", 10.0),
            Producto(5, "Omeprazol", 8.0),
            Producto(6, "Lorazepam", 15.0),
            Producto(7, "Dipirona", 6.0),
            Producto(8, "Dextrometorfano", 11.0),
            Producto(9, "Metformina", 9.0),
            Producto(10, "Paroxetina", 14.0)
        )

        val adapter = ProductoAdapter(this, productos) { producto ->
            carrito.agregarProducto(producto)
        }

        listView.adapter = adapter
    }
}
