package com.maxcheung.lookup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.maxcheung.custodianservice.custodianlookup.service.FileImportFailedException;
import com.maxcheung.hellox.CustodianLookUpService;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupAvailableLookupsResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupFileVersionResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupImportResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupsGetResponse;


@RestController
public class CustodianLookUpControllerImpl implements CustodianLookUpController{

	@Autowired
	CustodianLookUpService custodianLookUpService;

	@Override
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
	
	
	@Override
	public ResponseEntity<CustodianLookupsGetResponse>  getCustodianLookup(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			final HttpServletRequest request, final HttpServletResponse response) {
		return new ResponseEntity<CustodianLookupsGetResponse>(custodianLookUpService.fetchLookUp(custodian),new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustodianLookupsGetResponse> getCustodianLookupByType(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			@PathVariable("custodianLookupType") final String lookUpType,
			final HttpServletRequest request, final HttpServletResponse response) {
		return new ResponseEntity<CustodianLookupsGetResponse>(custodianLookUpService.fetchLookUpByType(custodian, lookUpType),new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustodianLookupAvailableLookupsResponse> getCustodianAvailableLookups(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			final HttpServletRequest request, final HttpServletResponse response) {
		return new ResponseEntity<CustodianLookupAvailableLookupsResponse>(custodianLookUpService.fetchAvailableLookUp(custodian),new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustodianLookupImportResponse> importLookupData(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@RequestPart("lookupfile") final MultipartFile lookupfile,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			final HttpServletRequest request, final HttpServletResponse response) {

		try {
			return new ResponseEntity<CustodianLookupImportResponse>(custodianLookUpService.importLookupData(lookupfile, custodian),new HttpHeaders(), HttpStatus.OK);
		} catch (IOException | OpenXML4JException e) {
			throw new FileImportFailedException("Error importing custodian lookup:", e);
		}
	}

	@Override
    @ResponseBody
	public ResponseEntity<byte[]> exportLookupData(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			final HttpServletRequest request, final HttpServletResponse response) {

//		CustodianLookUpFileEntity  custodianLookUpFileEntity = custodianLookUpService.exportFile(custodian);
//		if ((custodianLookUpFileEntity != null) && (custodianLookUpFileEntity.getFileContent() != null)) {
//			return new ResponseEntity<byte[]>(custodianLookUpFileEntity.getFileContent() ,	custodianLookUpService.generateExportResponseHeader(custodianLookUpFileEntity), HttpStatus.OK);
//		}
		
		final HttpHeaders noFileFoundHeader = new HttpHeaders();
		noFileFoundHeader.setContentLength(0L);
		return new ResponseEntity<byte[]>(new byte[] {}, noFileFoundHeader,	HttpStatus.NOT_FOUND);
	}

	private String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}


	@Override
	public ResponseEntity<CustodianLookupFileVersionResponse> getCustodianLookupVersion(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) final String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) final String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) final String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) final String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) final String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) final String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			final HttpServletRequest request, final HttpServletResponse response) {
		return new ResponseEntity<CustodianLookupFileVersionResponse>(custodianLookUpService.getCustodianLookupVersion(custodian),new HttpHeaders(), HttpStatus.OK);
	}	
}
