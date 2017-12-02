


package com.maxcheung.xssf;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class XSSFServiceImpl implements XSSFService {

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<KeyValue>>  extractData(String clientCd, InputStream excelFileToRead) throws IOException, OpenXML4JException {
		Map<String, List<KeyValue>> codeMap  = new HashMap<String, List<KeyValue>>();
		final OPCPackage pkg = OPCPackage.open(excelFileToRead);
		final Workbook workbook = new XSSFWorkbook(pkg);
		for (Sheet sheet : workbook) {
			codeMap.put(sheet.getSheetName(), extractCodes(sheet));
		}
		workbook.close();
		return codeMap;
	}

	@SuppressWarnings("rawtypes")
	private List<KeyValue> extractCodes(Sheet sheet) {
		List<KeyValue> codes = new ArrayList<KeyValue>();
		for (Row row : sheet) {
			String k = getCellValueAsCellType(row.getCell(0),Cell.CELL_TYPE_STRING);
			String v = getCellValueAsCellType(row.getCell(1), Cell.CELL_TYPE_STRING);
			KeyValue kv = createKeyValue(k,v);
			if (isValid(kv)){
				codes.add(kv);
			}
		}
		return codes;
	}

	private String getCellValueAsCellType(Cell cell, int cellType) {
		if (isValid(cell))
		{
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		return  getCellValue(cell);
	}

	private String getCellValue(Cell cell) {
		String val = null;
		if (isValid(cell))
		{
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				val = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				boolean cellVal = cell.getBooleanCellValue();
				val = String.valueOf(cellVal);
				break;
			case Cell.CELL_TYPE_NUMERIC:
				val =  new BigDecimal(cell.getNumericCellValue()).toPlainString();
				break;
			}
		}
		return val;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private KeyValue createKeyValue(String key, String value)
	{
		return  new DefaultKeyValue(key,defaultString(value)); 
	}


	private String defaultString(String value)
	{
		return org.apache.commons.lang3.StringUtils.defaultIfEmpty(value, "");   
	}

	
	private boolean isValid(@SuppressWarnings("rawtypes") KeyValue kv)
	{
		return (!StringUtils.isEmpty(kv.getKey()));   
	}
	
	private boolean isValid(Cell cell)
	{
		return (cell != null);   
	}

}