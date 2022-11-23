package model;

public class BoardDTO {
	private int driverId;
	private int boardId;
	private String arrival;
	private String departure;
	private String arrivalTime;
	private String departureTime;
	private String carShareDate;
	private int headCount;

	public BoardDTO(int driverId, String arrival, String departure, String arrivalTime, String departureTime,
			String carShareDate, int headCount) {
		super();
		this.driverId = driverId;
		this.arrival = arrival;
		this.departure = departure;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.carShareDate = carShareDate;
		this.headCount = headCount;
	}

	
	public BoardDTO(int driverId, int boardId, String arrival, String departure, String arrivalTime,
			String departureTime, String carShareDate, int headCount) {
		super();
		this.driverId = driverId;
		this.boardId = boardId;
		this.arrival = arrival;
		this.departure = departure;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.carShareDate = carShareDate;
		this.headCount = headCount;
	}
	
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getCarShareDate() {
		return carShareDate;
	}
	public void setCarShareDate(String carShareDate) {
		this.carShareDate = carShareDate;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}

	@Override
	public String toString() {
		return "BoardDTO [driverId=" + driverId + ", boardId=" + boardId + ", arrival=" + arrival + ", departure="
				+ departure + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", carShareDate="
				+ carShareDate + ", headCount=" + headCount + "]";
	}
}
