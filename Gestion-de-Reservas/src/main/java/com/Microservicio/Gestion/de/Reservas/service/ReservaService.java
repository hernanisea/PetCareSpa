package com.Microservicio.Gestion.de.Reservas.service;

import com.Microservicio.Gestion.de.Reservas.model.Reservas;
import com.Microservicio.Gestion.de.Reservas.repository.ReservaRepository;
import com.Microservicio.Gestion.de.Reservas.webclient.MascotaClient;
import com.Microservicio.Gestion.de.Reservas.webclient.UsuarioClient;
import com.Microservicio.Gestion.de.Reservas.webclient.VeterinarioClient;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private MascotaClient mascotaClient;
    @Autowired
    private VeterinarioClient veterinarioClient;

    //metodo para obtener todos los pedidos
    public List<Reservas> getReserva(){
        return reservaRepository.findAll();
    }

    //metodo para guardar un nuevo pedido
    public Reservas saveReserva(Reservas nuevaReserva){
        //verificar si el cliente existe
        //debo comunicarme con el microservicio del cliente
        Map<String, Object> usuario = usuarioClient.getUsuarioById(nuevaReserva.getUsuarioId());
        if(usuario == null || usuario.isEmpty()){
            //no se consiguio el cliente mediante el metodo get del otro microservicio
            throw new RuntimeException("Usuario no encontrado. No se puede guardar el pedido");
        
        }
         Map<String, Object> mascota = mascotaClient.getMascotaById(nuevaReserva.getMascotaId());
        if(mascota == null || mascota.isEmpty()){
            //no se consiguio el cliente mediante el metodo get del otro microservicio
            throw new RuntimeException("Mascota no encontrada. No se puede guardar el pedido");
        }
         Map<String, Object> veterinario = veterinarioClient.getVeterinarioById(nuevaReserva.getVeterinarioId());
        if(veterinario == null || veterinario.isEmpty()){
            //no se consiguio el cliente mediante el metodo get del otro microservicio
            throw new RuntimeException("Veterinario no encontrado. No se puede guardar el pedido");
        //si se encontro el cliente
        }
        return reservaRepository.save(nuevaReserva);
    }
}
