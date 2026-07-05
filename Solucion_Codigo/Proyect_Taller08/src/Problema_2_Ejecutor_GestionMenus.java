/**
 * Problema 2 - Gestión de menus en un Restaurant
 * En un restaurant se tiene diferentes tipos de menú para ofrecer a los clientes. Una 
 * cuenta por pagar está compuesta por características como: nombre del cliente, listado 
 * de todos las cartas(menú) solicitados por el cliente, valor a cancelar total, subtotal, Iva.
 *Los tipos de menú del restaurant son:
 *Menú a la carta
 *
 *nombre del plato
 *valor del menú
 *valor inicial del menú
 *valor de porción de guarnición
 *valor de bebida
 *porcentaje adicional por servicio en relación del valor inicial del menú
 *Menú del día
 *
 *nombre del plato
 *valor del menú
 *valor inicial del menú
 *valor de postre
 *valor de bebida
 *Menú de niños
 *
 *nombre del plato
 *valor del menú
 *valor inicial del menú
 *valor de porción de helado
 *valor de porción de pastel
 *Menú económico
 *
 *nombre del plato
 *valor del menú
 *valor inicial del menú
 *porcentaje de descuento, en referencia al valor inicial del menú
 * @author Junior Rodriguez
 * @version 1.0
 */
import java.util.ArrayList;
abstract class Menu{
    public String nombrePlato;
    public double valorMenu;
    public double valorInicialMenu;

    public Menu(String nombrePlato, double valorMenu, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorMenu = valorMenu;
        this.valorInicialMenu = valorInicialMenu;
    }
    public abstract double calcularPrecioMenu();
    @Override
    public String toString() {
        return "Menu{" + "nombrePlato=" + nombrePlato + ", valorMenu=" + valorMenu + ", valorInicialMenu=" + valorInicialMenu + '}';
    }
}
class Carta extends Menu{
    public double valorPorcionGuarnicion;
    public double valorBebida;
    public double porcentajeAdicional;

    public Carta(double valorPorcionGuarnicion, double valorBebida, double porcentajeAdicional, String nombrePlato, double valorMenu, double valorInicialMenu) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeAdicional = porcentajeAdicional;
    }
    @Override
    public double calcularPrecioMenu(){
        double servicio = valorInicialMenu * (porcentajeAdicional / 100.0);
        this.valorMenu = valorInicialMenu + valorPorcionGuarnicion + valorBebida + servicio;
        return this.valorMenu;
    }
    @Override
    public String toString() {
        return "Carta{" + "valorPorcionGuarnicion=" + valorPorcionGuarnicion + ", valorBebida=" + valorBebida + ", porcentajeAdicional=" + porcentajeAdicional + '}';
    }
}
class Dia extends Menu{
    public double valorPostre;
    public double valorBebida;

    public Dia(double valorPostre, double valorBebida, String nombrePlato, double valorMenu, double valorInicialMenu) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }
    @Override
    public double calcularPrecioMenu(){
        this.valorMenu = valorInicialMenu + valorPostre + valorBebida;
        return this.valorMenu;
    }
    @Override
    public String toString() {
        return "Dia{" + "valorPostre=" + valorPostre + ", valorBebida=" + valorBebida + '}';
    }
}
class Niños extends Menu{
    public double valorPorcionHelado;
    public double valorPorcionPastel;

    public Niños(double valorPorcionHelado, double valorPorcionPastel, String nombrePlato, double valorMenu, double valorInicialMenu) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }
    @Override
    public double calcularPrecioMenu(){
        this.valorMenu = valorInicialMenu + valorPorcionHelado + valorPorcionPastel;
        return this.valorMenu;
    }
    @Override
    public String toString() {
        return "Ninos{" + "valorPorcionHelado=" + valorPorcionHelado + ", valorPorcionPastel=" + valorPorcionPastel + '}';
    }
}
class Economico extends Menu{
    public double porcentajeDescuento;

    public Economico(double porcentajeDescuento, String nombrePlato, double valorMenu, double valorInicialMenu) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }
    @Override
    public double calcularPrecioMenu(){
        double descuento = valorInicialMenu * (porcentajeDescuento / 100.0);
        this.valorMenu = valorInicialMenu - descuento;
        return this.valorMenu;
    }
    @Override
    public String toString() {
        return "Economico{" + "porcentajeDescuento=" + porcentajeDescuento + '}';
    }
}
class Cuenta{
    public String nombreCliente;
    public ArrayList<Menu> menus;
    public double valorTotal;
    public double subtotal;
    public double iva;

    public Cuenta(String nombreCliente, ArrayList<Menu> menus) {
        this.nombreCliente = nombreCliente;
        this.menus = menus;
    }
    public void calcularTotal(){
        this.subtotal = 0;
        for (Menu m : menus) {
            m.calcularPrecioMenu();
            this.subtotal += m.valorMenu;
        }
        this.iva = this.subtotal * 0.15;
        this.valorTotal = this.subtotal + this.iva;
    }
    @Override
    public String toString() {
        return "Cuenta{" + "nombreCliente=" + nombreCliente + ", menus=" + menus + ", valorTotal=" + valorTotal + ", subtotal=" + subtotal + ", iva=" + iva + '}';
    }
}
public class Problema_2_Ejecutor_GestionMenus {
    public static void main(String[] args) {
        ArrayList<Menu> pedidos = new ArrayList<>();
        
        pedidos.add(new Carta(2.50, 1.50, 10, "Lomo Fino", 0, 12));
        pedidos.add(new Dia(1, 0.50, "Seco de Pollo", 0, 3.50));
        pedidos.add(new Niños(1.25, 1.50, "Hamburguesa", 0, 4));
        pedidos.add(new Economico(15, "Almuerzo Carnoso", 0, 3));
        
        Cuenta c1 = new Cuenta("Junior Rodriguez", pedidos);
        c1.calcularTotal();
        System.out.println(c1);
    }
}
/**
 * run:
Cuenta{nombreCliente=Junior Rodriguez, menus=[Carta{valorPorcionGuarnicion=2.5, valorBebida=1.5, porcentajeAdicional=10.0}, Dia{valorPostre=1.0, valorBebida=0.5}, Ninos{valorPorcionHelado=1.25, valorPorcionPastel=1.5}, Economico{porcentajeDescuento=15.0}], valorTotal=36.225, subtotal=31.5, iva=4.725}
BUILD SUCCESSFUL (total time: 0 seconds)

 */
