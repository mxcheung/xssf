package com.maxcheungcustodianservice.custodianlookup.client.model;

public class CustodianLookUpDTO {

    private static final long serialVersionUID = 1L;

    private String label;
    private String code;
    
	public CustodianLookUpDTO() {
	}
	
	public CustodianLookUpDTO(String key, String value) {
		this.code = key;
		this.label = value;
	}
    
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

    
    
}
