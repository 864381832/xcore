package com.xwintop.xcore.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

@Getter
@Setter
public class RedisUtil {
	private Jedis jedis;
	private int id;

	/** 
	 * @Title: getDbAmount 
	 * @Description: 获取数据库db大小
	 */
	public int getDbAmount() {
		int dbAmount = 0;
		try {
			List<String> dbs = jedis.configGet("databases");
			if (dbs.size() > 0)
				dbAmount = Integer.parseInt(dbs.get(1));
		} catch (JedisException e) {
			dbAmount = 15;
		}
		return dbAmount;
	}
	
	public Long getDbSize() {
		jedis.select(id);
		return jedis.dbSize();
	}

	public RedisUtil(Jedis jedis) {
		this.jedis = jedis;
	}

	public RedisUtil(String name, String host, int port) {
		this.jedis = new Jedis(host, port);
	}

	public RedisUtil(String name, String host, int port, String password) {
		this.jedis = new Jedis(host, port);
		if (StringUtils.isNotEmpty(password)) {
			jedis.auth(password);
		}
	}
}
