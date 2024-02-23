package com.moss.idoc.stream.springbootstarter.service.base;

import java.io.Serializable;

/**
 * @author david
 * @date 2019-10-19 08:35
 **/
public interface IBaseExecService<T> {

	/**
	 * getMesType
	 *
	 * @return String
	 */
	public String getMesType();

	/**
	 * 转换 json 为实体类
	 *
	 * @return T
	 */
	public T execTemplate(String idocContent);

	public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter);

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
	 * @return T
	 */
	public T exec(T t);

	/**
	 * 缓存实体类
	 *
	 * @param t 操作实体类
	 * @return Serializable
	 */
	public Serializable cacheObject(T t);

	/**
	 * supportSendMessage
	 *
	 * @return boolean
	 */
	public boolean supportSendMessage();

	/**
	 * sendMessage
	 *
	 * @param t 操作实体类
	 * @return String
	 */
	public String sendMessage(T t);
}
