/**
 * Sample resource of the 1500 paintings from Dali, reference: http://www.slobidka.com/dali
 */
public enum DaliPainting {
	Memory(1931, "La persistencia de la memoria"),
	Elephantes(1937, "Los cisnes que se reflejan como elefantes"),
	Hervidas(1936, "Construccion blanda con judias hervidas"),
	Pichot(1930, "Retrato del violonchelista Ricardo Pichot"),
	Padre(1930,"Retrato de mi padre (1920-1921)"),
	Autorretrato(1930,"Autorretrato"),
	Botijo(1930,"Botijo"),
	CostaBrava(1930, "Ba�istas en la Costa Brava (Ba�istas en Llan�)"),
	Cubista(1930, "Autorretrato cubista"),
	Nino(1936, "El ni�o enfermo Autorretrato en Cadaqu�s"),
	Contrapuesta(1930,"Retrato de mi hermana y figura picassiana contrapuesta"),
	Muerta(1931,"Naturaleza muerta"),
	RetratoLuis(1932,"Retrato de Luis Bu�uel"),
	Ventana(1933, "Muchacha a la ventana"),
	PadreRetrato(1934, "Retrato de mi padre (1925)"),
	Venus(1934, "Venus y amorcillos"),
	Tendida(1935, "Mujer tendida"),
	Segats(1935, "Penya-Segats (Mujer delante de los escollos)"),
	Cestillo(1935, "Cestillo de pan"),
	Putrefacto(1936, "El asno putrefacto"),
	RetratoPaul(1937, "Retrato de Paul �luard"),
	Monumento(1937, "Monumento imperial a la mujer ni�a"),
	Enigma(1938, "El enigma del deseo"),
	Juego(1939, "El juego l�gubre (o Juego funesto)"),
	Masturbador(1940, "El gran masturbador"),
	Hombre(1939, "El hombre invisible"),
	Recuerdos(1938, "La persistencia de los recuerdos"),
	Surrealista(1936, "Objeto surrealista indicador de la memoria instant�nea"),
	Huevos(1937, "Huevos al plato sin plato"),
	Verdadero(1937, "El verdadero cuadro de la Isla ele los muertos de Arnold B�cklin a la hora del �ngelus"),
	Anamorfosis(1940, "Gala y el �ngelus de Millet precediendo la llegada inminente de las anamorfosis c�nicas"),
	EnigmaTell(1942, "El enigma de Guilermo Tell"),
	Manifiesto(1950, "Manifiesto surrealista"),
	Rostro(1956, "Rostro de Mae West como apartamento surrealista"),
	Caballero(1956, "El caballero de la muerte"),
	GalaAngelus(1935, "El �ngelus de Gala"),
	Paranoico(1936, "Rostro paranoico (Un Picasso escondido)"),
	Mujer(1937, "Mujer con cabeza de rosas"),
	Cabezas(1938, "Pareja con las cabezas llenas de nubes"),
	Blanda(1939, "Construcci�n blanda con jud�as cocidas Premonici�n de la guerra civil"),
	Llamas(1939,"Jirafa en llamas"),
	Sueno(1939,"El sue�o"),
	Africa(1940, "Impresiones de �frica"),
	Hitler(1945, "El enigma de Hitler"),
	Guerra(1945, "El rostro de la guerra"),
	Despertar(1950, "Sue�o causado por el vuelo de una avispa alrededor de una granada momentos antes de despertar"),
	Retrato(1950,"Retrato de Isabel Styler-Tas (Melancol�a)"),
	SanAntonio(1950,"La tentaci�n de san Antonio"),
	Leda(1950,"Leda at�mica"),
	Madonna(1950,"La Madonna de Port Lligat"),
	Juan(1950,"Cristo de san Juan de la Cruz"),
	Regulares(1950,"Dal� desnudo en contemplaci�n delante de cinco cuerpos regulares metamorfoseados en corp�sculos en los cuales aparece repentinamente la Leda de Leonardo cromosomizada en el rostro de Gala"),
	Torero(1950,"El torero alucin�geno"),
	Golondrina(1950,"La cola de golondrina (Serie de cat�strofes)");
	int year;
	String description;
	
	DaliPainting(int year, String description) {
		this.year = year;
		this.description = description;
	}
	
	public int getYear() {
		return year;
	}
	public String getDescription() {
		return description;
	}
}
