package com.example.Notificaciones.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.repository.NotificacionRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class NotificacionService {
    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerTodas(){
        return notificacionRepository.findAll();
    }


    public List<Notificacion> obtenerPorCliente(Long clienteId){
        return notificacionRepository.findByClienteId(clienteId);

    }


    public Notificacion crearNotificacion(Notificacion notificacion){
        notificacion.setLeido(false);
        notificacion.setFecha(new java.util.Date());
        return notificacionRepository.save(notificacion);
    }


    public Notificacion marcarComoLeida(Long id){
        Notificacion noti = notificacionRepository.findById(id).orElseThrow();
        noti.setLeido(true);
        return notificacionRepository.save(noti);
    }

}
