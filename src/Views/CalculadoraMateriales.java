package Views;
/**
 * Clase para calcular los materiales de construccion
 * @author omarf
 */
public class CalculadoraMateriales {

    private double largo;
    private double alto;
    private double espesor;
    private double area;
    private double volumen;
    private double ancho;
    private double estructura;
    private double diametro;
    private double profundidad;
    
    /**
     * Calcula el area de un muro largo x alto
     * @param largo largo del muro
     * @param alto alto del muro
     * @return 
     */
    public double calcularAreaMuro(double largo, double alto){
        
        this.largo = largo;
        this.alto = alto;
        
        return largo * alto;
        
    }
    
    /**
     * Calcula el area de una losa y demás estructuras largo x ancho
     * @param largo largo de la estructura
     * @param ancho ancho de la estructura
     * @return resultado
     */
    public double calcularArea(double largo, double ancho){
     
        this.largo = largo;
        this.ancho = ancho;
        
        return largo * ancho;
    }
    
    /**
     * Calcula el area de una columna circular
     * @param diametro diametro de la columna circular
     * @return return resultado
     */
    
    public double calcularAreaColumnaC (double diametro){
        
        this.diametro = diametro;
        
        return Math.PI * diametro * diametro;
        
    }
    
    /**
     * Calcula el volumen de x estructura
     * @param area de la estrucutra
     * @param espesor espesor de la estructura, para ahorrarme tener que escribir codigo
     *                en el caso de esta función se puede utilizar también para calcular el volumen de los cimientos
     *                pero en vez de espesor poner la profundidad
     * @return 
     */
    public double calcularVolumen (double area, double espesor) {
        
        this.area = area;
        this.espesor = espesor;
        
        return area * espesor;
       
    }
    
    /**
     * Calcula la cantidad de concreto para un cimiento
     * @param largo Largo del cimiento
     * @param ancho Ancho del cimiento
     * @return  Resultado
     */
    
    public double calcularConcretoCimiento (double largo, double ancho, double profundidad){
        
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
        
        return largo * ancho * profundidad;
        
    }
    /**
     * Calcula la cantidad de blocks necesarios para la estructura
     * @param area area de la estructura
     * @return resultado
     */
    public double calcularBlocks (double area){
        
        this.area = area;
        
        return area * 13.75;
    }
    
    public double calcularArena (double volumen){
        
        this.volumen = volumen;
        
        return volumen * 0.67;
    }
    
    /**
     * Calcula la cantidad de arena necesaria para un muro
     * @param volumen volumen del muro
     * @return resultado
     */
    public double calcularCementoMuro (double volumen){
        
        this.volumen = volumen;
        
        return volumen * 7.6;
    }
    
    /**
     * Calcula lacantidad de cemento para una losa, también se puede utilizar para una columna
     * @param volumen de la losa o columna
     * @return resultado
     */

    public double calcularCementoLosa (double volumen){
        
        this.volumen = volumen;
        
        return volumen * 9.2;
    }
    
    /**
     * Calcula lacantidad de cemento para columna circular
     * @param volumen de la columna
     * @return resultado
     */
    
    public double calcularCementoCimiento (double volumen){
        
        this.volumen = volumen;
        
        return volumen * 8.5;
    }
 

    /**
     * Calcula la cantidad de varillas necesarias para la mayoria de estructuras
     * @param area area de la estructura
     * @return resultado
     */
    public double calcularVarilla (double area){

        this.area = area;
        
        return area * 25;
    }
    
    /**
     * Calcula la cantidad de varilla para una columna
     * @param area de la columna 
     * @return 
     */
    public double calcularVarillaColumna (double area) {
        
        this.area = area;
        
        return area * 120;
    }
    
    /**
     * Calcula la cantidad de varilla para una cimiento
     * @param area Area de la columna circular
     * @return 
     */
    public double calcularVarillaCimiento (double area) {
        
        this.area = area;
        
        return area * 80;
    }
    
    /** 
     * Calcula la cantidad de grava para una losa, TAMBIEN SE UTILIZA PARA CALCULAR LA ARENA DE UNA LOSA
     * @param volumen volumen de la losa
     * @return resultado
     */
    public double calcularGrava (double volumen){
        
        this.volumen = volumen;
        
        return volumen * 0.6;
    }
}
    

