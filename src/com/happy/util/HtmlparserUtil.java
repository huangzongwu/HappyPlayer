package com.happy.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 网页解析
 * 
 * @author zhangliangming
 * 
 */
public class HtmlparserUtil {
	private static String encoding = "utf-8";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(getBannerData("http://www.gdhrss.gov.cn/publicfiles/business/htmlfiles/gdhrss/index.html"));
		System.out
				.println(getBannerDetailData("http://www.gdhrss.gov.cn/publicfiles/business/htmlfiles/gdhrss/tpxw/201605/57614.html"));
	}

	/**
	 * 获取详情
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> getBannerDetailData(String url) {
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encoding);
			NodeFilter filterDate = new HasAttributeFilter("class",
					"contentdate");
			NodeFilter imgFilter = new TagNameFilter("IMG");
			NodeFilter filterCom = new HasAttributeFilter("class", "MsoNormal");
			NodeFilter[] filters = new NodeFilter[3];
			filters[0] = filterDate;
			filters[1] = imgFilter;
			filters[2] = filterCom;
			NodeFilter filter = new OrFilter(filters);
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if (nodes != null) {
				Map<String, String> result = new HashMap<String, String>();
				String msoNormal = "";
				String imgUrls = "";
				boolean flag = false;
				for (int i = 0; i < nodes.size(); i++) {
					Node nodeE = nodes.elementAt(i);
					if (nodeE != null) {
						if (nodeE instanceof Div) {
							result.put("contentdate", nodeE.getChildren()
									.toHtml().trim());
						} else if (nodeE instanceof ImageTag) {
							ImageTag imageTag = (ImageTag) nodeE;
							String imgUrl = imageTag.getImageURL();
							if (imgUrl
									.startsWith("http://www.gdhrss.gov.cn/publicfiles/business/")) {
								if (!flag) {
									imgUrls += imgUrl + ",";
								} else {
									imgUrls += imgUrl + "";
									flag = true;
								}
							}
						} else {
							msoNormal += nodeE.toHtml();
						}
					}
				}
				result.put("MsoNormal", msoNormal);
				result.put("IMG", imgUrls);
				return result;
			}
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取banner数据
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getBannerData(String url) {
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encoding);
			NodeFilter filterUL = new HasAttributeFilter("id", "pub_slideplay");
			NodeList nodes = parser.extractAllNodesThatMatch(filterUL);
			if (nodes != null) {
				if (nodes.size() > 0) {
					Node nodeUL = nodes.elementAt(0);
					if (nodeUL != null) {
						NodeList nodeLIs = nodeUL.getChildren();
						if (nodeLIs != null) {
							List<Map<String, String>> results = new ArrayList<Map<String, String>>();
							for (int i = 0; i < nodeLIs.size(); i++) {
								Node nodeLI = nodeLIs.elementAt(i);
								if (nodeLI != null) {
									Map<String, String> result = parserLI(nodeLI);
									if (result != null && !result.isEmpty()) {
										results.add(result);
									}
								}
							}
							return results;
						}
					}

				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private static Map<String, String> parserLI(Node node) {

		try {
			String html = node.toHtml();
			Parser parser = Parser.createParser(html, encoding);
			NodeFilter imgFilter = new TagNameFilter("IMG");
			NodeFilter aFilter = new TagNameFilter("a");
			NodeFilter filter = new OrFilter(imgFilter, aFilter);
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if (nodes != null) {
				Map<String, String> result = new HashMap<String, String>();
				for (int i = 0; i < nodes.size(); i++) {
					Node nodeE = nodes.elementAt(i);
					if (nodeE != null) {
						String baseUrl = "http://www.gdhrss.gov.cn/";
						if (nodeE instanceof LinkTag) {
							LinkTag linkTag = (LinkTag) nodeE;
							String name = linkTag.getAttribute("name");
							if (name == null || name.equals("")) {
								result.put("title",
										linkTag.getAttribute("title"));
								String url = linkTag.getLink();
								url = url.replaceAll("\\.\\./", "");
								result.put("url", baseUrl + "publicfiles/"
										+ url);
							}
						} else if (nodeE instanceof ImageTag) {
							ImageTag imageTag = (ImageTag) nodeE;
							result.put("imgUrl",
									baseUrl + imageTag.getImageURL());
						}
					}
				}
				return result;
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
}
