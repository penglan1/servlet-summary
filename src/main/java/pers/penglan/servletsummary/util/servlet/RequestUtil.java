package pers.penglan.servletsummary.util.servlet;

import org.apache.logging.log4j.core.util.StringBuilderWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * 处理有关请求的工具类
 * @author PENGL
 */
public class RequestUtil {
    private final HttpServletRequest request;  // 关联的request

    public RequestUtil(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 以字符串的形式返回请求体
     * @param charset 对字节流以何种方式解码
     * @return
     */
    public String getBody(String charset) {
        BufferedReader bufferedReader = null;
        String bodyString = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));
            StringBuilder stringBuilder = new StringBuilder(1000);
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
            bodyString = stringBuilder.toString();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bodyString;
    }

    /**
     * 以字符串的形式返回请求体
     * @return
     */
    public String getBody() {
        return getBody("UTF-8");
    }

    /**
     * 获取没有经过解码的URL
     * @param queryString 指定是否要包含QueryString, true 包含；false 不包含
     * @return
     */
    public String getURL(boolean queryString) {
        StringBuffer URL = request.getRequestURL();
        if (queryString && request.getQueryString() != null) {
            URL.append("?").append(request.getQueryString());
        }

        return URL.toString();
    }

    /**
     * 根据指定编码获取经过解码的URL
     * @param encoding 编码
     * @param queryString 是否包含Query String, true 包含；false 不包含
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getDecodedURL(String encoding, boolean queryString) throws UnsupportedEncodingException {
        String URL = getURL(queryString);
        encoding = Charset.forName(encoding).toString();
        String decodedURL = URLDecoder.decode(URL, encoding);

        return decodedURL;
    }

    /**
     * 默认使用UTF-8编码获取经过解码的URL
     * @param queryString 是否包含Query String, true 包含；false 不包含
     * @return
     */
    public String getDecodedURL(boolean queryString) {
        String decodedURL = null;
        try {
            decodedURL = getDecodedURL("UTF-8", queryString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decodedURL;
    }

    /**
     * 根据指定编码获取经过解码的URL
     * @param encoding 编码
     * @param queryString 是否包含Query String, true 包含；false 不包含
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getDecodedURI(String encoding, boolean queryString) throws UnsupportedEncodingException {
        String URI = getURI(queryString);
        encoding = Charset.forName(encoding).toString();
        String decodedURI = URLDecoder.decode(URI, encoding);

        return decodedURI;
    }

    /**
     * 默认使用UTF-8编码获取经过解码的URI
     * @param queryString 是否包含Query String, true 包含；false 不包含
     * @return
     */
    public String getDecodedURI(boolean queryString) {
        String decodedURI = null;
        try {
            decodedURI = getDecodedURI("UTF-8", queryString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decodedURI;
    }

    /**
     * 获取没有经过解码的URI
     * @param queryString 指定是否要包含QueryString, true 包含；false 不包含
     * @return
     */
    public String getURI(boolean queryString) {
        String URI = request.getRequestURI();
        if (queryString && request.getQueryString() != null) {
            URI += "?" + request.getQueryString();
        }

        return URI;
    }

    /**
     * 根据指定的编码获得Query String
     * @param encoding 编码
     * @return
     */
    public String getDecodedQueryString(String encoding) throws UnsupportedEncodingException {
        // 获得指定的编码
        encoding = Charset.forName(encoding).toString();
        String queryString = null;
        if (request.getQueryString() != null)
            queryString = URLDecoder.decode(request.getQueryString(), encoding);

        return queryString;
    }

    /**
     * 默认使用UTF-8编码获得Query String
     * @return
     */
    public String getDecodedQueryString() {
        String queryString = null;
        try {
            queryString = getDecodedQueryString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return queryString;
    }

    /**
     * 根据指定编码获取经过编码的name参数值
     * @param name 参数名
     * @param encoding 编码
     * @return
     */
    public String getEncodeParameter(String name, String encoding) throws UnsupportedEncodingException {
        encoding = Charset.forName(encoding).toString();
        String value = null;
        if (request.getParameter(name) != null) {
            value = URLEncoder.encode(request.getParameter(name), encoding);
        }

        return value;
    }

    /**
     * 默认使用UTF-8编码获取经过编码的name参数值
     * @param name 参数名
     * @return
     */
    public String getEncodeParameter(String name) {
        String value = null;
        try {
            value = getEncodeParameter(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 根据指定的编码获取对应的name的Cookie
     * 注意：此处的name是没有经过编码的，这里会根据指定的编码将所有的cookies都解码之后再和name比较
     * @param name 目标Cookie的name
     * @param encoding name比较时使用的编码
     * @return 不存在name对应的Cookie则返回null
     * @throws UnsupportedEncodingException
     */
    public Cookie getDecodedCookie(String name, String encoding) throws UnsupportedEncodingException {
        encoding = Charset.forName(encoding).toString();
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                String decodeName = URLDecoder.decode(c.getName(), encoding);
                if (decodeName.equals(name))
                    return c;
            }
        }
        return null;
    }

    /**
     * 默认使用UTF-8编码来比较name，从而获取对应的的Cookie
     * @param name 目标Cookie的name
     * @return 如果不存在name对应的Cookie则返回null
     */
    public Cookie getDecodedCookie(String name) {
        String encoding = "UTF-8";
        Cookie cookie = null;
        try {
            cookie = getDecodedCookie(name, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return cookie;
    }

    /**
     * 默认使用UTF-8编码来比较name，从而获取对应的的Cookie的value
     * @param name 目标Cookie的name
     * @return cookie对应的value，如果不存在则抛出异常
     */
    public String getDeCookieValue(String name) {
        // 有可能抛出 NullPointerException
        return getDecodedCookie(name).getValue();
    }

    /**
     * 使用指定的编码来比较name,从而获取对应的Cookie的value
     * @param name 目标Cookie的name
     * @param encoding 编码
     * @return Cookie对应的value,如果不存在，则抛出异常
     * @throws UnsupportedEncodingException
     */
    public String getDeCookieValue(String name, String encoding) throws UnsupportedEncodingException {
        return getDecodedCookie(name, encoding).getValue();
    }

    /**
     * 根据指定编码获得对value进行解码
     * @param value 需要解码的字符串
     * @param encoding 编码
     * @return 解码后的值
     * @throws UnsupportedEncodingException
     */
    public String getDecodedValue(String value, String encoding) throws UnsupportedEncodingException {
        encoding = Charset.forName(encoding).toString();
        value = URLDecoder.decode(value, encoding);
        return value;
    }

    /**
     * 默认使用UTF-8编码对value进行解码
     * @param value  需要解码的字符串
     * @return 解码后的值
     */
    public String getDecodedValue(String value) {
        String decodedValue =  null;
        try {
            decodedValue = getDecodedValue(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedValue;
    }

}
