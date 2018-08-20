package com.choice.sign.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.choice.sign.util.http.HttpPool;
import com.choice.sign.util.http.HttpResult;

public class HttpHelper {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000).build();

    private static final HttpHelper instance = new HttpHelper();

    public static HttpHelper getInstance() {
        return instance;
    }

    private HttpHelper() {
    }

    public HttpResult get(String uri) throws Exception{
        return get(uri, null);
    }
    public String getAndLog(String uri,String startLogSt,String endLogSt)throws Exception{
        long startTime = System.currentTimeMillis();
        HttpResult httpResult = null;
        logger.info("【请求标志"+startTime+"】"+startLogSt);
        httpResult = get(uri, null);
        if(httpResult.getStatusCode() != 200){
            throw new Exception("【错误:http请求返回非200】");
        }
        long endTime = System.currentTimeMillis();
        String s = new String(httpResult.getResponseBody());
        logger.info("【请求标志"+startTime+"】"+endLogSt+"【用时"+(endTime-startTime)/1000+"】"+"返回【"+s+"】");
        return s;
    }

    public HttpResult get(String uri, Map<String, String> headers) throws Exception{
        return get(uri, headers, null);
    }

    public HttpResult get(String uri, Map<String, String> headers, Map<String, Object> parameters) throws Exception{
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("uri is required");
        }
        RequestBuilder requestBuilder = RequestBuilder.get();
        requestBuilder.setUri(uri);
        if (parameters != null && !parameters.isEmpty()) {
            for (final String key : parameters.keySet()) {
                if (parameters.get(key) != null) {
                    requestBuilder.addParameter(key, String.valueOf(parameters.get(key)));
                }
            }
        }
        requestBuilder.setConfig(defaultRequestConfig);
        if (headers != null && !headers.isEmpty()) {
            for (final String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        return parseRequest(HttpPool.getClient(), requestBuilder.build());
    }

    public HttpResult post(String uri) throws Exception{
        return post(uri, null, null, null);
    }

    public HttpResult post(String uri, Map<String, Object> parameters) throws Exception{
        return post(uri, null, parameters, null);
    }

    public HttpResult post(String uri, byte[] body) throws Exception{
        return post(uri, null, null, body);
    }

    public HttpResult post(String uri, Map<String, Object> parameters, byte[] body) throws Exception{
        return post(uri, null, parameters, body);
    }

    public HttpResult post(String uri, Map<String, String> headers, Map<String, Object> parameters, byte[] body) throws Exception{
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("uri is required");
        }

        final RequestBuilder requestBuilder = RequestBuilder.post();
        requestBuilder.setUri(uri);

        // Populate request parameters
        if (parameters != null && !parameters.isEmpty()) {
            for (final String key : parameters.keySet()) {
                if (parameters.get(key) != null) {
                    requestBuilder.addParameter(key, String.valueOf(parameters.get(key)));
                }
            }
        }

        // Populate request body
        if (body != null) {
            requestBuilder.setEntity(new ByteArrayEntity(body));
        }

        // Request configuration can be overridden at the request level.
        // They will take precedence over the one set at the client level.
        requestBuilder.setConfig(defaultRequestConfig);

        // Set custom header
        if (headers != null && !headers.isEmpty()) {
            for (final String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }

        return parseRequest(HttpPool.getClient(), requestBuilder.build());
    }

    public String  post(String weserviceUrl,String weserviceMethod, Map<String, String> headers, Map<String, Object> parameters, byte[] body) throws Exception{
        JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();
        //  weserviceUrl  =  "http://localhost:8080/webservice/test/user?wsdl"
        org.apache.cxf.endpoint.Client client =dcf.createClient(weserviceUrl);
        //getUser 为接口中定义的方法名称  张三为传递的参数   返回一个Object数组
        Object[] objects=client.invoke(weserviceMethod ,parameters);
        return objects[0].toString() ;
    }


    // --------------------------- Start: PUT Manual---------------------------
    public HttpResult put(String uri) throws Exception{
        return put(uri, null, null, null);
    }

    public HttpResult put(String uri, Map<String, Object> parameters) throws Exception{
        return put(uri, null, parameters, null);
    }

    public HttpResult put(String uri, byte[] body) throws Exception{
        return put(uri, null, null, body);
    }

    public HttpResult put(String uri, Map<String, Object> parameters, byte[] body) throws Exception{
        return put(uri, null, parameters, body);
    }

    public HttpResult put(String uri, Map<String, String> headers, Map<String, Object> parameters, byte[] body) throws Exception{
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("uri is required");
        }
        final RequestBuilder requestBuilder = RequestBuilder.put();
        requestBuilder.setUri(uri);
        // Populate request parameters
        if (parameters != null && !parameters.isEmpty()) {
            for (final String key : parameters.keySet()) {
                if (parameters.get(key) != null) {
                    requestBuilder.addParameter(key, String.valueOf(parameters.get(key)));
                }
            }
        }
        // Populate request body
        if (body != null) {
            requestBuilder.setEntity(new ByteArrayEntity(body));
        }
        // Request configuration can be overridden at the request level.
        // They will take precedence over the one set at the client level.
        requestBuilder.setConfig(defaultRequestConfig);
        // Set custom header
        if (headers != null && !headers.isEmpty()) {
            for (final String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        return parseRequest(HttpPool.getClient(), requestBuilder.build());
    }
    // --------------------------- End: PUT Manual ---------------------------

    // --------------------------- Start: DELETE Manual ---------------------------
    public HttpResult delete(String uri) throws Exception{
        return delete(uri, null);
    }

    public HttpResult delete(String uri, Map<String, String> headers) throws Exception{
        return delete(uri, headers, null);
    }

    public HttpResult delete(String uri, Map<String, String> headers, Map<String, Object> parameters) throws Exception{
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("uri is required");
        }
        RequestBuilder requestBuilder = RequestBuilder.delete();
        requestBuilder.setUri(uri);
        if (parameters != null && !parameters.isEmpty()) {
            for (final String key : parameters.keySet()) {
                if (parameters.get(key) != null) {
                    requestBuilder.addParameter(key, String.valueOf(parameters.get(key)));
                }
            }
        }
        requestBuilder.setConfig(defaultRequestConfig);
        if (headers != null && !headers.isEmpty()) {
            for (final String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        return parseRequest(HttpPool.getClient(), requestBuilder.build());
    }

    // --------------------------- Start: DELETE Manual ---------------------------

    /**
     * Check status of the given resource.
     * @param uri the uri of the resource
     * @return the status of the resource
     */
    public HttpResult head(String uri) throws Exception{
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("uri is required");
        }
        final RequestBuilder requestBuilder = RequestBuilder.head();
        requestBuilder.setUri(uri);
        return parseRequest(HttpPool.getClient(), requestBuilder.build());
    }

    private HttpResult parseRequest(CloseableHttpClient httpClient, HttpUriRequest request) throws Exception{
        logger.debug("Executing request " + request.getURI());

            final CloseableHttpResponse response = httpClient.execute(request);

            ProtocolVersion protocolVersion = response.getProtocolVersion();
            int code = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();

            final HttpResult result = new HttpResult(protocolVersion, code, reasonPhrase);
            logger.trace("----------------------------------------");
            logger.trace("{}", response.getStatusLine());
            populate(response, result); // 将报文内容加入到result中
            logger.trace("----------------------------------------");
            response.close();

            return result;

    }

    private void populate(final CloseableHttpResponse response, final HttpResult result) throws IOException {

        Header[] headers = response.getAllHeaders();
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                result.addHeader(header.getName(), header.getValue());
            }
        }

        final HttpEntity entity = response.getEntity();
        if (entity != null) {

            logger.trace("Response Content-Length: {}", entity.getContentLength());
            final ContentType contentType = ContentType.get(entity);

            if (contentType != null) {
                result.setContentType(contentType.getMimeType());
                Charset charset = contentType.getCharset();
                if (charset == null) {
                    charset = HTTP.DEF_CONTENT_CHARSET;
                }
                result.setCharset(charset);
            }
            result.setContentLength(entity.getContentLength());
            result.setResponseBody(EntityUtils.toByteArray(entity));
            EntityUtils.consume(entity);// 保证内容完全被消费掉，如果流存在则会被close
        }
    }
}
