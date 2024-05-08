package com.example.edprojectfinal.ui.clientes;

public class ClienteBean {
    int idcliente;
    String ruc;
    String razon_social;
    String representante;
    String telefono;
    String email;

    public ClienteBean(){

    }

    public ClienteBean(int idcliente,String ruc,String razon_social,String representante,String telefono,String email){
        this.idcliente=idcliente;
        this.ruc=ruc;
        this.razon_social=razon_social;
        this.representante=representante;
        this.telefono=telefono;
        this.email=email;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
