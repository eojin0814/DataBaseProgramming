package model;

public class StationDTO {
	private int stationId;
	private String stationName;
	private double latitudeX;
	private double longtitudeY;
	
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public double getLatitudeX() {
		return latitudeX;
	}
	public void setLatitudeX(double latitudeX) {
		this.latitudeX = latitudeX;
	}
	public double getLongtitudeY() {
		return longtitudeY;
	}
	public void setLongtitudeY(double longtitudeY) {
		this.longtitudeY = longtitudeY;
	}
}
