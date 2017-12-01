package datatable.xssf.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.DataDTO;
import datatable.dto.USMDTO;
import datatable.xssf.service.FileUtil;

public class USMMapper extends XXSFMapper implements XSSFRowMapper {

	private List<String>  headers = FileUtil.readFileIntoList("mapping/USMColumns.txt");

	public DataDTO mapRow(Row row) throws JsonProcessingException {
		return objectMapper.convertValue(rowToMap( headers, row), USMDTO.class);
	}
}