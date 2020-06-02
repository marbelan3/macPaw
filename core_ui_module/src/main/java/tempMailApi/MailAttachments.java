package tempMailApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailAttachments{

	@JsonProperty("attachment")
	private List<Object> attachment;

	public void setAttachment(List<Object> attachment){
		this.attachment = attachment;
	}

	public List<Object> getAttachment(){
		return attachment;
	}

	@Override
 	public String toString(){
		return 
			"MailAttachments{" + 
			"attachment = '" + attachment + '\'' + 
			"}";
		}
}