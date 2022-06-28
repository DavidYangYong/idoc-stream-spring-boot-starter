package com.fl.idoc.stream.springbootstarter.service.base;

import java.io.Serializable;

/**
 * @author david
 * @create 2019-10-19 08:35
 **/
public interface IBaseExecService<T> {

	/**
	 * 转换 json 为实体类
	 *
	 * @param idocContent json idoc
	 */
	public T execTemplate(String idocContent);

	/**
	 * 对实体类进行操作
	 *
	 * @param t 操作实体类
	 */
	public T exec(T t);

	/**
	 * 缓存实体类
	 *
	 * @return Serializable
	 */
	public Serializable cacheObject(T t);

	public boolean supportSendMessage();

	public String sendMessage(T t);
}
