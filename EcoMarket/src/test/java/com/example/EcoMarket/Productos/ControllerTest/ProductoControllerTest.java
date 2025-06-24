package com.example.EcoMarket.Productos.ControllerTest;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import Productos.controller.ProductoController;
import Productos.model.Producto;
import Productos.model.Proveedor;
import Productos.service.ProductoService;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    void setup() {
        Proveedor proveedor = mock(Proveedor.class);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción del producto de prueba");
        producto.setCategoria("Categoría Test");
        producto.setPrecio(100.0);
        producto.setProveedor(proveedor);
        
    }

    @Test
    public void testGetAllProductos() throws Exception {
        when(productoService.obtenerTodos()).thenReturn(List.of(producto));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/productos"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("Producto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Descripción del producto de prueba"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].categoria").value("Categoría Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].precio").value(100.0));
    }

    @Test
    public void testGetProductoById() throws Exception {
        when(productoService.obtenerPorId(1L)).thenReturn(producto);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/productos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Producto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descripcion").value("Descripción del producto de prueba"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.categoria").value("Categoría Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100.0));
    }

    @Test
    public void testCreateProducto() throws Exception {
        when(productoService.guardar(producto)).thenReturn(producto);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/productos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Producto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descripcion").value("Descripción del producto de prueba"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.categoria").value("Categoría Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100.0));
    }

    @Test
    public void testUpdateProducto() throws Exception {
        producto.setNombre("Producto Actualizado");
        when(productoService.guardar(producto)).thenReturn(producto);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/productos/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Producto Actualizado"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descripcion").value("Descripción del producto de prueba"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.categoria").value("Categoría Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100.0));
    }

    @Test
    public void testDeleteProducto() throws Exception {
        Long id = 1L;

        doNothing().when(productoService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/productos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(productoService, new Times(1)).eliminar(id);
    }

}
