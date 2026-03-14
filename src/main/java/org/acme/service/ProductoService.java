package org.acme.service;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Producto;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoService {

    public List<Producto> listarTodos() {
        return Producto.listAll();
    }

    public Optional<Producto> buscarPorId(String id) {
        // Panache con MongoDB usa ObjectId para las búsquedas por ID
        return Producto.findByIdOptional(new ObjectId(id));
    }

    public void crear(Producto producto) {
        producto.persist();
    }

    public void actualizar(String id, Producto productoActualizado) {
        Optional<Producto> productoOpt = buscarPorId(id);
        if (productoOpt.isPresent()) {
            Producto p = productoOpt.get();
            p.setNombre(productoActualizado.getNombre());
            p.setDescripcion(productoActualizado.getDescripcion());
            p.setPrecio(productoActualizado.getPrecio());
            p.setStock(productoActualizado.getStock());
            p.setCategoria(productoActualizado.getCategoria());
            p.setActivo(productoActualizado.isActivo());
            p.update(); // Guarda los cambios en MongoDB
        }
    }

    public boolean eliminar(String id) {
        return Producto.deleteById(new ObjectId(id));
    }
}