package com.example.ec.domain;

public enum Region {
	
	Central_Coast("Central Coast"), 
	
	Southren_California("Southren California"), 
	
	Northren_California("Northren California"), 
	
	Varies("Varies");
	
	private String label;
	
	private Region(String label) {
		this.label = label;
	}

	public static Region findByLabel(String byLabel) {
		for(Region region : Region.values()) {
			if(region.label.equalsIgnoreCase(byLabel))
				return region;
		}
		return null;
	}
}
