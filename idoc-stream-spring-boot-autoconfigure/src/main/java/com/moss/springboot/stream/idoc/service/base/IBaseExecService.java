package com.moss.springboot.stream.idoc.service.base;

import java.io.Serializable;

/**
 * @author david
 * @date 2019-10-19 08:35
 **/
public interface IBaseExecService<T, R> {

	/**
	 * getMesType
	 *
	 * @return String
	 */
	public String getMesType();

	public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter);

	/**
	 * 转换 json 为实体类
	 *
	 * @return T
	 */
	public T execTemplate(String idocContent);

	/**
	 * 转换 idocContentConvert
	 *
	 * @return String
	 */
	public String idocContentConvert(String idocContent);

	/**
	 * 对实体类进行操作
	 *
	 * @param t 操作实体类
	 * @return R 返回实体类
	 */
	public R exec(T t);

	/**
	 * 缓存实体类
	 *
	 * @param t 操作实体类
	 * @return Serializable
	 */
	default Serializable cacheObject(T t) {
		return null;
	}

	default boolean supportSendMessage() {
		return false;
	}

	default String sendMessage(T t) {
		return null;
	}

	public boolean idocContentNotConvert();
}
