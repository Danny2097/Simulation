package simulation.api.utils;


import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

public class IndexingUtils {
	
	
	public static Header[] readHeaders = getReadHeaders();

	public static Header[] writeHeaders = getWriteHeaders();
	
	static Header[] getReadHeaders() {

		Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
				new BasicHeader("Role", "Read") };
		return headers;
	}


	static Header[] getWriteHeaders() {

		Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
				new BasicHeader("Role", "Write") };
		return headers;
	}
	
	

}
