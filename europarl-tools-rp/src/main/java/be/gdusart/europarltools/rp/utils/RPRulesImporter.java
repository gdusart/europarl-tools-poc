package be.gdusart.europarltools.rp.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.gdusart.europarltools.rp.model.ReverseProxyRule;

public class RPRulesImporter {
	// RewriteRule ^/committees/(..)/(....)/studies.html$
	// /committees/$1/$2/supporting-analyses.html [R=301,L,NE]
	private static final Pattern SIMPLE_REWRITE_PATTERN = Pattern.compile("^RewriteRule (.*) (.*) \\[R=(...).*");

	private static final Logger LOG = LoggerFactory.getLogger(RPRulesImporter.class);

	private class ParsingResult {
		private List<ReverseProxyRule> rules = new ArrayList<>();
		private List<String> notParsed = new ArrayList<>();

		public List<ReverseProxyRule> getRules() {
			return rules;
		}

		public void setRules(List<ReverseProxyRule> rules) {
			this.rules = rules;
		}

		public List<String> getNotParsed() {
			return notParsed;
		}

		public void setNotParsed(List<String> notParsed) {
			this.notParsed = notParsed;
		}
	}

	public List<ReverseProxyRule> getRulesFromRpFile(Resource file) throws IOException {
		List<String> lines = FileUtils.readLines(file.getFile(), StandardCharsets.UTF_8);

		String previousLine = "";
		ParsingResult result = new ParsingResult();
		for (String line : lines) {
			parseLine(line, previousLine, result);
			previousLine = line;
		}

		LOG.info("Result :{}", new ObjectMapper().writeValueAsString(result));
		return result.rules;
	}

	private void parseLine(String line, String previousLine, ParsingResult result) {
		Matcher matcher = SIMPLE_REWRITE_PATTERN.matcher(line);
		if (!previousLine.startsWith("RewriteCond") && matcher.matches()) {
			ReverseProxyRule rule = new ReverseProxyRule();
			rule.setBaseUrl(replaceInputPatterns(matcher.group(1)));
			rule.setExpectedRedictionUrl(replaceOutputPatterns(matcher.group(2)));
//			rule.setExpectedCode(Integer.parseInt(matcher.group(3)));

			result.rules.add(rule);
		} else {
			result.notParsed.add(line);
		}
	}

	private String replaceOutputPatterns(String output) {
		String clean = stripRegexp(output);
		return clean.replaceFirst("\\$1", "fr");
	}

	private String replaceInputPatterns(String input) {
		String clean = stripRegexp(input);
		return clean.replaceFirst("\\(\\.\\.\\)", "fr"); // replace "(..)" with language
	}

	private static String stripRegexp(String input) {
		return StringUtils.stripEnd(StringUtils.stripStart(input, "^"), "$");		
	}


}
