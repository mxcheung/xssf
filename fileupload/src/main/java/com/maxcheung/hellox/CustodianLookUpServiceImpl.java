package com.maxcheung.hellox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.KeyValue;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.maxcheung.xssf.XSSFService;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupAvailableLookupsResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupFileVersionResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupImportResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupsGetResponse;

import ch.qos.logback.core.util.FileUtil;



@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustodianLookUpServiceImpl implements CustodianLookUpService {

    private static final String MULTIPART_FORMDATA = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    @Autowired
    private XSSFService xSSFService;
    
    @Autowired
    private CustodianLookUpConverter custodianLookUpConverter;

 //   @Autowired
//    private CustodianLookUpDefaultService custodianLookUpDefaultService;

    @Autowired
    public CustodianLookUpServiceImpl() {
    }

    

    @Override
    public CustodianLookupsGetResponse fetchLookUp(String custodian) {
        CustodianLookupsGetResponse response = new CustodianLookupsGetResponse();
//        response.setDataMap(custodianLookUpConverter.convertMap(custodianLookUpDefaultService.findByCustodianWithDefault(custodian)));
        return response;
    }
  
    

    @Override
    public CustodianLookupsGetResponse fetchLookUpByType(String custodian, String lookUpType) {
        CustodianLookupsGetResponse response = new CustodianLookupsGetResponse();
  //      response.setDataMap(custodianLookUpConverter.convertMap(custodianLookUpDefaultService.findByCustodianAndGroupKeyWithDefault(custodian, lookUpType)));
        return response;
    }

    @Override
    public CustodianLookupAvailableLookupsResponse fetchAvailableLookUp(String custodian) {
        CustodianLookupAvailableLookupsResponse response = new CustodianLookupAvailableLookupsResponse();
    //    response.setData(custodianLookUpConverter.getAvailableLookUps(custodianLookUpDefaultService.findByCustodianWithDefault(custodian)));
        return response;
    }

    @Override
    public CustodianLookupImportResponse importLookupData( MultipartFile lookupfile, String custodian) throws IOException, OpenXML4JException {
        @SuppressWarnings("rawtypes")
        Map<String, List<KeyValue>> rawData = xSSFService.extractData(custodian, lookupfile.getInputStream());
    //    persistImportFile(extractFileEntity(lookupfile, custodian), custodianLookUpConverter.convertToEntity(custodian, rawData));
        return createImportResponse(rawData);
    }


    

    @Override
    public CustodianLookupFileVersionResponse getCustodianLookupVersion(String custodian) {
        CustodianLookupFileVersionResponse response = new CustodianLookupFileVersionResponse();
        response.setFileName("");
//        List<CustodianLookUpFileEntity> files = custodianLookUpDefaultService.findByCustodianFileWithDefault(custodian);
//        if (files.size() > 0) 
//        {
//            response.setFileName(files.get(0).getFileName());
//        }
        return response;
    }




    private long getContentLength(  byte[] data) {
        return Base64.encodeBase64(data).length + 1;
    }
    
    private CustodianLookupImportResponse createImportResponse( @SuppressWarnings("rawtypes") Map<String, List<KeyValue>> rawData) {
        CustodianLookupImportResponse response =  new CustodianLookupImportResponse();
        response.setDataMap(custodianLookUpConverter.convertResult(rawData));
        return response;
    }



}
