package org.apininja;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holiday{

	@JsonProperty("date")
	private String date;

	@JsonProperty("country")
	private String country;

	@JsonProperty("iso")
	private String iso;

	@JsonProperty("year")
	private int year;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("day")
	private String day;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setIso(String iso){
		this.iso = iso;
	}

	public String getIso(){
		return iso;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Holiday{" + 
			"date = '" + date + '\'' + 
			",country = '" + country + '\'' + 
			",iso = '" + iso + '\'' + 
			",year = '" + year + '\'' + 
			",name = '" + name + '\'' + 
			",type = '" + type + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}