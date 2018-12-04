package metro;

public class Estacion 
{
	private Estacion padre;
	private double pesoRecorrido;
	private Coordenada coordenada;
	private String nombre, estacionA, estacionB;
	private static final double VELOCIDAD = 0.75; //45kmpH
	private int linea, conexionA,tiempoA, tiempoB;
	private boolean num_enlaces;

	public Estacion(int linea, String nombre, String estacionA, int tiempoA, String estacionB, int tiempoB,
			int conexionA, String latitud, String longitud){
		this.linea = linea;
		this.nombre = nombre;
		this.estacionA = estacionA;
		this.tiempoA = tiempoA;
		this.estacionB = estacionB;
		this.tiempoB = tiempoB;
		this.num_enlaces = false;
		if(conexionA > 0) {
			this.conexionA = conexionA;
			this.num_enlaces = true;
		}
		this.coordenada = new Coordenada(latitud, longitud);
		this.padre = null;
		this.pesoRecorrido = 0;
	}
	
	public boolean existePadre(){
		return this.padre != null; 
	}
	
	public boolean existeB(){
		return this.estacionB != null;
	}	
	
	public int getLinea(){
		return this.linea;
	}
	
	public boolean getNumEnlaces(){
		return this.num_enlaces;
	}

	public int getConexionA(){
		return this.conexionA;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getEstacionA(){
		return this.estacionA;
	}

	public String getEstacionB(){
		return this.estacionB;
	}
	
	public Estacion getPadre() {
		return this.padre;
	}
	
	public double getPesoRecorrido(){
		return this.pesoRecorrido;
	}
	
	public double getTiempoHeuristico(Estacion destino){
		return tiempoHeuristicoMinutos(this.calculos(destino));
	}

	public void setPadre(Estacion estacion){
		this.padre = estacion;
	}
	
	public void setPesoRecorrido(double peso){
		this.pesoRecorrido = peso;
	}
	
	public void setLinea(int linea){
		this.linea = linea;
	}

	private double tiempoHeuristicoMinutos(double distancia){
		return distancia / VELOCIDAD;
	}
	
	public boolean equals(Object o){
		Estacion estacion = o instanceof Estacion ? (Estacion) o : null;
		return estacion != null && this.getNombre().equals(estacion.getNombre()) && this.getLinea() == estacion.getLinea();
	}
	
	public int getTiempoReal(Estacion destino)
	{
		String estacion = destino.getNombre();
		int tiempo = 0;
		if(this.estacionA.equals(estacion)) 
			tiempo = this.tiempoA;
		else if(this.estacionB.equals(estacion))
			tiempo = this.tiempoB;
		return tiempo;
	}

	private double calculos(Estacion destino)
	{
		double lat1 = this.coordenada.deegreToDecimalLat(), lat2 = destino.coordenada.deegreToDecimalLat();
		double lon1 = this.coordenada.deegreToDecimalLon(), lon2 = destino.coordenada.deegreToDecimalLon();
		
		double R = 6372.8, fi1 = Math.toRadians(lat1), fi2 = Math.toRadians(lat2);
		double deltaFi = Math.toRadians(lat2 - lat1);
		double deltaLambda = Math.toRadians(lon2 - lon1);
		
		double a = Math.pow(Math.sin(deltaFi/2),2) + Math.pow(Math.sin(deltaLambda/2),2) * Math.cos(fi1) * Math.cos(fi2);
		double c =  2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
}