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
public class ResultMessage<T> {
	
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
	 * 视图数据
	 */
	private T data;

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
	 * 成功
	 * @return
	 */
	public static<T> ResultMessage<T> success(T data){
		ResultMessage<T> message=new ResultMessage<>();
		message.setCode(successMsgEnum.getCode());
		message.setMessage(successMsgEnum.getMsg());
		message.setData(data);
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
		ResultMessage message=new ResultMessage<>();
		message.setCode(code);
		message.setMessage(msg);
		return message;
	}
	
	/**
	 * 构建自定义信息
	 * @param code
	 * @param msg
	 * @return
	 */
	public static<T> ResultMessage<T> build(Integer code, String msg, T data){
		ResultMessage<T> message=new ResultMessage<>();
		message.setCode(code);
		message.setMessage(msg);
		message.setData(data);
		return message;
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
