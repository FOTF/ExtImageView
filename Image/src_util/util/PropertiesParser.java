package util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {

	private String propertiesFileName;
	private Properties m_configuration;

	// private String propertiesFilePath;

	public PropertiesParser(String propertiesFileName) throws Exception {
		super();
		this.propertiesFileName = propertiesFileName;
		getConfig();
	}

	private void getConfig() throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				propertiesFileName);
		if (is == null) {
			throw new FileNotFoundException("Cannot find " + propertiesFileName
					+ " file in classpath");
		}

		Properties p = new Properties();
		p.load(is);

		synchronized (this) {
			m_configuration = p;
		}
	}

	public String getInfoFromConfiguration(String key) {
		synchronized(this) {
			return m_configuration.getProperty(key);
		}
	}
	
	public int getInfoFromConfigurationInt(String key){
		synchronized(this) {
			return Integer.parseInt(m_configuration.getProperty(key));
		}
	}
}
