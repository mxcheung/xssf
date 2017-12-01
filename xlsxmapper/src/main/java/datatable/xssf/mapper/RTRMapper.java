package datatable.xssf.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.DataDTO;
import datatable.dto.RTRDTO;
import datatable.xssf.service.FileUtil;

public class RTRMapper extends XXSFMapper implements XSSFRowMapper {

	private List<String>  headers = FileUtil.readFileIntoList("mapping/RTRColumns.txt");

	public DataDTO mapRow(Row row) throws JsonProcessingException {
		return objectMapper.convertValue(rowToMap( headers, row), RTRDTO.class);
	}
}