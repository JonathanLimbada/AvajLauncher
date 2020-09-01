package vehicles;

import weather.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
	public Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
    }

    public	void	updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
				this.coordinates.setHeight(this.coordinates.getHeight() + 4);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setLongitude(100);
				Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + "): the Sun shines!");
				break;
			case "RAIN":
				this.coordinates.setHeight(this.coordinates.getHeight() - 5);
				Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + "): it do be raining");
				break;
			case "FOG":
				this.coordinates.setHeight(this.coordinates.getHeight() - 2);
				Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + "): the fog is here");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 15);
				Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + "): there is snow here");
				break;
			default:
				Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + "): cannot contact weather tower");
				break;
		}
		if (this.coordinates.getHeight() <= 0){
			Coordinates.writer.println("Baloon#" + this.name + "(" + this.id + ") landing.");
			this.weatherTower.unregister(this);
			Coordinates.writer.println("Tower  says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

    public	void	registerTower(WeatherTower weatherTower) {
		Coordinates.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}
}