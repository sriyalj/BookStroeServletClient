package Entity;

import java.io.Serializable;

public class Author implements Serializable{
	
	private String fstName;
	private String middleName;
	private String lastName;
	private String originCountry;
	
	public Author (String fstName, String middleName, String lastName, String originCountry){
		this.fstName = fstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.originCountry = originCountry;
	}

	public String getFstName() {
		return fstName;
	}

	public void setFstName(String fstName) {
		this.fstName = fstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

}
