package datatable.xssf.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import datatable.dto.DataDTO;

public interface XSSFService {


	Map<String, List<DataDTO>> extractData(InputStream ExcelFileToRead) throws IOException, OpenXML4JException;

}
