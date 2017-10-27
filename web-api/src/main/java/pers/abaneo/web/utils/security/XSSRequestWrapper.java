package pers.abaneo.web.utils.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String> xssWords;

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
		xssWords = new HashMap<String, String>();
		xssWords.put("script", "");
		xssWords.put("javascript", "");
		xssWords.put("eval", "");
		xssWords.put("\\", "\\\\");
		xssWords.put("<", "＜");
		xssWords.put(">", "＞");
		xssWords.put("'", "\\\'");
		xssWords.put("｀", "\\｀");
		xssWords.put("\"", "\\\"");
		xssWords.put("/", "\\/");
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return replace(value);
	}

	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null)
			return null;
		for (int i = 0; i < values.length; i++) {
			values[i] = replace(values[i]);
		}
		return values;
	}

	private String replace(String value) {
		if(value==null){
			return null;
		}
		for (Map.Entry<String, String> entry : xssWords.entrySet()) {
			value = value.replace(entry.getKey(), entry.getValue());
		}
		return value;
	}

}
