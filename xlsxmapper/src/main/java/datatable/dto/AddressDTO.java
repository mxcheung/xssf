package datatable.dto;

public class AddressDTO extends DataDTO {

    private String streetNo;
    private String streetName;
    private String streetType;
    private String suburb;
    private String postCode;
    
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

    
    
}

