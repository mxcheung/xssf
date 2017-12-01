package datatable.xssf.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.DataDTO;
import datatable.dto.SuffixDTO;
import datatable.xssf.service.FileUtil;

public class SuffixMapper extends XXSFMapper implements XSSFRowMapper {

	private List<String>  headers = FileUtil.readFileIntoList("mapping/SuffixColumns.txt");

	public DataDTO mapRow(Row row) throws JsonProcessingException {
		return objectMapper.convertValue(rowToMap( headers, row), SuffixDTO.class);
	}
}