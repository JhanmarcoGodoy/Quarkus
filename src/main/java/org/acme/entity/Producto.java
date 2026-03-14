package org.acme.entity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@MongoEntity(collection = "productos")
public class Producto extends PanacheMongoEntity {

    private String nombre;
    
    private String descripcion;
    
    private double precio;
    
    private int stock;
    
    private String categoria;
    
    private String sku; 
    
    private boolean activo;
    
    private LocalDateTime fechaCreacion;

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
        this.persistOrUpdate();
    }
}