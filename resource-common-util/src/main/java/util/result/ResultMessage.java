package util.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultMessage {
	private Integer core;
	private String message;
	private Map<String,Object> map=new HashMap<String,Object>();
	
	public static ResultMessage success(){
		ResultMessage message=new ResultMessage();
		message.setCore(100);
		message.setMessage("操作成功");
		return message;
	}
	
	public static ResultMessage fail(){
		ResultMessage message=new ResultMessage();
		message.setCore(200);
		message.setMessage("操作失败");
		return message;
	}
	
	public ResultMessage add(String key, Object value){
		this.getMap().put(key, value);
		return this;
	}
	
	public ResultMessage addMessage(String message){
		this.setMessage(message);
		return this;
	}
	
}
