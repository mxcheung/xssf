package datatable.xssf.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;

import datatable.dto.DataDTO;
import datatable.xssf.mapper.AddressMapper;
import datatable.xssf.mapper.CountryMapper;
import datatable.xssf.mapper.IRRMapper;
import datatable.xssf.mapper.RTRMapper;
import datatable.xssf.mapper.SuffixMapper;
import datatable.xssf.mapper.USMMapper;
import datatable.xssf.mapper.XSSFRowMapper;

@Component
public class XSSFServiceImpl implements XSSFService {

	private static final int HEADER_ROWS = 1;
	private XSSFRowMapper countryMapper = new CountryMapper();
	private XSSFRowMapper suffixMapper = new SuffixMapper();
	private XSSFRowMapper addressMapper = new AddressMapper();
	private XSSFRowMapper irrMapper = new IRRMapper();
	private XSSFRowMapper usmMapper = new USMMapper();
	private XSSFRowMapper rtrMapper = new RTRMapper();
	private Map<String, XSSFRowMapper> rowMappers =	ImmutableMap.<String, XSSFRowMapper>builder()
		    .put("Country", countryMapper) 
		    .put("Suffix", suffixMapper) 
		    .put("Address", addressMapper) 
		    .put("IRR", irrMapper) 
		    .put("RTR", rtrMapper) 
		    .put("USM", usmMapper) 
		    .build();
			

	public Map<String, List<DataDTO>> extractData(InputStream ExcelFileToRead)
			throws IOException, OpenXML4JException {
	   Map<String, List<DataDTO>> wbData = new HashMap<String, List<DataDTO>>();

//		InputStream ExcelFileToRead = new FileInputStream(fileName);
		final OPCPackage pkg = OPCPackage.open(ExcelFileToRead);
		final Workbook workbook = new XSSFWorkbook(pkg);
		for (Sheet sheet : workbook) {
			String sheetname = sheet.getSheetName();
			wbData.put(sheetname, mapSheet(sheet));
		}
		workbook.close();
		return wbData;	}

	
	private List<DataDTO> mapSheet(Sheet sheet) throws JsonProcessingException {
		String sheetname = sheet.getSheetName();
		XSSFRowMapper mapper =  rowMappers.get(sheetname);
		List<DataDTO> ret  = new ArrayList<DataDTO>();
		Iterable<Row> rows = Iterables.skip(sheet, HEADER_ROWS);
		for (Row row : rows) {
			ret.add(mapper.mapRow(row));
		}
		return ret;
	}

}