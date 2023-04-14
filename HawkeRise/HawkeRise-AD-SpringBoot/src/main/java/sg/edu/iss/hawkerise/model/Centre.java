package sg.edu.iss.hawkerise.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Centre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String address;
	private int numOfStalls = 0;

	private double latitude;
	private double longitude;
	private double distance = 0.0;

	private String imgUrl = "";

	@OneToMany(mappedBy = "centre", cascade = { CascadeType.REMOVE })
	@JsonIgnore
	private Set<Hawker> hawkers;

	public Centre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Centre(int id, String name, String address, double latitude, double longitude, double distance,
			String imgUrl,int numOfStalls) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		this.imgUrl = imgUrl;
		this.numOfStalls = numOfStalls;
	}

	public Centre(int id, String name, String address, double latitude, double longitude, String imgUrl,
			int numOfStalls) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.imgUrl = imgUrl;
		this.numOfStalls = numOfStalls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumOfStalls() {
		return numOfStalls;
	}

	public void setNumOfStalls(int numOfStalls) {
		this.numOfStalls = numOfStalls;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Hawker> getHawkers() {
		return hawkers;
	}

	public void setHawkers(Set<Hawker> hawkers) {
		this.hawkers = hawkers;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
