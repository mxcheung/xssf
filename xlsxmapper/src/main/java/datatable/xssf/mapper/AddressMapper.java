package datatable.xssf.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.AddressDTO;
import datatable.dto.DataDTO;
import datatable.xssf.service.FileUtil;

public class AddressMapper extends XXSFMapper implements XSSFRowMapper {

	private List<String>  headers = FileUtil.readFileIntoList("mapping/AddressColumns.txt");

	public DataDTO mapRow(Row row) throws JsonProcessingException {
		return objectMapper.convertValue(rowToMap( headers, row), AddressDTO.class);
	}
}