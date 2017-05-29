package com.xwintop.apache.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @ClassName: SolrTest
 * @Description: solr API简单操作
 * @author: xufeng
 * @date: 2017年5月29日 下午11:59:12
 */
public class SolrTest {
	private static final String url = "http://localhost:8983/solr";// solr 部署的url
	private static final String uri = "my_news";// home

	// 添加一条数据
	@Test
	public void addDoc() throws SolrServerException, IOException {
		// 得到请求
		SolrClient sc = getSolrClient();
		// 拼装文本
		SolrInputDocument doc = new SolrInputDocument();
		// doc.addField("id", "id");
		// 其他参数省略。。。
		doc.addField("name", "我深深地爱着你，solr");
		sc.add(doc);
		sc.commit();
	}

	// 删除一条数据
	@Test
	public void deleteDocById() throws SolrServerException, IOException {
		// 得到请求
		SolrClient sc = getSolrClient();
		sc.deleteById("0");// id为0的数据
		sc.commit();
	}

	@Test
	// 删除全部数据
	public void deleteAllDoc() throws SolrServerException, IOException {
		// 得到请求
		SolrClient sc = getSolrClient();
		sc.deleteByQuery("*:*");
		sc.commit();
	}

	@Test
	// 根据id修改一个数据(与添加类似：存在就修改，不存在就添加)
	public void updateDocById() throws SolrServerException, IOException {
		// 得到请求
		SolrClient sc = getSolrClient();
		// 拼装文本
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "id");
		// 其他省略。。。
		doc.addField("name", "我深深地爱着你，solr !");
		sc.add(doc);
		sc.commit();
	}

	// 根据Id查询一条数据
	@Test
	public void getDocById() throws SolrServerException, IOException {
		// 得到请求
		SolrClient sc = getSolrClient();
		SolrDocument sd = sc.getById("id");
		System.out.println(sd); // 打印为：SolrDocument{id=id,
								// content_test=[我深深地爱着你，solr !],
								// _version_=1560544234369449984}
		System.out.println(sd.get("id"));
		System.out.println(sd.get("title"));
		System.out.println(sd.get("_version_"));

	}

	// 全部查询
	@Test
	public void getDocByAll() throws SolrServerException, IOException {
		SolrClient sc = getSolrClient();
		SolrQuery query = new SolrQuery();
		// 设置查询条件（全部）
		query.setQuery("*:*");
		// 查询
		SolrDocumentList solrDocumentList = sc.query(query).getResults();
		// 遍历结果集
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument);
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("name"));
			System.out.println(solrDocument.get("_version_"));
		}
	}

	// 分页和高亮
	@Test
	public void testSearch3() throws SolrServerException, IOException {
		SolrClient solr = getSolrClient();
		// 查询对象
		SolrQuery query = new SolrQuery();
		// 设置name、关键字查询
		query.setQuery("name:许峰");
		// 分页参数
		int pageSize = 2;// 每页显示记录数
		int curPage = 2;// 当前页码
		int begin = pageSize * (curPage - 1);// 开始记录下标
		query.setStart(begin);// 起始下标
		query.setRows(pageSize);// 结束下标
		// 设置高亮参数
		query.setHighlight(true); // 开启高亮组件
		query.addHighlightField("name");// 高亮字段
		query.setHighlightSimplePre("<span color='red'>");// 前缀标记
		query.setHighlightSimplePost("</span>");// 后缀标记 Solr
		// 全文检索服务 :
		QueryResponse response = solr.query(query);// 请求查询
		SolrDocumentList docs = response.getResults();// 查询结果
		System.out.println("查询文档总数" + docs.getNumFound());// 查询文档总数
		for (SolrDocument doc : docs) {
			String id = (String) doc.get("id");
			System.out.println("=============================");
			System.out.println("id=" + id);
			System.out.println("name=" + doc.get("name"));
			// 高亮信息
			if (response.getHighlighting() != null) {
				if (response.getHighlighting().get(id) != null) {
					// Solr 全文检索服务 :
					Map<String, List<String>> map = response.getHighlighting().get(id);// 取出高亮片段
					if (map.get("name") != null) {
						for (String s : map.get("name")) {
							System.out.println(s);
						}
					}
				}
			}
		}
	}

	/**
	 * 该对象有两个可以使用，都是线程安全的 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的 2、
	 * EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了 3、solr
	 * 4.0之后好像添加了不少东西，其中CommonsHttpSolrServer这个类改名为HttpSolrClient
	 * 
	 * @return
	 */
	public static SolrClient getSolrClient() {
		return new HttpSolrClient.Builder(url + "/" + uri).build();
		// return new HttpSolrClient(url + "/" + uri);
	}
}
