package com.example.EcoMarket.Pedidos.ControllerTest;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import Pedidos.controller.CuponController;
import Pedidos.model.Cupon;
import Pedidos.model.Pedido;
import Pedidos.service.CuponService;

@WebMvcTest(CuponController.class)
public class CuponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CuponService cuponService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cupon cupon;

    @BeforeEach
    void setup() {
        Pedido pedido = mock(Pedido.class);
        List<Pedido> pedidos = List.of(pedido);
        cupon = new Cupon();
        cupon.setId(1L);
        cupon.setCodigo("TESTCODE");
        cupon.setDescuento(10);
        cupon.setPedidos(pedidos); 
    }

    @Test
    public void testGetAllCupones() throws Exception {
        
        when(cuponService.obtenerTodos()).thenReturn(List.of(cupon));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/cupones"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].codigo").value("TESTCODE"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descuento").value(10));
    }

    @Test
    public void testGetCuponById() throws Exception {

        Long id = 1L;
        when(cuponService.ObtenerPorId(id)).thenReturn(cupon);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/cupones/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.codigo").value("TESTCODE"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descuento").value(10));
    }

    @Test
    public void testCreateCupon() throws Exception {

        when(cuponService.guardar(cupon)).thenReturn(cupon);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/cupones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cupon)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.codigo").value("TESTCODE"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descuento").value(10));
    }

    @Test
    public void testUpdateCupon() throws Exception {

        Long id = 1L;
        cupon.setId(id);
        when(cuponService.guardar(cupon)).thenReturn(cupon);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/cupones/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cupon)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.codigo").value("TESTCODE"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.descuento").value(10));
    }

    @Test
    public void testDeleteCupon() throws Exception {
        Long id = 1L;

        doNothing().when(cuponService).eliminar(id);
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/cupones/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(cuponService,new Times(1)).eliminar(id);
    }

}
