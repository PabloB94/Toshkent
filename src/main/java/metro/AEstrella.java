package metro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AEstrella {

    private static final double TRANSBORDO = 3;
    private HashMap<Double, Estacion> abierta;
    private List<Estacion> cerrada;
    private Estacion destino;
    private double ultimoTiempo;
    private ListaParadas construcciones;

    public AEstrella() {
        this.ultimoTiempo = 0;
        this.destino = null;
    }

    private void agregarEnAbierta(int linea, Estacion padre, String hijo) {
        Estacion estacionHijo = (ListaParadas.getLineas()[linea - 1]).get(hijo);

        if (!this.cerrada.contains(estacionHijo)) {
            double pesoDesdePadre = padre.getPesoRecorrido();
            pesoDesdePadre += padre.getNombre().equals(hijo) ? (TRANSBORDO + Math.random() / 100) : padre.getTiempoReal(estacionHijo);

            if (estacionHijo.getPesoRecorrido() == 0 || estacionHijo.getPesoRecorrido() >= pesoDesdePadre) {
                estacionHijo.setPadre(padre);
                estacionHijo.setPesoRecorrido(pesoDesdePadre);
            }

            double peso = estacionHijo.getPesoRecorrido() + estacionHijo.getTiempoHeuristico(this.destino);
            this.abierta.put(peso, estacionHijo);
        }
    }

    private void explorar() {
        Estacion estacionActual;
        List<Double> pesos;

        while (!this.abierta.containsValue(this.destino)) {
            /* Se coge la estacion con menor peso de la lista abierta */
            pesos = new ArrayList<Double>(this.abierta.keySet());
            Collections.sort(pesos);
            estacionActual = this.abierta.remove(pesos.get(0));

            if (this.cerrada.contains(estacionActual)) continue;

            /* Se exploran las estaciones que NO se encuentren en lista cerrada */
            int lineaActual = estacionActual.getLinea();
            agregarEnAbierta(lineaActual, estacionActual, estacionActual.getEstacionA());

            if (estacionActual.existeB())
                agregarEnAbierta(lineaActual, estacionActual, estacionActual.getEstacionB());

            if (estacionActual.getNumEnlaces()) {
                lineaActual = estacionActual.getConexionA();
                agregarEnAbierta(lineaActual, estacionActual, estacionActual.getNombre());
            }

            /* Se inserta la estacion actual en lista cerrada para indicar que ya se ha explorado */
            this.cerrada.add(estacionActual);
        }

        /* Se coge el destino de la lista abierta y se inserta en cerrada */
        for (Map.Entry<Double, Estacion> entry : this.abierta.entrySet())
            if (entry.getValue().equals(this.destino))
                this.cerrada.add(entry.getValue());
    }

    public double getUltimoTiempo() {
        return this.ultimoTiempo;
    }

    public List<Estacion> recorrido(Estacion origen, Estacion destino) {
        List<Estacion> recorrido = new ArrayList<>();

        /* Se explora el grafo */
        this.abierta = new HashMap<Double, Estacion>();
        this.cerrada = new ArrayList<Estacion>();
        this.construcciones = new ListaParadas();
        this.destino = destino;
        this.abierta.put(origen.getTiempoHeuristico(destino), origen);
        explorar();

        /* Se obtiene el recorrido mas optimo */
        Estacion meta = this.cerrada.get(this.cerrada.size() - 1);
        recorrido.add(meta);
        this.ultimoTiempo = meta.getPesoRecorrido();
        while (meta.existePadre()) {
            recorrido.add(meta.getPadre());
            meta = meta.getPadre();
        }

        return recorrido;
    }
}