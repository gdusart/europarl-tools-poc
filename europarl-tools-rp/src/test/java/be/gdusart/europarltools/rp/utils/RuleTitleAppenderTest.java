package be.gdusart.europarltools.rp.utils;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import be.gdusart.europarltools.rp.model.ReverseProxyRule;

public class RuleTitleAppenderTest {

	@Test
	public void testMe() throws Exception {
		String jsonInput = FileUtils.readFileToString(new ClassPathResource("pe_dmz_rules.json").getFile());
		List<ReverseProxyRule> rules = new ObjectMapper().readValue(jsonInput, new TypeReference<List<ReverseProxyRule>>(){});
		new RuleTitleAppender().addExpectedPageTitleToRules(rules, "http://www.europarl.europa.eu");
		
		String json = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(rules);
		
		FileUtils.writeStringToFile(new File("pe_dzm_rules_withtitle.json"), json);
		
		
	}
}
