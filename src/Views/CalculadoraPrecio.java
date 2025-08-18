/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

/**
 *
 * @author omarf
 */
public class CalculadoraPrecio {
    
    double concreto;
    double cemento;
    double arena;
    double grava;
    double varilla;
    double blocks;
    
    public double calcularBlock (double blocks){
        
        this.blocks = blocks;
        
        return blocks * 31;
    }
    
    public double calcularArena (double arena){
        
        this.arena = arena;
        
        return arena * 1700;
    }
    
    public double calcularCemento (double cemento){
        
        this.cemento = cemento;
        
        return cemento * 545;
    }
    
    public double precioMuro (double blocks, double arena, double cemento){
        
        this.blocks = blocks;
        this.arena = arena;
        this.cemento = cemento;
        
        return blocks + arena + cemento;
    }
}
