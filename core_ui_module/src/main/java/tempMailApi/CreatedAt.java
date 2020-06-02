package tempMailApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedAt{

	@JsonProperty("milliseconds")
	private long milliseconds;

	public void setMilliseconds(long milliseconds){
		this.milliseconds = milliseconds;
	}

	public long getMilliseconds(){
		return milliseconds;
	}

	@Override
 	public String toString(){
		return 
			"CreatedAt{" + 
			"milliseconds = '" + milliseconds + '\'' + 
			"}";
		}
}