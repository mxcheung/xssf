package com.maxcheung.hellox;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.KeyValue;

import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookUpDTO;


public interface CustodianLookUpConverter {

	/*******xxsf to dto ************/  
	Map<String, List<CustodianLookUpDTO>> convertResult(Map<String, List<KeyValue>> codes);
	

}
