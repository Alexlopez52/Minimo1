import java.util.*;
import org.apache.log4j.Logger;

public class GestorProductosImpl implements GestorProductos{
    static final Logger log=Logger.getLogger(GestorProductosImpl.class);

    private HashMap<String, User> users;
    private List<Producto> productos;//hacer una funcion que haga addproducti y guardar
    private Queue<Pedido> pedidos;   //aqui



    public GestorProductosImpl(){
        this.productos = new ArrayList<Producto>(); //inicializado en test
        this.pedidos = new LinkedList<Pedido>();
        this.users = new HashMap<String, User>();
    }

public List<Producto> getProductos()
{
    return productos;
}

    public void addProduct(String nombre, double precio)
    {
        if (productos.size()==0) {
            productos.add(new Producto(0, nombre, 0, precio));
        }
        else productos.add(new Producto(this.productos.get(productos.size()-1).getId(), nombre, 0, precio));


    }
      public void addUser(String id, String nombre) {
        users.put(id, new User(id,nombre));
    }





    public List<Producto> productOrdPrecio() { //1
        //We create a copy of the list
        List<Producto> ret = this.productos; //mirar con logger
          ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Ascending order
                return (int) (o1.getPrecio() - o2.getPrecio());
            }
        });
        return ret;

    }

    public void anotarPedido (String id, Pedido c){ //2 //ID ALOMEJOR INNECESARIO
       //enlazar con un user
        users.get(id).addPedido(c); //pilla a alguien segun su id y le hace la funcion para añadir pedido
        pedidos.add(c);

    }


    public Pedido servirPedido(){ //3

         Pedido p = pedidos.remove(); // lo guardo en p para verlo luego en el logger
        int i=0;
        while (i<p.getLPs().size())
        {
            String nombre=p.getLPs().get(i).getProducto();
            int j=0;
            while(j<productos.size())
            {
                if(productos.get(j).getNombre()== nombre)
                {
                productos.get(j).addventa(p.getLPs().get(i).getCantidad());
                j=productos.size();
                }
                 else j++;
            }
            i++;
        }
              return p;
    }
    public List<Producto> productOrdVentas(){ //4
        //We create a copy of the list
        List<Producto> ret = this.productos;
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Descending order
                return (-1)*(o1.getNumVentas()-o2.getNumVentas());
            }
        });

        return ret;

    }
    public Queue<Pedido> pedidosPorUsuario(String id) throws UserNotFoundException{ //5

        Queue<Pedido> pedidosUser=null;
        //We want to find the given user
        User theUser = this.users.get(id);//preguntar sobre hashmap

        if(theUser!=null){
            pedidosUser = theUser.getPedido(id);
        }
        else throw new UserNotFoundException();
log.info(pedidosUser.peek().getLPs().get(0).getProducto());
        return pedidosUser;

    }
}

