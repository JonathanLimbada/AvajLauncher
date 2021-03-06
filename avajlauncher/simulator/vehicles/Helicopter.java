package vehicles;
import weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
	public Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);
    }

    public	void	updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + "): the SUN is whack af, I'm hot");
				break;
			case "RAIN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
				Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + "): the RAIN is whack af, I'm wet");
				break;
			case "FOG":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
				Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + "): the FOG is whack af, I can't see");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + "): the SNOW is whack af, I'm cold");
			break;
			default:
			Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + "): cannot contact weather tower");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			Coordinates.writer.println("Helicopter#" + this.name + "(" + this.id + ") landing.");
			this.weatherTower.unregister(this);
			Coordinates.writer.println("Tower  says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}
    
    public	void	registerTower(WeatherTower weatherTower) {
        Coordinates.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}

}