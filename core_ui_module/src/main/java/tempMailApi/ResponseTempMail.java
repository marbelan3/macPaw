package tempMailApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTempMail{

	@JsonProperty("mail_preview")
	private String mailPreview;

	@JsonProperty("mail_id")
	private String mailId;

	@JsonProperty("mail_text_only")
	private String mailTextOnly;

	@JsonProperty("mail_subject")
	private String mailSubject;

	@JsonProperty("mail_html")
	private String mailHtml;

	@JsonProperty("mail_text")
	private String mailText;

	@JsonProperty("mail_from")
	private String mailFrom;

	@JsonProperty("createdAt")
	private CreatedAt createdAt;

	@JsonProperty("mail_attachments")
	private MailAttachments mailAttachments;

	@JsonProperty("mail_timestamp")
	private double mailTimestamp;

	@JsonProperty("_id")
	private Id id;

	@JsonProperty("mail_address_id")
	private String mailAddressId;

	@JsonProperty("mail_attachments_count")
	private int mailAttachmentsCount;

	public void setMailPreview(String mailPreview){
		this.mailPreview = mailPreview;
	}

	public String getMailPreview(){
		return mailPreview;
	}

	public void setMailId(String mailId){
		this.mailId = mailId;
	}

	public String getMailId(){
		return mailId;
	}

	public void setMailTextOnly(String mailTextOnly){
		this.mailTextOnly = mailTextOnly;
	}

	public String getMailTextOnly(){
		return mailTextOnly;
	}

	public void setMailSubject(String mailSubject){
		this.mailSubject = mailSubject;
	}

	public String getMailSubject(){
		return mailSubject;
	}

	public void setMailHtml(String mailHtml){
		this.mailHtml = mailHtml;
	}

	public String getMailHtml(){
		return mailHtml;
	}

	public void setMailText(String mailText){
		this.mailText = mailText;
	}

	public String getMailText(){
		return mailText;
	}

	public void setMailFrom(String mailFrom){
		this.mailFrom = mailFrom;
	}

	public String getMailFrom(){
		return mailFrom;
	}

	public void setCreatedAt(CreatedAt createdAt){
		this.createdAt = createdAt;
	}

	public CreatedAt getCreatedAt(){
		return createdAt;
	}

	public void setMailAttachments(MailAttachments mailAttachments){
		this.mailAttachments = mailAttachments;
	}

	public MailAttachments getMailAttachments(){
		return mailAttachments;
	}

	public void setMailTimestamp(double mailTimestamp){
		this.mailTimestamp = mailTimestamp;
	}

	public double getMailTimestamp(){
		return mailTimestamp;
	}

	public void setId(Id id){
		this.id = id;
	}

	public Id getId(){
		return id;
	}

	public void setMailAddressId(String mailAddressId){
		this.mailAddressId = mailAddressId;
	}

	public String getMailAddressId(){
		return mailAddressId;
	}

	public void setMailAttachmentsCount(int mailAttachmentsCount){
		this.mailAttachmentsCount = mailAttachmentsCount;
	}

	public int getMailAttachmentsCount(){
		return mailAttachmentsCount;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTempMail{" + 
			"mail_preview = '" + mailPreview + '\'' + 
			",mail_id = '" + mailId + '\'' + 
			",mail_text_only = '" + mailTextOnly + '\'' + 
			",mail_subject = '" + mailSubject + '\'' + 
			",mail_html = '" + mailHtml + '\'' + 
			",mail_text = '" + mailText + '\'' + 
			",mail_from = '" + mailFrom + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",mail_attachments = '" + mailAttachments + '\'' + 
			",mail_timestamp = '" + mailTimestamp + '\'' + 
			",_id = '" + id + '\'' + 
			",mail_address_id = '" + mailAddressId + '\'' + 
			",mail_attachments_count = '" + mailAttachmentsCount + '\'' + 
			"}";
		}
}