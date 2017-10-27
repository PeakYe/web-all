package pers.abaneo.web.utils.model;

import java.util.HashMap;
import java.util.Map;

public class ResultModel{
	private boolean success;
	/**
	 * 0-999为固定值，全局有效
	 * 1000以上为自定义值，有效范围为方法级别
	 * 
	 * 已定义全局值：
	 * 0：成功
	 * 1：失败
	 * 2：程序异常失败
	 */
	private int errCode;
	private String message;
	private Object data;
	
	public ResultModel() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public ResultModel(boolean isSuccess,String message) {
		this.success=isSuccess;
		this.errCode=isSuccess?0:1;
		this.message=message;
		init();
	}
	public ResultModel(boolean isSuccess,int errCode,String message) {
		this.success=isSuccess;
		this.errCode=isSuccess?0:1;
		this.errCode=errCode;
		this.message=message;
		init();
	}
	public ResultModel(Object data) {
		this.success=true;
		this.errCode=success?0:1;
		this.data=data;
		init();
	}
	
	private void init(){
		
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultModel setSuccess(boolean isSuccess) {
		this.success = isSuccess;
		return this;
	}

	public int getErrCode() {
		return errCode;
	}

	public ResultModel setErrCode(int errCode) {
		this.errCode = errCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResultModel setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResultModel setData(Object data) {
		this.data = data;
		return this;
	}
	
	public ResultModel setMapData(Object...strings ){
		HashMap<Object, Object> map=new HashMap<>();
		for(int i=0;i<strings.length;i=i+2){
			if(strings.length<i+1){
				throw new RuntimeException("参数必须为偶数 :"+strings.length);
			}
			map.put(strings[i], strings[i+1]);
		}
		this.data=map;
		return this;
	}
	
	@Override
	public String toString(){
//		JsonObject json = new JsonObject();
//		
//		json.addProperty("success", success);
//		json.addProperty("message", success);
//		json.addProperty("data", JsonUtils.objectToJson(o));
//		json.addProperty("success", success);
//		return json.toString();
//		return JsonUtils.objectToJson(this);
		return "";
	}
	
	public static void main(String[] args) {
		ResultModel model=new ResultModel(true,2,"系统异常");
		Map<String, String> c=new HashMap<>();
		c.put("sss", "aasd");
		model.setData(c);
//		System.out.println(JsonUtils.objectToJson(model));
	}
	
}
