package vehicles;
import weather.WeatherTower;

public class jetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
	public jetPlane(String name, Coordinates coordinates)
	{
		super(name, coordinates);
    }

    public	void	updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + "): Sun time");
				break;
			case "RAIN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
				Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + "): Rain time");
				break;
			case "FOG":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
				Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + "): Fog time");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + "): Snow time!");
				break;
			default:
			Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + "): cannot contact weather tower");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			Coordinates.writer.println("JetPlane#" + this.name + "(" + this.id + ") landing.");
			this.weatherTower.unregister(this);
			Coordinates.writer.println("Tower  says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

    public	void	registerTower(WeatherTower weatherTower) {
        Coordinates.writer.println("Tower says: jetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}
}