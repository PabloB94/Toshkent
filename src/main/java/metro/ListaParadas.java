package metro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaParadas {

	private HashMap<String,Estacion> LUzbekistan, LChilonzor, LYunusabad;

	public ListaParadas() {

		LUzbekistan = new HashMap<String, Estacion>();
		LChilonzor = new HashMap<String,Estacion>();
		LYunusabad = new HashMap<String,Estacion>();
		
		
		/*LIENA UZBEKISTAN*/
		LUzbekistan.put("Beruni",new Estacion(1,"Beruni","Tinchlik",1,null,0,0,"41 20 45.8 N","69 12 23.6 E"));
		LUzbekistan.put("Tinchlik", new Estacion(1,"Tinchlik","Beruni",1,"Chorsu",1,0,"41 19 56.9 N","69 13 08.7 E"));
		LUzbekistan.put("Chorsu",new Estacion(1,"Chorsu","Tinchlik",1,"Gafur Gulom",1,0,"41 19 31.8 N","69 14 09.0 E"));
		LUzbekistan.put("Gafur Gulom",new Estacion(1,"Gafur Gulom","Chorsu",1,"Aliser Navoi/Pakhtakor",1,0,"41 19 40.0 N","69 14 51.1 E"));
		LUzbekistan.put("Aliser Navoi/Pakhtakor", new Estacion(1,"Aliser Navoi/Pakhtakor","Gafur Gulom",1,"Uzbekistan",2,2,"41 19 12.7 N","69 15 14.5 E"));
		LUzbekistan.put("Uzbekistan",new Estacion(1,"Uzbekistan","Aliser Navoi/Pakhtakor",1,"Kosmonavtlar",1,0,"41 18 41.0 N","69 15 11.0 E"));
		LUzbekistan.put("Kosmonavtlar",new Estacion(1,"Kosmonavtlar","Uzbekistan",1,"Oybek/Ming Urik",1,0,"41 18 19.6 N","69 15 49.9 E"));
		LUzbekistan.put("Oybek/Ming Urik",new Estacion(1,"Oybek/Ming Urik","Kosmonavtlar",1,"Toshkent",1,3,"41 17 55.3 N","69 16 22.9 E"));
		LUzbekistan.put("Toshkent",new Estacion(1,"Toshkent","Oybek/Ming Urik",1,"Mashinasozlar",1,0,"41 17 29.8 N","69 17 13.0 E"));
		LUzbekistan.put("Mashinasozlar",new Estacion(1,"Mashinasozlar","Toshkent",1,"Dostlik",1,0,"41 17 58.9 N","69 18 15.8 E"));
		LUzbekistan.put("Dostlik",new Estacion(1,"Dostlik","Mashinasozlar",1,null,1,0,"41 17 40.0 N","69 19 17.5 E"));

		/*LINEA CHILONZOR*/
		
		LChilonzor.put("Olmazor", new Estacion(2,"Olmazor","Chilonzor",1,null,0,0,"41 15 27.3 N","69 11 45.3 E"));
		LChilonzor.put("Chilonzor",new Estacion(2,"Chilonzor","Olmazor",1,"Mirzo Ulugbek",1,0,"41 16 26.9 N","69 12 13.7 E"));
		LChilonzor.put("Mirzo Ulugbek",new Estacion(2,"Mirzo Ulugbek","Chilonzor",1,"Novza",1,0,"41 16 54.0 N","69 12 47.0 E"));
		LChilonzor.put("Novza",new Estacion(2,"Novza","Mirzo Ulugbek",1,"Milliy Bog",1,0,"41 17 29.4 N","69 13 23.5 E"));
		LChilonzor.put("Milliy Bog",new Estacion(2,"Milliy Bog","Novza",1,"Bunyodkor",1,0,"41 18 16.5 N","69 14 05.9 E"));
		LChilonzor.put("Bunyodkor",new Estacion(2,"Bunyodkor","Milliy Bog",1,"Aliser Navoi/Pakhtakor",1,0,"41 18 41.1 N","69 14 31.0 E"));
		LChilonzor.put("Aliser Navoi/Pakhtakor", new Estacion(2,"Aliser Navoi/Pakhtakor","Bunyodkor",1,"Mustakillik Maydoni",1,1,"41 19 12.7 N","69 15 14.5 E"));
		LChilonzor.put("Mustakillik Maydoni", new Estacion(2,"Mustakillik Maydoni","Aliser Navoi/Pakhtakor",1,"Amir Temur Hiyoboni/Yunus Rajabiy",1,0,"41 18 43.1 N","69 14 32.5 E"));
		LChilonzor.put("Amir Temur Hiyoboni/Yunus Rajabiy", new Estacion(2,"Amir Temur Hiyoboni/Yunus Rajabiy","Mustakillik Maydoni",1,"Khamid Alimjan",1,3,"41 18 48.4 N","69 17 5.5 E"));
		LChilonzor.put("Khamid Alimjan",new Estacion(2,"Khamid Alimjan","Amir Temur Hiyoboni/Yunus Rajabiy",1,"Pushkin",1,0,"41 19 3.1 N","69 17 42.9 E"));
		LChilonzor.put("Pushkin",new Estacion(2,"Pushkin","Khamid Alimjan",1,"Buyuk Ipak Yuli",1,0,"41 19 26.6 N","69 18 45.7 E"));
		LChilonzor.put("Buyuk Ipak Yuli",new Estacion(2,"Buyuk Ipak Yuli","Pushkin",1,null,1,0,"41 19 35 N","69 19 40.8 E"));
		
		/*LINEA YUNUSABAD*/
		
		LYunusabad.put("Shakriston", new Estacion(3,"Shakriston","Bodonzor",1,null,0,0,"41 21 11 N","69 17 17.4 E"));
		LYunusabad.put("Bodonzor",new Estacion(3,"Bodonzor","Shakriston",1,"Minor",1,0,"41 20 16.3 N","69 17 7 E"));
		LYunusabad.put("Minor",new Estacion(3,"Minor","Bodonzor",1,"Abdulla Kodiriy",1,0,"41 19 39.2 N","69 17 01.6 E"));
		LYunusabad.put("Abdulla Kodiriy",new Estacion(3,"Abdulla Kodiriy","Minor",1,"Amir Temur Hiyoboni/Yunus Rajabiy",1,0,"41 19 09.8 N","69 16 56.9 E"));
		LYunusabad.put("Amir Temur Hiyoboni/Yunus Rajabiy",new Estacion(3,"Amir Temur Hiyoboni/Yunus Rajabiy","Abdulla Kodiriy",1,"Oybek/Ming Urik",1,2,"41 18 48.4 N","69 17 5.5 E"));
		LYunusabad.put("Oybek/Ming Urik",new Estacion(3,"Oybek/Ming Urik","Amir Temur Hiyoboni/Yunus Rajabiy",1,null,1,1,"41 17 55.3 N","69 16 22.9 E"));


		
		
		
	}

	public List<String> estacionesOrdenadas(){
		List<String> toReturn = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		HashMap<String,Estacion>[] hashMap = new HashMap[3];
		hashMap = getLineas();
		for(int i=0; i<3 ; i++){
			for(Map.Entry<String,Estacion> entry : hashMap[i].entrySet()){
				if(!toReturn.contains(entry.getValue().getNombre()))
					toReturn.add(entry.getValue().getNombre());
			}
		}
		Collections.sort(toReturn);

		return toReturn;
	}

	public Estacion getEstacion(String origen){
		@SuppressWarnings("unchecked")
		HashMap<String,Estacion>[] hashMap = new HashMap[3];
		hashMap = this.getLineas();
		for(int i=0; i<3 ; i++){
			if (hashMap[i].containsKey(origen))
				return hashMap[i].get(origen);
		}
		return null;

	}

	public HashMap<String,Estacion>[] getLineas(){
		@SuppressWarnings("unchecked")
		HashMap<String,Estacion>[] hashMap = new HashMap[3];
		hashMap[0] = this.LUzbekistan;
		hashMap[1] = this.LChilonzor;
		hashMap[2] = this.LYunusabad;
		//hashMap[3] = this.CambioLinea;

		return hashMap;
	}


}
