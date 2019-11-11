

import org.junit.Before;
import  org.junit.Assert;

import org.junit.Test;

public class GestorProductosTest {
    GestorProductos g;

@Before
    public void setUp() {
g= new GestorProductosImpl();
        g.addProduct("COCA", 2.5);
        g.addProduct("CAFE-SOLO", 1);
        g.addProduct("BOCATA-JAMON", 3);
        g.addUser("28282X", "Toni");
    }
@Test

    public void testRealizar() throws UserNotFoundException{
        Pedido p = new Pedido("28282X"); //creas objeto de pedido, lo configuras y lo pasas
        //p.addUser("28282X");    // para anotarlo
        p.addLP(2, "COCA");
        p.addLP(1, "BOCATA-JAMON");

        g.anotarPedido("28282X", p);
        Assert.assertEquals("COCA",g.getProductos().get(0).getNombre());
        g.pedidosPorUsuario("28282X");
    }
    @Test
    public void testServir() {
        Pedido p1 = new Pedido("28282X"); //creas objeto de pedido, lo configuras y lo pasas
        //p.addUser("28282X");    // para anotarlo
        p1.addLP(2, "COCA");
        p1.addLP(1, "BOCATA-JAMON");

        g.anotarPedido("28282X", p1);

       Pedido p2= g.servirPedido();
        Assert.assertEquals("COCA" ,p2.getLPs().get(0).getProducto());

    }


}