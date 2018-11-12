package org.cola.bean;

import java.io.Serializable;

public class BoxBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String box_id;
	
	private double longitude;
	
	private double latitude;

	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	

}
