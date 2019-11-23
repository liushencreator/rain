package util.result;

import exception.DefaultErrorMsgEnum;
import exception.DefaultSuccessMsgEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回信息
 * @author raojing
 * @date 2019/11/15 12:37
 */
@Data
public class ResultMessage {
	
	private static DefaultSuccessMsgEnum successMsgEnum = DefaultSuccessMsgEnum.SUCCESS;
	private static DefaultErrorMsgEnum errorMsgEnum = DefaultErrorMsgEnum.FAIL;

	/**
	 * code
	 */
	private Integer code;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 记录数(分页使用)
	 */
	private Integer count;

	/**
	 * 分页数据(分页使用)
	 */
	private Object pageData;

	/**
	 * 视图数据
	 */
	private Map<String,Object> data = new HashMap<String,Object>();

	/**
	 * 私有化构造器
	 */
	private ResultMessage(){};

	/**
	 * 成功
	 * @return
	 */
	public static ResultMessage success(){
		ResultMessage message=new ResultMessage();
		message.setCode(successMsgEnum.getCode());
		message.setMessage(successMsgEnum.getMsg());
		return message;
	}

	/**
	 * 失败
	 * @return
	 */
	public static ResultMessage fail(){
		ResultMessage message=new ResultMessage();
		message.setCode(errorMsgEnum.getCode());
		message.setMessage(errorMsgEnum.getMsg());
		return message;
	}

	/**
	 * 构建自定义信息
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResultMessage build(Integer code, String msg){
		ResultMessage message=new ResultMessage();
		message.setCode(code);
		message.setMessage(msg);
		return message;
	}

	/**
	 * 返回视图数据
	 * @param key
	 * @param value
	 * @return
	 */
	public ResultMessage add(String key, Object value){
		this.getData().put(key, value);
		return this;
	}

	public ResultMessage page(Integer count, Object pageDataa){
		this.count = count;
		this.pageData = pageDataa;
		return this;
	}

	/**
	 * 返回信息
	 * @param message
	 * @return
	 */
	public ResultMessage addMessage(String message){
		this.setMessage(message);
		return this;
	}
	
}
