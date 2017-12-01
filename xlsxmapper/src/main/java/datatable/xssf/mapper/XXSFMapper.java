package datatable.xssf.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;

public class XXSFMapper {

	protected ObjectMapper objectMapper = new ObjectMapper();

	public XXSFMapper() {
		super();
	}

	protected HashMap<String, Object> rowToMap(List<String> headers, Row row ) {
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
			for (Cell cell : row){
				int i = cell.getColumnIndex();
				String  cellValue = getCellValue(cell);
				String coName = headers.get(i);
				dataMap.put(coName, cellValue);
			}
		return dataMap;
	}
	
	private String  getCellValue(Cell cell) {
		String val = null;
		cell.setCellType(Cell.CELL_TYPE_STRING);
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			val = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			boolean cellVal = cell.getBooleanCellValue();
			val = String.valueOf(cellVal);
			break;
		case Cell.CELL_TYPE_NUMERIC:
			Double x = cell.getNumericCellValue();
			val = String.valueOf(x);
			break;
		}
		return val;
	}
	
	
}