package com.maxcheung.hellox;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.web.multipart.MultipartFile;

import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupAvailableLookupsResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupFileVersionResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupImportResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupsGetResponse;


public interface CustodianLookUpService {

	CustodianLookupsGetResponse fetchLookUp(String custodian);

	CustodianLookupsGetResponse fetchLookUpByType(String custodian, String lookUpType);

	CustodianLookupAvailableLookupsResponse fetchAvailableLookUp(String custodian);

	CustodianLookupImportResponse importLookupData(	MultipartFile lookupfile, String custodian) throws IOException, OpenXML4JException;


	CustodianLookupFileVersionResponse getCustodianLookupVersion(String custodian);

}
