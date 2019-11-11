import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class User {
    private String idUser;
    private String nom;
    public Queue<Pedido> pedidos;


    public User(String id, String nombre) {
        idUser = id;
        nom = nombre;
        pedidos = new LinkedList<Pedido>();
    }

    public String getId() {
        return idUser;
    }

    public String getNombre() {
        return nom;
    }

    public void addPedido(Pedido c) {

        pedidos.add(c);
    }

    public  Queue<Pedido> getPedido(String id) {

        return this.pedidos;
    }

//    public void Pedido() {
//        pedidos = new LinkedList<Pedido>(); //no esta bien
//    }


    //public void addUser(String id, String name){
        // users.put(id, new User(id, name));

}


