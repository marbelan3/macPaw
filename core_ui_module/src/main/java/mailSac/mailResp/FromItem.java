package mailSac.mailResp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FromItem{

	@JsonProperty("address")
	private String address;

	@JsonProperty("name")
	private String name;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"FromItem{" + 
			"address = '" + address + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}