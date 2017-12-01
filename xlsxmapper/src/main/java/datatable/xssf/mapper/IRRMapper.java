package datatable.xssf.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.DataDTO;
import datatable.dto.IRRDTO;
import datatable.xssf.service.FileUtil;

public class IRRMapper extends XXSFMapper implements XSSFRowMapper {

	private List<String>  headers = FileUtil.readFileIntoList("mapping/IRRColumns.txt");

	public DataDTO mapRow(Row row) throws JsonProcessingException {
		return objectMapper.convertValue(rowToMap( headers, row), IRRDTO.class);
	}
}