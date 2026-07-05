/**
 * Problema 4 - Sistema de monitoreo de impactos del cambio climático en Ecuador
 * Una red de monitoreo ambiental tiene como objetivo registrar, analizar y reportar los 
 * impactos del cambio climático en diferentes regiones. En cada ubicación se instalan dispositivos
 * capaces de medir distintos indicadores climáticos como temperatura, precipitación, calidad del aire,
 * y humedad del suelo. Dependiendo de la región (costa, sierra y oriente), los dispositivos pueden variar en capacidades y protocolos de recolección.
 * Los datos recolectados deben almacenarse y analizarse periódicamente. Además, ciertas ubicaciones 
 * requieren generar reportes personalizados que destaquen riesgos ambientales como sequías, deslizamientos
 * o contaminación del aire. Algunos dispositivos pueden comportarse de forma especializada para detectar únicamente
 * ciertos tipos de indicadores dependiendo de la región (costa, sierra y oriente).
 * Requisitos funcionales:
 * Representar diferentes tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
 * Implementar métodos polimórficos que permitan procesar los datos según los tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
 * Generar reportes dinámicos en función del tipo de riesgo ambiental detectado según la región
 * @author Junior Rodriguez
 * @version 1.0
 */
import java.util.ArrayList;
abstract class Dispositivo{
    public double temperatura;
    public int calidadAire;
    public int precipitacion;
    public double humedadSuelo;

    public Dispositivo(double temperatura, int calidadAire, int precipitacion, double humedadSuelo) {
        this.temperatura = temperatura;
        this.calidadAire = calidadAire;
        this.precipitacion = precipitacion;
        this.humedadSuelo = humedadSuelo;
    }
    public abstract void procesarDatos();
    @Override
    public String toString() {
        return "Dispositivo{" + "temperatura=" + temperatura + ", calidadAire=" + calidadAire + ", precipitacion=" + precipitacion + ", humedadSuelo=" + humedadSuelo + '}';
    }
}
class DispositivoCosta extends Dispositivo{
    public boolean sequia;

    public DispositivoCosta(double temperatura, int calidadAire, int precipitacion, double humedadSuelo) {
        super(temperatura, calidadAire, precipitacion, humedadSuelo);
    }
    @Override
    public void procesarDatos(){
        if (this.precipitacion < 10 && this.humedadSuelo < 20.0) {
            this.sequia = true;
        }
    }
    @Override
    public String toString() {
        return "DispositivoCosta{" + "sequia=" + sequia + '}'+super.toString();
    }
}
class DispositivoSierra extends Dispositivo{
    public boolean deslizamiento;

    public DispositivoSierra( double temperatura, int calidadAire, int precipitacion, double humedadSuelo) {
        super(temperatura, calidadAire, precipitacion, humedadSuelo);
    }
    @Override
    public void procesarDatos(){
        if (this.precipitacion > 100) {
            this.deslizamiento = true;
        }
    }
    @Override
    public String toString() {
        return "DispositivoSierra{" + "deslizamiento=" + deslizamiento + '}'+super.toString();
    }
}
class DispositivoOriente extends Dispositivo{
    public boolean contaminacionAire;

    public DispositivoOriente( double temperatura, int calidadAire, int precipitacion, double humedadSuelo) {
        super(temperatura, calidadAire, precipitacion, humedadSuelo);
    }
    @Override
    public void procesarDatos(){
        if (this.calidadAire > 100) {
            this.contaminacionAire = true;
        }
    }
    @Override
    public String toString() {
        return "DispositivoOriente{" + "contaminacionAire=" + contaminacionAire + '}'+super.toString();
    }
}
class Monitoreo{
    public ArrayList<Dispositivo> dispositivos;

    public Monitoreo(ArrayList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }
    public void analizarDatos(){
        for(Dispositivo d: dispositivos){
            d.procesarDatos();
        }
    }
    public void generarReporte(){
        System.out.println("=== REPORTE DE IMPACTO CLIMATICO ===");
        for(Dispositivo d: dispositivos){
            System.out.println(d.toString());
        }
    }
    @Override
    public String toString() {
        return "Monitoreo{" + "dispositivos=" + dispositivos + '}';
    }
}
public class Problema_4_Ejecutor_Monitoreo {
    public static void main(String[] args) {
        ArrayList<Dispositivo> dispo = new ArrayList<>();
        dispo.add(new DispositivoCosta(35.5, 40, 5, 12));
        dispo.add(new DispositivoSierra(14.2, 25, 125, 80.5));
        dispo.add(new DispositivoOriente(28, 115, 70, 65));
        
        Monitoreo mon = new Monitoreo(dispo);
        mon.analizarDatos();
        mon.generarReporte();
    }
}
/**
 * run:
=== REPORTE DE IMPACTO CLIMATICO ===
DispositivoCosta{sequia=true}Dispositivo{temperatura=35.5, calidadAire=40, precipitacion=5, humedadSuelo=12.0}
DispositivoSierra{deslizamiento=true}Dispositivo{temperatura=14.2, calidadAire=25, precipitacion=125, humedadSuelo=80.5}
DispositivoOriente{contaminacionAire=true}Dispositivo{temperatura=28.0, calidadAire=115, precipitacion=70, humedadSuelo=65.0}
BUILD SUCCESSFUL (total time: 0 seconds)
 */