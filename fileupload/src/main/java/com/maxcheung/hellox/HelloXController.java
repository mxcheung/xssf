package com.maxcheung.hellox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxcheung.controller.RestUploadController;
import com.maxcheung.custodianservice.custodianlookup.service.FileImportFailedException;
import com.maxcheungcustodianservice.custodianlookup.client.model.CustodianLookupImportResponse;

@RestController
public class HelloXController {

    private final Logger logger = LoggerFactory.getLogger(HelloXController.class);


    private ObjectMapper mapper = new ObjectMapper();
    
	@Autowired
	CustodianLookUpService custodianLookUpService;
	
	
    @RequestMapping("/hellox/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    
    @RequestMapping("/hellox/import")
	public ResponseEntity<CustodianLookupImportResponse> importLookupData(
			@RequestPart("lookupfile") final MultipartFile lookupfile,
			final HttpServletRequest request, final HttpServletResponse response) throws IOException {

		try {
			return new ResponseEntity<CustodianLookupImportResponse>(custodianLookUpService.importLookupData(lookupfile, null),new HttpHeaders(), HttpStatus.OK);
		} catch (IOException | OpenXML4JException e) {
			throw new FileImportFailedException("Error importing custodian lookup:", e);
		}
	}

    
    
    // Multiple file upload
    @PostMapping("/api/upload/multi2")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) throws OpenXML4JException {

        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

        	uploadedFileName =   saveUploadedFiles(Arrays.asList(uploadfiles));


        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }
    
    //save file
    private String  saveUploadedFiles(List<MultipartFile> files) throws IOException, OpenXML4JException {

    	String response =  "";
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

           CustodianLookupImportResponse custodianLookupImportResponse = custodianLookUpService.importLookupData(file, null);
           
           
            String json = this.mapper.writeValueAsString(custodianLookupImportResponse);
           response = response + json;

        }
        return  response;

    }

}
