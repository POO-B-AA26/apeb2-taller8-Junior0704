
import java.util.Random;

abstract class Personaje{
    public int vida, experiencia, batallaGanada;

    public Personaje(int vida) {
        this.vida = vida;
    }
    public abstract boolean ataque(Personaje personaje);
    public abstract boolean defensa(Personaje personaje);
    @Override
    public String toString() {
        return "Personaje{" + "vida=" + vida + ", experiencia=" + experiencia + ", batallaGanada=" + batallaGanada + '}';
    }
}
class Guerrero extends Personaje{
    public int fuerza;

    public Guerrero(int fuerza, int vida) {
        super(vida);
        this.fuerza = fuerza;
    }
    public boolean ataque(Personaje personaje){
        Random ale = new Random();
        boolean lucha = ale.nextBoolean();
        this.experiencia++;
        personaje.experiencia++;
        if (lucha) {
            this.batallaGanada++;
            personaje.vida--;
        }else{
            this.vida--;
            personaje.batallaGanada++;
        }
        return lucha;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }
    @Override
    public String toString() {
        return "Guerrero{" + "fuerza=" + fuerza + '}'+super.toString();
    }    
}
class Mago extends Personaje{
    public String hechizo;

    public Mago(String hechizo, int vida) {
        super(vida);
        this.hechizo = hechizo;
    }
    public boolean ataque(Personaje personaje){
        return false;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }
    @Override
    public String toString() {
        return "Mago{" + "hechizo=" + hechizo + '}'+super.toString();
    }
}
class Arquero extends Personaje{
    public int presicion, cantidadFlechas;

    public Arquero(int presicion, int cantidadFlechas, int vida) {
        super(vida);
        this.presicion = presicion;
        this.cantidadFlechas = cantidadFlechas;
    }
    public boolean ataque(Personaje personaje){
        return false;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }
    @Override
    public String toString() {
        return "Arquero{" + "presicion=" + presicion + ", cantidadFlechas=" + cantidadFlechas + '}'+super.toString();
    }
}
public class Problema_1_EjecutorBatalla {
    public static void main(String[] args) {
        Personaje guerrero = new Guerrero(7, 3);
        Personaje mago = new Mago("Abrazadabra", 2);
        Personaje arquero = new Arquero(10, 8, 3);
        System.out.println("Resultado ataque del Guerrero: " + guerrero.ataque(mago));
        System.out.println(guerrero);
        System.out.println(mago);
        System.out.println("\nResultado ataque del Guerrero: " + guerrero.ataque(arquero));
        System.out.println(guerrero);
        System.out.println(arquero);
    }
}
