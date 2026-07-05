/**
 * Problema 6 - Simulador de conflictos bélicos mundiales 2025
 * La ONU le solicita desarrollar un simulador de conflictos bélicos mundiales en el lenguaje
 * de alto nivel orientado a objetos Java, considerando sus cuatro pilares fundamentales: 
 * abstracción, encapsulamiento, herencia y polimorfismo, cumpliendo con los siguientes lineamientos:
 * Requisitos Funcionales
 * De manera general, cada nación debe ser representada con la siguiente información: 
 * Nombre de la nación, Número de habitantes de la nación, Cantidad de recursos económicos disponibles,
 * Nivel de poder militar (valor entre 1 y 100), su estado de conflicto que indica si la nación está 
 * actualmente en conflicto o no, y cualquier otra información que usted considere necesaria. No olvide
 * implementar los métodos y/o constructores básicos para procesar esta información dados todos los requerimientos.
 * A su vez se requiere la información de las naciones desarrolladas con alta tecnología militar, como: 
 * Si la nación dispone de tecnología avanzada. Para estas naciones avanzadas, implementar el cálculo del
 * impacto, el cual considera un bono de tecnología para el incremento de su poder militar (no olvide que para este
 * último la restricción es de 1-100, y en el caso de sobre pasar, asigne directamente 100).
 * De igual forma se necesita conocer de las naciones en vías de desarrollo su nivel de recursos limitados 
 * (recursos económicos y poder militar por cada N habitantes), así como la implementación del cálculo del impacto,
 * el cual reduce el impacto en el conflicto debido a sus recursos limitados. Queda a su criterio matemático y/o 
 * estadístico el planteamiento del modelo matemático (con las variables/parámetros que tenga a bien) para calcular este factor de impacto.
 * Para las naciones desarrolladas o en vías de desarrollo, considere sus naciones aliadas, lo cual es decisivo para
 * incrementar o decrementar su nivel de impacto directamente a su poder militar, pero solo si tiene aliados disponibles.
 * El programa debe permitir declarar conflictos entre dos naciones seleccionadas con un proceso aleatorio/randomico.
 * Calcular las consecuencias del conflicto utilizando polimorfismo y la implementación de cálculo de impacto.
 * Consecuencias del conflicto:
 * Reducción del 5% de población por cada diferencia en los niveles de poder militar.
 * Reducción del 10% de recursos de la nación derrotada.
 * Si las naciones tienen el mismo nivel de poder militar, ambas pierden el 5% de recursos.
 * Al finalizar el programa, debe mostrar un reporte con el estado actual de cada nación (población, recursos
 * y estado de conflicto, etc), así como el total de conflictos que se simularon entre N naciones.
 * @author Junior Rodriguez
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Random;
abstract class Nacion{
    public String nombre;
    public int numeroHabitantes, cantidadRecursos, poderMilitar;
    public boolean estadoConflicto;
    public ArrayList<Nacion> aliados;

    public Nacion(String nombre, int numeroHabitantes, int cantidadRecursos, int poderMilitar, boolean estadoConflicto) {
        this.nombre = nombre;
        this.numeroHabitantes = numeroHabitantes;
        this.cantidadRecursos = cantidadRecursos;
        this.poderMilitar = poderMilitar;
        this.estadoConflicto = estadoConflicto;
        this.aliados = new ArrayList<>();
    }
    public boolean estaEstadoConflicto(){
        return this.estadoConflicto;
    }
    public abstract int calculoImpacto();
    @Override
    public String toString() {
        return "Nacion{" + "nombre=" + nombre + ", numeroHabitantes=" + numeroHabitantes + ", cantidadRecursos=" + cantidadRecursos + ", poderMilitar=" + poderMilitar + ", estadoConflicto=" + estadoConflicto + ", aliados=" + aliados + '}';
    }
}
class NacionDesarrollada extends Nacion{
    public boolean tecnologiaAvanzada;

    public NacionDesarrollada(boolean tecnologiaAvanzada, String nombre, int numeroHabitantes, int cantidadRecursos, int poderMilitar, boolean estadoConflicto) {
        super(nombre, numeroHabitantes, cantidadRecursos, poderMilitar, estadoConflicto);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }
    @Override
    public int calculoImpacto(){
        int impactoTotal = this.poderMilitar;

        if (tecnologiaAvanzada) {
            impactoTotal += 20; 
        }
        if (!aliados.isEmpty()) {
            impactoTotal += aliados.size() * 5;
        }
        if (impactoTotal > 100) {
            impactoTotal = 100;
        }
        return impactoTotal;
    }
    @Override
    public String toString() {
        return "NacionDesarrollada{" + "tecnologiaAvanzada=" + tecnologiaAvanzada + '}';
    }
}
class NacionViaDesarrollo extends Nacion{
    public int recursosLimitados;
    public int habitantesPorUnidad;

    public NacionViaDesarrollo(int recursosLimitados, int habitantesPorUnidad, String nombre, int numeroHabitantes, int cantidadRecursos, int poderMilitar, boolean estadoConflicto) {
        super(nombre, numeroHabitantes, cantidadRecursos, poderMilitar, estadoConflicto);
        this.recursosLimitados = recursosLimitados;
        this.habitantesPorUnidad = habitantesPorUnidad;
    }
    @Override
    public int calculoImpacto(){
        int impactoTotal = this.poderMilitar;

        if (habitantesPorUnidad > 0) {
            double factorPenalizacion = (double) recursosLimitados / (numeroHabitantes / (double) habitantesPorUnidad);
            impactoTotal -= (int) (factorPenalizacion * 10);
        }
        if (!aliados.isEmpty()) {
            impactoTotal += aliados.size() * 3;
        } else {
            impactoTotal -= 5;
        }
        if (impactoTotal > 100) impactoTotal = 100;
        if (impactoTotal < 1) impactoTotal = 1;
        return impactoTotal;
    }
    @Override
    public String toString() {
        return "NacionViaDesarrollo{" + "recursosLimitados=" + recursosLimitados + ", habitantesPorUnidad=" + habitantesPorUnidad + '}';
    }
}
class ONU{
    public ArrayList<Nacion> naciones;
    public int contadorConflictos;

    public ONU() {
        this.naciones = new ArrayList<>();
        this.contadorConflictos = contadorConflictos;
    }
    public void estadoNaciones(){
        System.out.println("=== REPORTE ACTUAL DE LAS NACIONES ===");
        for (Nacion n : naciones) {
            System.out.println(n.toString());
        }
    }
    public int totalConflictos() {
        return this.contadorConflictos;
    }
    public void iniciarConflicto(){
        if (naciones.size() < 2) {
            System.out.println("No hay suficientes naciones.");
            return;
        }
        Random ale = new Random();
        int indice1 = ale.nextInt(naciones.size());
        int indice2 = (indice1 + 1) % naciones.size();
        Nacion n1 = naciones.get(indice1);
        Nacion n2 = naciones.get(indice2);
        n1.estadoConflicto = true;
        n2.estadoConflicto = true;
        contadorConflictos++;
        System.out.println("\n=== CONFLICTO INTERNACIONAL ===");
        System.out.println("Guerra: " + n1.nombre + " vs " + n2.nombre);
    
        evaluarConsecuecias(n1, n2);
    }
    public void evaluarConsecuecias(Nacion n1, Nacion n2) {
        int imp1 = n1.calculoImpacto();
        int imp2 = n2.calculoImpacto();

        System.out.println("Impacto militar de " + n1.nombre + ": " + imp1);
        System.out.println("Impacto militar de " + n2.nombre + ": " + imp2);

        if (imp1 > imp2) {
            n2.numeroHabitantes -= (int) (n2.numeroHabitantes * 0.10);
            n2.cantidadRecursos -= (int) (n2.cantidadRecursos * 0.15);
            System.out.println("Resultado: Victoria para " + n1.nombre);
        } else if (imp2 > imp1) {
            n1.numeroHabitantes -= (int) (n1.numeroHabitantes * 0.10);
            n1.cantidadRecursos -= (int) (n1.cantidadRecursos * 0.15);
            System.out.println("Resultado: Victoria para " + n2.nombre);
        } else {
            n1.cantidadRecursos -= (int) (n1.cantidadRecursos * 0.05);
            n2.cantidadRecursos -= (int) (n2.cantidadRecursos * 0.05);
            System.out.println("Resultado: Empate tactico.");
        }
        n1.estadoConflicto = false;
        n2.estadoConflicto = false;
    }
}
public class Problema_6_Ejecutor_Onu {
    public static void main(String[] args) {
        ONU simul = new ONU();
        NacionDesarrollada n1 = new NacionDesarrollada(true, "EEUU", 335000000, 600000, 85, false);
        NacionDesarrollada n2 = new NacionDesarrollada(true, "Alemania", 84000000, 420000, 78, false);
        NacionViaDesarrollo n3 = new NacionViaDesarrollo(1200, 50000, "Brasil", 84000000, 420000, 78, false);
        NacionViaDesarrollo n4 = new NacionViaDesarrollo(3000, 150000, "India", 1400000000, 260000, 65, false);
        
        n1.aliados.add(n2);
        n2.aliados.add(n1);
        n3.aliados.add(n1);
        
        simul.naciones.add(n1);
        simul.naciones.add(n2);
        simul.naciones.add(n3);
        simul.naciones.add(n4);
        
        simul.iniciarConflicto();
        simul.estadoNaciones();
        
        System.out.println("\n[REPORTE] Total de conflictos evaluados: " + simul.totalConflictos());
    }
}
/**
 * run:

=== CONFLICTO INTERNACIONAL ===
Guerra: Alemania vs Brasil
Impacto militar de Alemania: 100
Impacto militar de Brasil: 74
Resultado: Victoria para Alemania
=== REPORTE ACTUAL DE LAS NACIONES ===
NacionDesarrollada{tecnologiaAvanzada=true}
NacionDesarrollada{tecnologiaAvanzada=true}
NacionViaDesarrollo{recursosLimitados=1200, habitantesPorUnidad=50000}
NacionViaDesarrollo{recursosLimitados=3000, habitantesPorUnidad=150000}

[REPORTE] Total de conflictos evaluados: 1
BUILD SUCCESSFUL (total time: 0 seconds)
 */