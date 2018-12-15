package metro;

public class Coordenada {
	
	private double gradosLat, minutosLat, segundosLat, gradosLon, minutosLon, segundosLon;
	private String letraLat, letraLon;
	
	public Coordenada(String latitud, String longitud)
	{
		String[] partesLatitud = latitud.split(" ");
		this.gradosLat = Double.parseDouble(partesLatitud[0]);
		this.minutosLat = Double.parseDouble(partesLatitud[1]);
		this.segundosLat = Double.parseDouble(partesLatitud[2]);
		this.letraLat = partesLatitud[3];
		
		String[] partesLongitud = longitud.split(" ");
		this.gradosLon = Double.parseDouble(partesLongitud[0]);
		this.minutosLon = Double.parseDouble(partesLongitud[1]);
		this.segundosLon = Double.parseDouble(partesLongitud[2]);
		this.letraLon = partesLongitud[3];
	}
	
	public double deegreToDecimalLat()
	{
		return this.gradosLat + this.minutosLat/60 + this.segundosLat/3600;
	}
	
	public double deegreToDecimalLon()
	{
		return this.gradosLon + this.minutosLon/60 + this.segundosLon/3600;
	}
	
	public String getLetraLat() {
		return this.letraLat;
	}

	public String getLetraLon() {
		return this.letraLon;
	}
}
