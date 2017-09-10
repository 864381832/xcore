package com.xwintop.xcore.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.exceptions.JedisException;

@Getter
@Setter
public class RedisUtil {
	private String name;
	private String password;
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

	public void del(String... key){
		jedis.del(key);
	}
	
	public String getString(String key) {
		return jedis.get(key);
	}

	public byte[] getString(byte[] key) {
		return jedis.get(key);
	}

	public void setString(String key, String value) {
		jedis.set(key, value);
	}

	public void insertList(String key, boolean beforeAfter, String pivot, String value) {
		jedis.linsert(key, beforeAfter ? LIST_POSITION.BEFORE : LIST_POSITION.AFTER, pivot, value);
	}

	public List<String> getList(String key) {
		return jedis.lrange(key, 0, -1);
	}

	public List<String> getList(String key, int start, int end) {
		return jedis.lrange(key, start, end);
	}

	public void setListValue(String key, int index, String value) {
		jedis.lset(key, index, value);
	}

	/** 
	 * @Title: addList 
	 * @Description: 添加List
	 */
	public void addList(String key, List<String> values, boolean headTail, boolean exist) {
		for (String value : values) {
			if (headTail && exist)
				jedis.rpush(key, value);
			else if (headTail && !exist)
				jedis.rpushx(key, value);
			else if (!headTail && exist)
				jedis.lpush(key, value);
			else
				jedis.lpushx(key, value);
		}
	}
	
	/** 
	 * @Title: updateList 
	 * @Description: 更新List
	 */
	public void updateList(String key, List<String> values) {
		updateList(key,values,true);
	}

	public void updateList(String key, List<String> values, boolean headTail) {
		Long time = jedis.ttl(key);
		jedis.del(key);
		addList(key,values,true,true);
		setDeadLine(key,time.intValue());
	}

	public String removeListValue(String key, boolean headTail) {
		if (headTail) {
			return jedis.lpop(key);
		} else {
			return jedis.rpop(key);
		}
	}
	
	public void addHash(String key, Map<String, String> values) {
		jedis.hmset(key, values);
	}
	
	public Map<String, String> getHash(String key) {
		return jedis.hgetAll(key);
	}
	
	public Set<String> getHashKeys(String key) {
		return jedis.hkeys(key);
	}
	
	public void setHashField(String key, String field, String value) {
		jedis.hset(key, field, value);
	}
	
	public void delHashField(String key, String[] fields) {
		jedis.hdel(key, fields);
	}
	
	public void updateHash(String key, Map<String, String> values) {
		Long time = jedis.ttl(key);
		jedis.del(key);
		addHash(key, values);
		setDeadLine(key,time.intValue());
	}

	public Long getDbSize() {
		return jedis.dbSize();
	}

	public Set<String> getListKeys() {
		Set<String> nodekeys = jedis.keys("*");
		return nodekeys;
	}

	public String getValueType(String key) {
		return jedis.type(key);
	}

	public Long getSize(String key) {
		Long size;
		String type = jedis.type(key);
		if (type.equals("string"))
			size = (long) 1;
		else if (type.equals("hash"))
			size = jedis.hlen(key);
		else if (type.equals("list"))
			size = jedis.llen(key);
		else if (type.equals("set"))
			size = jedis.scard(key);
		else
			size = jedis.zcard(key);
		return size;
	}

	/**
	 * @Title: getDeadline
	 * @Description: 获取过期时间
	 */
	public Long getDeadline(String key) {
		return jedis.ttl(key);
	}

	public void setDeadLine(String key, int second) {
		if (second != -1)
			jedis.expire(key, second);
		else
			jedis.persist(key);
	}

	public RedisUtil(Jedis jedis) {
		this.jedis = jedis;
	}

	public RedisUtil(String name, String host, int port) {
		this.name = name;
		this.jedis = new Jedis(host, port);
	}

	public RedisUtil(String name, String host, int port, String password) {
		this.name = name;
		this.jedis = new Jedis(host, port);
		if (StringUtils.isNotEmpty(password)) {
			jedis.auth(password);
			this.password = password;
		}
	}

	public RedisUtil clone() {
		Client client = jedis.getClient();
		RedisUtil redisUtil = new RedisUtil(name, client.getHost(), client.getPort(), password);
		return redisUtil;
	}

	public void setId(int id) {
		jedis.select(id);
		this.id = id;
	}

}
