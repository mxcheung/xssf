package datatable.xssf.service;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.junit.Before;
import org.junit.Test;

import datatable.dto.AddressDTO;
import datatable.dto.CountryDTO;
import datatable.dto.DataDTO;
import datatable.dto.IRRDTO;
import datatable.dto.RTRDTO;
import datatable.dto.SuffixDTO;
import datatable.dto.USMDTO;


public class XSSFServiceTest {


	private static final String XSSF_RESOURCE = "/xssf/testdata2.xlsx";

	private  InputStream excelFileToRead = getClass().getResourceAsStream(XSSF_RESOURCE);

	private XSSFService xSSFServiceImpl;



	@Before
	public void setup() throws Exception {
        xSSFServiceImpl = new XSSFServiceImpl();

	}

	@Test
	public void test2() throws  IOException, OpenXML4JException {
		Map<String, List<DataDTO>> wbData = xSSFServiceImpl.extractData(excelFileToRead);
		assertEquals(6,wbData.size());
		
		List<DataDTO> countryCodes = wbData.get("Country");
		assertEquals(2,countryCodes.size());
		CountryDTO country = (CountryDTO) countryCodes.get(0);
		assertEquals("AU",country.getCountryCode());

		List<DataDTO> suffixCodes = wbData.get("Suffix");
		assertEquals(2,suffixCodes.size());
		SuffixDTO suffix = (SuffixDTO) suffixCodes.get(0);
		assertEquals("MC",suffix.getCodeKey());

		
		List<DataDTO> addressCodes = wbData.get("Address");
		assertEquals(1,addressCodes.size());
		AddressDTO address = (AddressDTO) addressCodes.get(0);
		assertEquals("220",address.getStreetNo());

	}
	
	
    @Test
    public void testIRR() throws  IOException, OpenXML4JException {
        Map<String, List<DataDTO>> wbData = xSSFServiceImpl.extractData(excelFileToRead);
        assertEquals(6,wbData.size());
        List<DataDTO> iRRs = wbData.get("IRR");
        assertEquals(1,iRRs.size());
        IRRDTO iRR = (IRRDTO) iRRs.get(0);
        assertEquals("Family Name",iRR.getFamilyName());
        assertEquals("TFN",iRR.getTfn());
        assertEquals("Other Details 1 Text ",iRR.getOtherDetailsText1());
        assertEquals("Other Details 1 Description",iRR.getOtherDetailsDesc1());
        assertEquals("Other Details 2 Text ",iRR.getOtherDetailsText2());
        assertEquals("Other Details 2 Description",iRR.getOtherDetailsDesc2());
        assertEquals("Other Details 3 Text ",iRR.getOtherDetailsText3());
        assertEquals("Other Details 3Description",iRR.getOtherDetailsDesc3());
        assertEquals("Other Details 4 Text ",iRR.getOtherDetailsText4());
        assertEquals("Other Details 4 Description",iRR.getOtherDetailsDesc4());
        assertEquals("Other Details 5 Text ",iRR.getOtherDetailsText5());
        assertEquals("Other Details 5 Description",iRR.getOtherDetailsDesc5());
        assertEquals("Other Details 6 Text ",iRR.getOtherDetailsText6());
        assertEquals("Other Details 6 Description",iRR.getOtherDetailsDesc6());
    }

    @Test
    public void testRTR() throws  IOException, OpenXML4JException {
        Map<String, List<DataDTO>> wbData = xSSFServiceImpl.extractData(excelFileToRead);
        assertEquals(6,wbData.size());
        List<DataDTO> rTRs = wbData.get("RTR");
        assertEquals(1,rTRs.size());
        RTRDTO rTR = (RTRDTO) rTRs.get(0);
        assertEquals("Family Name",rTR.getFamilyName());
        assertEquals("TFN",rTR.getTfn());
        assertEquals("Other Details 1 Description",rTR.getOtherDetailsDesc1());
        assertEquals("Other Details 1 Text ",rTR.getOtherDetailsText1());
        assertEquals("Other Details 1 Description",rTR.getOtherDetailsDesc1());
        assertEquals("Other Details 2 Text ",rTR.getOtherDetailsText2());
        assertEquals("Other Details 2 Description  ",rTR.getOtherDetailsDesc2());
        assertEquals("Other Details 3 Text ",rTR.getOtherDetailsText3());
        assertEquals("Other Details 3Description",rTR.getOtherDetailsDesc3());
        assertEquals("Other Details 4 Text ",rTR.getOtherDetailsText4());
        assertEquals("Other Details 4 Description",rTR.getOtherDetailsDesc4());
        assertEquals("Other Details 5 Text ",rTR.getOtherDetailsText5());
        assertEquals("Other Details 5 Description",rTR.getOtherDetailsDesc5());
        assertEquals("Other Details 6 Text ",rTR.getOtherDetailsText6());
        assertEquals("Other Details 6 Description",rTR.getOtherDetailsDesc6());
    }

    @Test
    public void testUSM() throws  IOException, OpenXML4JException {
        Map<String, List<DataDTO>> wbData = xSSFServiceImpl.extractData(excelFileToRead);
        assertEquals(6,wbData.size());
        List<DataDTO> uSMs = wbData.get("USM");
        assertEquals(1,uSMs.size());
        USMDTO uSM = (USMDTO) uSMs.get(0);
        assertEquals("Family Name",uSM.getFamilyName());
        assertEquals("TFN",uSM.getTfn());
        assertEquals("Other Details 1 Description",uSM.getOtherDetailsDesc1());
        assertEquals("Other Details 1 Text ",uSM.getOtherDetailsText1());
        assertEquals("Other Details 1 Description",uSM.getOtherDetailsDesc1());
        assertEquals("Other Details 2 Text ",uSM.getOtherDetailsText2());
        assertEquals("Other Details 2 Description  ",uSM.getOtherDetailsDesc2());
        assertEquals("Other Details 3 Text ",uSM.getOtherDetailsText3());
        assertEquals("Other Details 3Description",uSM.getOtherDetailsDesc3());
        assertEquals("Other Details 4 Text ",uSM.getOtherDetailsText4());
        assertEquals("Other Details 4 Description",uSM.getOtherDetailsDesc4());
        assertEquals("Other Details 5 Text ",uSM.getOtherDetailsText5());
        assertEquals("Other Details 5 Description",uSM.getOtherDetailsDesc5());
        assertEquals("Other Details 6 Text ",uSM.getOtherDetailsText6());
        assertEquals("Other Details 6 Description",uSM.getOtherDetailsDesc6());
    }
    


}
