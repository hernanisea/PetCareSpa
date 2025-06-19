package com.Microservicios.GestionUsuarios.controller;

public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private Boolean estado;
    private String telefono;
    private Long id;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
