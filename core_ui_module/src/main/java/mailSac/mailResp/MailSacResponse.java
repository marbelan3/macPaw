package mailSac.mailResp;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailSacResponse{

	@JsonProperty("cc")
	private List<Object> cc;

	@JsonProperty("bcc")
	private List<Object> bcc;

	@JsonProperty("attachments")
	private List<Object> attachments;

	@JsonProperty("read")
	private Object read;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("ip")
	private String ip;

	@JsonProperty("received")
	private String received;

	@JsonProperty("originalInbox")
	private String originalInbox;

	@JsonProperty("via")
	private String via;

	@JsonProperty("labels")
	private List<Object> labels;

	@JsonProperty("folder")
	private String folder;

	@JsonProperty("size")
	private int size;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("from")
	private List<FromItem> from;

	@JsonProperty("links")
	private List<String> links;

	@JsonProperty("_id")
	private String id;

	@JsonProperty("to")
	private List<ToItem> to;

	@JsonProperty("rtls")
	private boolean rtls;

	@JsonProperty("spam")
	private int spam;

	@JsonProperty("inbox")
	private String inbox;

	@JsonProperty("savedBy")
	private Object savedBy;

	public void setCc(List<Object> cc){
		this.cc = cc;
	}

	public List<Object> getCc(){
		return cc;
	}

	public void setBcc(List<Object> bcc){
		this.bcc = bcc;
	}

	public List<Object> getBcc(){
		return bcc;
	}

	public void setAttachments(List<Object> attachments){
		this.attachments = attachments;
	}

	public List<Object> getAttachments(){
		return attachments;
	}

	public void setRead(Object read){
		this.read = read;
	}

	public Object getRead(){
		return read;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return ip;
	}

	public void setReceived(String received){
		this.received = received;
	}

	public String getReceived(){
		return received;
	}

	public void setOriginalInbox(String originalInbox){
		this.originalInbox = originalInbox;
	}

	public String getOriginalInbox(){
		return originalInbox;
	}

	public void setVia(String via){
		this.via = via;
	}

	public String getVia(){
		return via;
	}

	public void setLabels(List<Object> labels){
		this.labels = labels;
	}

	public List<Object> getLabels(){
		return labels;
	}

	public void setFolder(String folder){
		this.folder = folder;
	}

	public String getFolder(){
		return folder;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setDomain(String domain){
		this.domain = domain;
	}

	public String getDomain(){
		return domain;
	}

	public void setFrom(List<FromItem> from){
		this.from = from;
	}

	public List<FromItem> getFrom(){
		return from;
	}

	public void setLinks(List<String> links){
		this.links = links;
	}

	public List<String> getLinks(){
		return links;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTo(List<ToItem> to){
		this.to = to;
	}

	public List<ToItem> getTo(){
		return to;
	}

	public void setRtls(boolean rtls){
		this.rtls = rtls;
	}

	public boolean isRtls(){
		return rtls;
	}

	public void setSpam(int spam){
		this.spam = spam;
	}

	public int getSpam(){
		return spam;
	}

	public void setInbox(String inbox){
		this.inbox = inbox;
	}

	public String getInbox(){
		return inbox;
	}

	public void setSavedBy(Object savedBy){
		this.savedBy = savedBy;
	}

	public Object getSavedBy(){
		return savedBy;
	}

	@Override
 	public String toString(){
		return 
			"MailSacResponse{" + 
			"cc = '" + cc + '\'' + 
			",bcc = '" + bcc + '\'' + 
			",attachments = '" + attachments + '\'' + 
			",read = '" + read + '\'' + 
			",subject = '" + subject + '\'' + 
			",ip = '" + ip + '\'' + 
			",received = '" + received + '\'' + 
			",originalInbox = '" + originalInbox + '\'' + 
			",via = '" + via + '\'' + 
			",labels = '" + labels + '\'' + 
			",folder = '" + folder + '\'' + 
			",size = '" + size + '\'' + 
			",domain = '" + domain + '\'' + 
			",from = '" + from + '\'' + 
			",links = '" + links + '\'' + 
			",_id = '" + id + '\'' + 
			",to = '" + to + '\'' + 
			",rtls = '" + rtls + '\'' + 
			",spam = '" + spam + '\'' + 
			",inbox = '" + inbox + '\'' + 
			",savedBy = '" + savedBy + '\'' + 
			"}";
		}
}