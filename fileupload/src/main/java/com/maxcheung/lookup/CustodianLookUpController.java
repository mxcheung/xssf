package com.maxcheung.lookup;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupAvailableLookupsResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupFileVersionResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupImportResponse;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupsGetResponse;

@RestController
@RequestMapping(value = CustodianLookUpController.CUSTODIAN_LOOKUP_BASE_CONTEXT)
public interface CustodianLookUpController {

	// String CUSTODIAN_LOOKUP_BASE_CONTEXT =
	// "/v1/custodian/{custodianIdentifier}/lookups";
	String CUSTODIAN_LOOKUP_BASE_CONTEXT = "/v1/custodian/lookups";

	String GET_CUSTODIAN_LOOKUP_VERSION_MAPPING = "/version";
	String GET_CUSTODIAN_LOOKUP_MAPPING = "";
	String GET_CUSTODIAN_LOOKUP_TYPE_MAPPING = "/{custodianLookupType}";
	String GET_CUSTODIAN_AVALIABLE_LOOKUPS_MAPPING = "/availablelookups";
	String CUSTODIAN_IMPORT_LOOKUP_MAPPING = "/importlookup";
	String CUSTODIAN_EXPORT_LOOKUP_MAPPING = "/exportlookup";

	String CUSTODIAN_IDENTIFIER_PARAMETER = "custodianIdentifier";
	String CUSTODIAN_LOOKUP_TYPE_PARAMETER = "custodianLookupType";

	@RequestMapping(value = GET_CUSTODIAN_LOOKUP_MAPPING, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	ResponseEntity<CustodianLookupsGetResponse> getCustodianLookup(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = GET_CUSTODIAN_LOOKUP_TYPE_MAPPING, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	ResponseEntity<CustodianLookupsGetResponse> getCustodianLookupByType(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian,
			@PathVariable(CUSTODIAN_LOOKUP_TYPE_PARAMETER) String lookUpType, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = GET_CUSTODIAN_AVALIABLE_LOOKUPS_MAPPING, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	ResponseEntity<CustodianLookupAvailableLookupsResponse> getCustodianAvailableLookups(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = CUSTODIAN_IMPORT_LOOKUP_MAPPING, method = POST, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	@ResponseBody
	ResponseEntity<CustodianLookupImportResponse> importLookupData(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@RequestPart("lookupfile") MultipartFile lookupfile,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = CUSTODIAN_EXPORT_LOOKUP_MAPPING, method = GET)
	@ResponseStatus(OK)
	@ResponseBody
	ResponseEntity<byte[]> exportLookupData(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = GET_CUSTODIAN_LOOKUP_VERSION_MAPPING, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	ResponseEntity<CustodianLookupFileVersionResponse> getCustodianLookupVersion(
			@RequestHeader(value = "X-Request-Correlation-ID", required = true) String xRequestCorrelationID,
			@RequestHeader(value = "X-Request-ID", required = true) String xRequestID,
			@RequestHeader(value = "X-Request-DateTime", required = true) String xRequestDateTime,
			@RequestHeader(value = "X-Request-TimeZone", required = true) String xRequestTimeZone,
			@RequestHeader(value = "X-Data-Custodian", required = true) String xDataCustodian,
			@RequestHeader(value = "X-Authorization-User", required = true) String xAuthorizationUser,
			@PathVariable(CUSTODIAN_IDENTIFIER_PARAMETER) String custodian, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping("/hello2/")
	String index();

}
