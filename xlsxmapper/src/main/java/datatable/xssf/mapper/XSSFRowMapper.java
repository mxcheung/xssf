package datatable.xssf.mapper;

import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;

import datatable.dto.DataDTO;

public interface XSSFRowMapper {
	DataDTO mapRow(Row row) throws JsonProcessingException;
}
