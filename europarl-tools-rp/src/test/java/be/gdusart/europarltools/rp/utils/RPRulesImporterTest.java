package be.gdusart.europarltools.rp.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import be.gdusart.europarltools.rp.model.ReverseProxyRule;

public class RPRulesImporterTest {

	@Test
	public void testMe() throws IOException {
		List<ReverseProxyRule> result = new RPRulesImporter().getRulesFromRpFile(new ClassPathResource("pe_dmz.conf"));
		
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		FileUtils.writeStringToFile(new File("pe_dmz.rules"), mapper.writeValueAsString(result));
	}
}
