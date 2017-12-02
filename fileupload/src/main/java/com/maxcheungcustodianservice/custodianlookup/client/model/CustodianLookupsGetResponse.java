package com.maxcheungcustodianservice.custodianlookup.client.model;

import java.util.List;
import java.util.Map;

public class CustodianLookupsGetResponse  {

    private static final long serialVersionUID = 1L;
    
    private Map<String, List<CustodianLookUpDTO>> dataMap;

	public Map<String, List<CustodianLookUpDTO>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, List<CustodianLookUpDTO>> dataMap) {
		this.dataMap = dataMap;
	}


	
}
