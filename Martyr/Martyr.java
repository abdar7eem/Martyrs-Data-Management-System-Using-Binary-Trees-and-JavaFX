package Martyr;

import java.util.Date;

import Date.DateNode;
import District.Dnode;
import Location.Lnode;

public class Martyr {
	
	private String mname;
	private DateNode date;
	private int age;
	private Lnode location;
	private Dnode district;
	private String gender;
	
	
	public Martyr(String mName, DateNode date, int age, Lnode location, Dnode district, String gender) {
		super();
		this.mname = mName;
		this.date = date;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}


	public String getMname() {
		return mname;
	}

	public void setmName(String mName) {
		this.mname = mName;
	}

	public DateNode getDate() {
		return date;
	}

	public void setDate(DateNode date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Lnode getLocation() {
		return location;
	}

	public void setLocation(Lnode location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Dnode getDistrict() {
		return district;
	}

	public void setDistrict(Dnode district) {
		this.district = district;
	}

	@Override
    public String toString() {
        return mname + ","+   date +","+age+"," + location + ","+ district
                +","+gender;
    }

	
	
	
	
}
