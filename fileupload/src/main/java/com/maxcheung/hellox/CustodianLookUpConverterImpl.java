package com.maxcheung.hellox;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.KeyValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookUpDTO;


@Component
public class CustodianLookUpConverterImpl  implements CustodianLookUpConverter {

	@Override
	public Map<String, List<CustodianLookUpDTO>> convertResult(	@SuppressWarnings("rawtypes") Map<String, List<KeyValue>> codeMap) {
		Map<String, List<CustodianLookUpDTO>> dataMap = new HashMap<String, List<CustodianLookUpDTO>>();
		for (Map.Entry<String,  List<KeyValue>> entry : codeMap.entrySet()) {
			dataMap.put(entry.getKey(), convertList(entry.getValue()));
		}
		return dataMap;
	}




	
	private List<CustodianLookUpDTO> convertList(List<KeyValue> keyValues) {
		List<CustodianLookUpDTO> list = new ArrayList<CustodianLookUpDTO>();
		for (KeyValue kv : keyValues)
		{
			list.add(convert(kv));
		}
		return list;
	}

	private CustodianLookUpDTO convert(KeyValue keyValue) {
		CustodianLookUpDTO custodianLookUpDTO;
		custodianLookUpDTO = new CustodianLookUpDTO( convertToString(keyValue.getKey()),   convertToString(keyValue.getValue()));
		return custodianLookUpDTO;
	}

	private String convertToString(Object obj) {
		return StringUtils.defaultString((String) obj);
	}

	
	
}
