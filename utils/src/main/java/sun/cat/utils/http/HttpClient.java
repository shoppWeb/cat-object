package sun.cat.utils.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * shl
 * HTTP工具类
 *
 * @author ming
 * @version 1.0.0
 */
public class HttpClient {

    /**
     * 请求方式
     */
    public static enum METHOD {

        /**
         * GET请求
         */
        GET,
        /**
         * POST请求
         */
        POST;
    }
    /**
     * 默认使用Method
     */
    private static final METHOD DEFAULT_METHOD = METHOD.GET;
    /**
     * 默认编码，字符串"UTF-8"
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 默认超时，30秒
     */
    private static final int DEFAULT_TIMEOUT = 30 * 1000;
    /**
     * 缓存大小，整数512
     */
    private static final int BUFF_SIZE = 4096;
    /**
     * HTTP请求ID生成器
     */
    private static int HttpClientRequestCounter = 0;
    private static final String COOKIE = "Cookie";
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String SECURE = "secure";
    private static final String VERSION = "version";
    private static final String COMMENT = "comment";
    private static final String DOMAIN = "domain";
    private static final String PATH = "path";
    private static final String MAX_AGE = "max-age";
    private static final String EXPIRES = "expires";
    private static final String[] DATE_FORMATS = {
            "EEE, dd MMM yyyy HH:mm:ss zzz",
            "EEEE, dd-MMM-yy HH:mm:ss zzz",
            "EEE MMM d HH:mm:ss yyyy",
            "EEE, dd-MMM-yyyy HH:mm:ss z",
            "EEE, dd-MMM-yyyy HH-mm-ss z",
            "EEE, dd MMM yy HH:mm:ss z",
            "EEE dd-MMM-yyyy HH:mm:ss z",
            "EEE dd MMM yyyy HH:mm:ss z",
            "EEE dd-MMM-yyyy HH-mm-ss z",
            "EEE dd-MMM-yy HH:mm:ss z",
            "EEE dd MMM yy HH:mm:ss z",
            "EEE,dd-MMM-yy HH:mm:ss z",
            "EEE,dd-MMM-yyyy HH:mm:ss z",
            "EEE, dd-MM-yyyy HH:mm:ss z"};

    /**
     * HTTP客户端Cookie对象
     */
    public static class Cookie {

        private static final long TS_EXPIRE = 0;
        /**
         * 默认的版本
         */
        public static final int DEFUALT_VERSION = 0;
        /**
         * 默认的超时(SESSION)
         */
        public static final int DEFUALT_MAX_AGE = -1;
        /**
         * Cookie名称
         */
        public String name;
        /**
         * Cookie值
         */
        public String value;
        /**
         * Cookie安全，用于HTTPS
         */
        public boolean secure = false;
        /**
         * Cookie版本
         */
        public int version = DEFUALT_VERSION;
        /**
         * Cookie注释
         */
        public String comment = null;
        /**
         * Cookie域
         */
        public String domain = null;
        /**
         * Cookie路径
         */
        public String path = null;
        /**
         * Cookie过期时间戳
         */
        public long timeStamp = TS_EXPIRE;

        /**
         * 构造一个Cookie
         *
         * @param name 名称
         * @param value 值
         */
        public Cookie(String name, String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 判断Cookie的域是否满足匹配
         *
         * @param domain 目标域
         * @return 是否满足匹配
         */
        public boolean matchDomain(String domain) {
            if (this.domain == null) {
                return true;
            } else if (domain.indexOf(this.domain) >= 0) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 判断Cookie的路径是否满足匹配
         *
         * @param path 目标路径
         * @return 是否满足匹配
         */
        public boolean matchPath(String path) {
            if (this.path == null) {
                return true;
            } else if (path.startsWith(this.path)) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * HTTP头域：Accept
     */
    public String accept;
    /**
     * HTTP头域：Accept-Charset，会影响未知编码类型的相应的编码
     */
    public String acceptCharset;
    /**
     * HTTP头域：Accept-Encoding
     */
    public String acceptEncoding;
    /**
     * HTTP头域：Accept-Language
     */
    public String acceptLanguage;
    /**
     * HTTP头域：Charset，会影响请求的数据编码
     */
    public String charset;
    /**
     * HTTP头域：Host
     */
    public String host;
    /**
     * HTTP头域：Referer
     */
    public String referer;
    /**
     * HTTP头域：User-Agent
     */
    public String userAgent;
    /**
     * HTTP客户端Cookie容器
     */
    public final ArrayList<Cookie> cookies = new ArrayList<Cookie>();
    /**
     * 用户可设置默认Method
     */
    public METHOD defaultMethod = DEFAULT_METHOD;
    /**
     * 超时时间，单位：毫秒
     */
    public int timeout = DEFAULT_TIMEOUT;

    /**
     * 发送GET请求
     *
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据
     * @return 响应
     */
    public HttpClientResponse get(String urlStr, HashMap<String, String> paramMap) {
        return send(urlStr, METHOD.GET, paramMap);
    }

    /**
     * 发送POST请求
     *
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据
     * @return 响应
     */
    public HttpClientResponse post(String urlStr, HashMap<String, String> paramMap) {
        return send(urlStr, METHOD.POST, paramMap);
    }

    /**
     * 发送请求
     *
     * @param urlStr 请求url字符串
     * @param method 请求方式
     * @param paramMap 参数数据
     * @return 响应
     */
    public HttpClientResponse send(String urlStr, METHOD method, HashMap<String, String> paramMap) {
        HttpClientRequest request = new HttpClientRequest(urlStr, method, paramMap);
        try {
            return request.initRequest().getResponse();
        } catch (Exception ex) {
            return new HttpClientResponse(ex);
        }
    }

    /**
     * Multipart的POST提交
     *
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据(Object可为String或File)
     * @return 响应
     */
    public HttpClientResponse multipartPost(String urlStr, HashMap<String, Object> paramMap) {
        HttpClientRequest request = new HttpClientRequest(urlStr, paramMap);
        try {
            return request.initRequest().getResponse();
        } catch (Exception ex) {
            return new HttpClientResponse(ex);
        }
    }

    /**
     * 下载文件
     *
     * @param urlStr URL字符串
     * @param path 保存路径（如果结尾为"/"或"\"则文件名为对应URL文件）
     * @return 文件对象
     */
    public File download(String urlStr, String path) {
        return download(urlStr, path, METHOD.GET, null);
    }

    /**
     * 下载文件
     *
     * @param urlStr URL字符串
     * @param path 保存路径（如果结尾为"/"或"\"则文件名为对应URL文件）
     * @param method 请求方式
     * @param paramMap 请求参数
     * @return 文件对象
     */
    public File download(String urlStr, String path, METHOD method, HashMap<String, String> paramMap) {
        if (urlStr == null || path == null) {
            return null;
        }
        path = path.replace('\\', '/');
        int index = path.lastIndexOf('/');
        String fileName = path.substring(index + 1);
        if ("".equals(fileName)) {
            int ui = urlStr.lastIndexOf('/');
            fileName = urlStr.substring(ui >= 0 ? ui + 1 : 0);
            if (fileName.equals("")) {
                fileName = "index.html";
            }
        }
        String dirPath = path.substring(0, index + 1);
        File dir = new File(dirPath);
        if (!dir.exists() && !dir.mkdirs()) {
            return null;//创建目录失败
        }
        File file = new File(dirPath + fileName);
        HttpClientRequest request = new HttpClientRequest(urlStr, method, paramMap);
        try {
            InputStream is = request.initRequest().conn.getInputStream();
            FileOutputStream os = new FileOutputStream(file);
            byte[] buff = new byte[BUFF_SIZE];
            int readCnt = 0;
            while ((readCnt = is.read(buff)) != -1) {
                os.write(buff, 0, readCnt);
            }
            is.close();
            os.close();
            return file;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 异步发送GET请求
     *
     * @param listener 回调监听器
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据
     * @return 请求
     */
    public HttpClientRequest doGet(HttpClientListener listener, String urlStr, HashMap<String, String> paramMap) {
        return doSend(listener, urlStr, METHOD.GET, paramMap);
    }

    /**
     * 异步发送POST请求
     *
     * @param listener 回调监听器
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据
     * @return 请求
     */
    public HttpClientRequest doPost(HttpClientListener listener, String urlStr, HashMap<String, String> paramMap) {
        return doSend(listener, urlStr, METHOD.POST, paramMap);
    }

    /**
     * 异步发送请求
     *
     * @param listener 回调监听器
     * @param urlStr 请求url字符串
     * @param method 请求方式
     * @param paramMap 参数数据
     * @return 请求
     */
    public HttpClientRequest doSend(HttpClientListener listener, String urlStr, METHOD method, HashMap<String, String> paramMap) {
        return (new HttpClientRequest(urlStr, method, paramMap, listener)).send();
    }

    /**
     * Multipart的POST提交
     *
     * @param listener 回调监听器
     * @param urlStr 请求url字符串
     * @param paramMap 参数数据(Object可为String或File)
     * @return 请求
     */
    public HttpClientRequest doMultipartPost(HttpClientListener listener, String urlStr, HashMap<String, Object> paramMap) {
        return (new HttpClientRequest(urlStr, paramMap, listener)).send();
    }

    /**
     * 处理返回客户端的Cookie
     *
     * @param headerFields 响应头信息
     */
    private void solveSetCookie(Map<String, List<String>> headerFields) {
        if (headerFields == null || cookies == null) {
            return;
        }
        synchronized (cookies) {
            List<String> values = headerFields.get(SET_COOKIE);
            if (values == null) {
                return;
            }
            for (String v : values) {
                String[] fields = v.split(";");
                String[] cookieStrs = fields[0].split("=");
                Cookie cookie;
                String name = cookieStrs[0];
                if (cookieStrs.length > 1) {
                    String val = cookieStrs[1];
                    if (val != null && val.startsWith("\"") && val.endsWith("\"")) {
                        val = val.substring(1, val.length() - 1);
                    }
                    cookie = new Cookie(name, val);
                } else {
                    cookie = new Cookie(name, null);
                }
                for (int i = 1; i < fields.length; i++) {
                    String[] header = fields[i].split("=");
                    String headerName = header[0].trim().toLowerCase();
                    String headerValue = header.length > 1 ? header[1].trim() : "";
                    if (headerValue != null && headerValue.startsWith("\"") && headerValue.endsWith("\"")) {
                        headerValue = headerValue.substring(1, headerValue.length() - 1);
                    }
                    if (SECURE.equals(headerName)) {
                        cookie.secure = true;
                    } else if (VERSION.equals(headerName)) {
                        try {
                            cookie.version = Integer.parseInt(headerValue);
                        } catch (Exception ex) {
                            cookie.version = Cookie.DEFUALT_VERSION;
                        }
                    } else if (COMMENT.equals(headerName)) {
                        cookie.comment = headerValue;
                    } else if (DOMAIN.equals(headerName)) {
                        cookie.domain = headerValue;
                    } else if (PATH.equals(headerName)) {
                        cookie.path = headerValue;
                    } else if (MAX_AGE.equals(headerName)) {
                        int maxAge;
                        try {
                            maxAge = Integer.parseInt(headerValue);
                        } catch (Exception ex) {
                            maxAge = Cookie.DEFUALT_MAX_AGE;
                        }
                        cookie.timeStamp = System.currentTimeMillis() + maxAge * 1000;
                    } else if (EXPIRES.equals(headerName)) {
                        for (int j = 0; j < DATE_FORMATS.length; j++) {
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATS[j], Locale.US);
                                Date date = dateFormat.parse(headerValue);
                                cookie.timeStamp = date.getTime();
                                break;
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
                for (int i = 0; i < cookies.size(); i++) {
                    if (cookies.get(i).name.equals(cookie.name)) {
                        cookies.remove(i);
                        break;
                    }
                }
                if (cookie.timeStamp == Cookie.TS_EXPIRE || cookie.timeStamp >= System.currentTimeMillis()) {
                    cookies.add(cookie);
                }
            }
        }
    }

    /**
     * 设置Cookie到URL连接
     *
     * @param url URL连接
     */
    private void solveCookie(HttpURLConnection url) {
        if (url == null || cookies == null) {
            return;
        }
        synchronized (cookies) {
            String domain = url.getURL().getHost();
            String path = url.getURL().getPath();
            StringBuilder sb = null;
            Iterator<Cookie> iterator = cookies.iterator();
            while (iterator.hasNext()) {
                Cookie c = iterator.next();
                if (c.timeStamp != Cookie.TS_EXPIRE && c.timeStamp < System.currentTimeMillis()) {
                    iterator.remove();
                    continue;
                }
                if (!c.matchDomain(domain)) {
                    continue;
                }
                if (!c.matchPath(path)) {
                    continue;
                }
                if (sb == null) {
                    sb = new StringBuilder(c.name + "=" + c.value);
                } else {
                    sb.append(";").append(c.name).append("=").append(c.value);
                }
            }
            if (sb != null) {
                url.setRequestProperty(COOKIE, sb.toString());
            }
        }
    }

    /**
     * HTTP客户端回调监听器
     */
    public static interface HttpClientListener {

        /**
         * 正常数据返回
         *
         * @param request HTTP请求
         * @param data 数据文本
         */
        public void onData(HttpClientRequest request, String data);

        /**
         * 错误数据返回
         *
         * @param request HTTP请求
         * @param code 错误代码
         */
        public void onError(HttpClientRequest request, int code);

        /**
         * 发生Exception错误返回
         *
         * @param ex 错误对象
         */
        public void onException(Exception ex);
    }

    /**
     * HTTP请求
     */
    public class HttpClientRequest extends Thread {

        /**
         * 请求ID
         */
        public long id;
        /**
         * 请求方式
         */
        public METHOD method;
        /**
         * 请求URL字符串
         */
        public String urlStr;
        /**
         * 请求参数
         */
        public HashMap paramMap;
        /**
         * 请求URL对象
         */
        public URL url;
        /**
         * 请求HTTP连接
         */
        public HttpURLConnection conn;
        /**
         * 回调监听器
         */
        private HttpClientListener listener;
        /**
         * 是否为multipart格式请求
         */
        private boolean multipart;

        /**
         * 构造HTTP请求（无回调）
         *
         * @param urlStr URL连接字符串
         * @param method 请求方式
         * @param paramMap 请求参数
         */
        public HttpClientRequest(String urlStr, METHOD method, HashMap<String, String> paramMap) {
            this.id = HttpClient.HttpClientRequestCounter++;
            this.urlStr = urlStr;
            this.method = method;
            this.paramMap = paramMap;
            this.listener = null;
            multipart = false;
        }

        /**
         * 构造HTTP请求（有回调）
         *
         * @param urlStr URL连接字符串
         * @param method 请求方式
         * @param paramMap 请求参数
         * @param listener 回调监听器
         */
        public HttpClientRequest(String urlStr, METHOD method, HashMap<String, String> paramMap, HttpClientListener listener) {
            this.id = HttpClient.HttpClientRequestCounter++;
            this.urlStr = urlStr;
            this.method = method;
            this.paramMap = paramMap;
            this.listener = listener;
            multipart = false;
        }

        /**
         * 构造HTTP请求（有回调）
         *
         * @param urlStr URL连接字符串
         * @param paramMap 请求参数
         */
        public HttpClientRequest(String urlStr, HashMap<String, Object> paramMap) {
            this.id = HttpClient.HttpClientRequestCounter++;
            this.urlStr = urlStr;
            this.method = METHOD.POST;
            this.paramMap = paramMap;
            this.listener = null;
            multipart = true;
        }

        /**
         * 构造HTTP请求（有回调）
         *
         * @param urlStr URL连接字符串
         * @param paramMap 请求参数
         * @param listener 回调监听器
         */
        public HttpClientRequest(String urlStr, HashMap<String, Object> paramMap, HttpClientListener listener) {
            this.id = HttpClient.HttpClientRequestCounter++;
            this.urlStr = urlStr;
            this.method = METHOD.POST;
            this.paramMap = paramMap;
            this.listener = listener;
            multipart = true;
        }

        @Override
        public void run() {
            HttpClientResponse response;
            try {
                initRequest();
                response = getResponse();
            } catch (Exception ex) {
                response = new HttpClientResponse(ex);
            }
            if (listener != null) {
                if (response.ex == null) {
                    if (response.code == HttpClientResponse.CODE_OK) {
                        listener.onData(this, response.text);
                    } else {
                        listener.onError(this, response.code);
                    }
                } else {
                    listener.onException(response.ex);
                }
            }
        }

        /**
         * 异步发送请求
         *
         * @return 类本身
         */
        private HttpClientRequest send() {
            this.start();
            return this;
        }

        /**
         * 初始化请求，会打开URL连接
         *
         * @return 类本身
         */
        private HttpClientRequest initRequest() throws Exception {
            String useCharset = charset == null ? DEFAULT_CHARSET : charset;
            //修正Method
            method = method == null ? defaultMethod : method;
            method = method == null ? DEFAULT_METHOD : method;
            //创建URL
            if (method == METHOD.GET) {//GET方式
                String data = buildParamData(paramMap, useCharset);
                if ("".equals(data)) {
                    url = new URL(urlStr);
                } else {
                    if (urlStr.indexOf("?") >= 0) {
                        url = new URL(urlStr + "&" + data);
                    } else {
                        url = new URL(urlStr + "?" + data);
                    }
                }
            } else {//POST方式
                url = new URL(urlStr);
            }
            //创建连接
            conn = (HttpURLConnection) url.openConnection();
            if (timeout > 0) {
                conn.setConnectTimeout(timeout);
                conn.setReadTimeout(timeout);
            }
            conn.setDoOutput(METHOD.POST == method);
            conn.setUseCaches(false);
            conn.setRequestMethod(method.name());
            //设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            if (accept != null) {
                conn.setRequestProperty("Accept", accept);
            }
            if (acceptCharset != null) {
                conn.setRequestProperty("Accept-Charset", acceptCharset);
            }
            if (acceptEncoding != null) {
                conn.setRequestProperty("Accept-Encoding", acceptEncoding);
            }
            if (acceptLanguage != null) {
                conn.setRequestProperty("Accept-Language", acceptLanguage);
            }
            if (charset != null) {
                conn.setRequestProperty("Charset", charset);
            }
            if (host != null) {
                conn.setRequestProperty("Host", host);
            }
            if (referer != null) {
                conn.setRequestProperty("Referer", referer);
            }
            if (userAgent != null) {
                conn.setRequestProperty("User-Agent", userAgent);
            }
            //写Cookie到请求头信息
            solveCookie(conn);
            //写请求数据
            if (METHOD.POST == method) {
                if (multipart) {
                    StringBuilder sbBoundary = new StringBuilder("----NutHttpClientFormBoundary");
                    sbBoundary.append(Long.toHexString(System.currentTimeMillis()));
                    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + sbBoundary.toString());
                    byte[] startBoundary = sbBoundary.insert(0, "--").toString().getBytes();
                    byte[] endBoundary = sbBoundary.append("--").toString().getBytes();
                    byte[] fileContentType = "Content-Type: application/octet-stream".getBytes();
                    byte[] lineBreak = "\r\n".getBytes();
                    int disposotionLen1 = "Content-Disposition: form-data; name=\"\"".getBytes().length;
                    int disposotionLen2 = "Content-Disposition: form-data; name=\"\"; filename=\"\"".getBytes().length;
                    int contentLength = 0;
                    for (Object name : paramMap.keySet()) {
                        Object data = paramMap.get(name);
                        if (data instanceof String) {
                            String str = (String) data;
                            contentLength += startBoundary.length + lineBreak.length
                                    + disposotionLen1 + name.toString().getBytes(useCharset).length + lineBreak.length + lineBreak.length
                                    + str.getBytes(useCharset).length + lineBreak.length;
                        } else if (data instanceof File) {
                            File file = (File) data;
                            contentLength += startBoundary.length + lineBreak.length
                                    + disposotionLen2 + name.toString().getBytes(useCharset).length + file.getName().getBytes(useCharset).length + lineBreak.length
                                    + fileContentType.length + lineBreak.length + lineBreak.length
                                    + file.length() + lineBreak.length;
                        } else if (data instanceof byte[]) {
                            byte[] bytes = (byte[]) data;
                            contentLength += startBoundary.length + lineBreak.length
                                    + disposotionLen1 + name.toString().getBytes(useCharset).length + lineBreak.length + lineBreak.length
                                    + bytes.length + lineBreak.length;
                        } else {
                            continue;
                        }
                    }
                    contentLength += endBoundary.length + lineBreak.length;
                    conn.setRequestProperty("Content-Length", Integer.toString(contentLength));
                    OutputStream os = conn.getOutputStream();
                    for (Object name : paramMap.keySet()) {
                        Object data = paramMap.get(name);
                        if (data instanceof String) {
                            String str = (String) data;
                            //write boundary
                            os.write(startBoundary);
                            os.write(lineBreak);
                            //write header
                            String disposition = "Content-Disposition: form-data; name=\"" + name.toString() + "\"";
                            os.write(disposition.getBytes(useCharset));
                            os.write(lineBreak);
                            os.write(lineBreak);
                            //write content
                            os.write(str.getBytes(useCharset));
                            os.write(lineBreak);
                        } else if (data instanceof File) {
                            File file = (File) data;
                            //write boundary
                            os.write(startBoundary);
                            os.write(lineBreak);
                            //write content-disposition
                            String disposition = "Content-Disposition: form-data; name=\"" + name.toString() + "\"; filename=\"" + file.getName() + "\"";
                            os.write(disposition.getBytes(useCharset));
                            os.write(lineBreak);
                            os.write(fileContentType);
                            os.write(lineBreak);
                            os.write(lineBreak);
                            //write content-type
                            FileInputStream is = new FileInputStream(file);
                            byte[] fileBuff = new byte[BUFF_SIZE];
                            int readCnt = 0;
                            while ((readCnt = is.read(fileBuff)) != -1) {
                                os.write(fileBuff, 0, readCnt);
                            }
                            is.close();
                            os.write(lineBreak);
                        } else if (data instanceof byte[]) {
                            byte[] bytes = (byte[]) data;
                            //write boundary
                            os.write(startBoundary);
                            os.write(lineBreak);
                            //write header
                            String disposition = "Content-Disposition: form-data; name=\"" + name.toString() + "\"";
                            os.write(disposition.getBytes(useCharset));
                            os.write(lineBreak);
                            os.write(lineBreak);
                            //write content
                            os.write(bytes);
                            os.write(lineBreak);
                        } else {
                            continue;
                        }
                    }
                    os.write(endBoundary);
                    os.write(lineBreak);
                    os.close();
                } else {
                    String data = buildParamData(paramMap, useCharset);
                    byte[] requestData = data.getBytes(useCharset);
                    conn.setRequestProperty("Content-Length", Integer.toString(requestData.length));
                    OutputStream os = conn.getOutputStream();
                    os.write(requestData);
                    os.close();
                }
            }
            return this;
        }

        /**
         * 发送请求获取响应，会关闭URL连接
         *
         * @return 响应
         */
        private HttpClientResponse getResponse() throws Exception {
            //获取响应
            HttpClientResponse response;
            int rtnCode = conn.getResponseCode();
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            solveSetCookie(headerFields);//从响应获取Cookie
            if (rtnCode == HttpClientResponse.CODE_OK) {
                BufferedReader in;
                //获取返回响应的编码
                String contentType = conn.getHeaderField("Content-Type");
                String responseCharset = null;
                if (contentType != null) {
                    String[] fields = contentType.split(";");
                    for (String field : fields) {
                        String[] pair = field.trim().split("=");
                        if (pair.length >= 2) {
                            if ("charset".equals(pair[0].trim().toLowerCase())) {
                                responseCharset = pair[1].trim();
                                break;
                            }
                        }
                    }
                }
                String contentEncoding = conn.getHeaderField("Content-Encoding");
                boolean gzip = contentEncoding != null && contentEncoding.toLowerCase().equals("gzip");
                if (responseCharset == null) {
                    responseCharset = acceptCharset;
                }
                if (responseCharset != null) {
                    in = new BufferedReader(new InputStreamReader((gzip ? (new GZIPInputStream(conn.getInputStream())) : conn.getInputStream()), responseCharset));
                } else {
                    in = new BufferedReader(new InputStreamReader((gzip ? (new GZIPInputStream(conn.getInputStream())) : conn.getInputStream())));
                }
                int length;
                char[] buff = new char[BUFF_SIZE];
                StringBuilder responseSb = new StringBuilder();
                while ((length = in.read(buff)) != -1) {
                    responseSb.append(new String(buff, 0, length));
                }
                in.close();
                response = new HttpClientResponse(rtnCode, headerFields, responseSb.toString());
            } else {
                response = new HttpClientResponse(rtnCode, headerFields, null);
            }
            //关闭连接
            conn.disconnect();
            return response;
        }
    }

    /**
     * HTTP响应对象类
     */
    public class HttpClientResponse {

        /**
         * 发生Exception时的HTTP响应编码
         */
        public static final int CODE_EXPCETION = -1;
        /**
         * HTTP成功OK的响应编码
         */
        public static final int CODE_OK = 200;
        /**
         * HTTP响应编码
         */
        public int code;
        /**
         * HTTP相应头
         */
        public Map<String, List<String>> headerFields;
        /**
         * HTTP响应内容文本
         */
        public String text;
        /**
         * HTTP错误对象，如果没有错误为null
         */
        public Exception ex;

        /**
         * 正常的HttpResponse构造函数
         *
         * @param code 返回代码
         * @param headerFields 头信息
         * @param text 内容文本
         */
        public HttpClientResponse(int code, Map<String, List<String>> headerFields, String text) {
            this.code = code;
            this.headerFields = headerFields;
            this.text = text;
            this.ex = null;
        }

        /**
         * 发生错误的HttpResponse构造函数
         *
         * @param ex 错误对象
         */
        public HttpClientResponse(Exception ex) {
            this.code = CODE_EXPCETION;
            this.text = null;
            this.ex = ex;
        }

        /**
         * 将响应返回状态码转换为描述
         *
         * @return 状态描述
         */
        public String getStatus() {
            switch (code) {
                //Error
                case -1:
                    return "Exception";
                //100+
                case 100:
                    return "Continue";
                case 101:
                    return "Switching Protocols";
                //200+
                case 200:
                    return "OK";
                case 201:
                    return "Created";
                case 202:
                    return "Accepted";
                case 203:
                    return "Non-Authoritative Information";
                case 204:
                    return "No Content";
                case 205:
                    return "Reset Content";
                case 206:
                    return "Partial Content";
                //300+
                case 300:
                    return "Multiple Choices";
                case 301:
                    return "Moved Permanently";
                case 302:
                    return "Found";
                case 303:
                    return "See Other";
                case 304:
                    return "Not Modified";
                case 305:
                    return "Use Proxy";
                case 307:
                    return "Temporary Redirect";
                //400+
                case 400:
                    return "Bad Request";
                case 401:
                    return "Unauthorized";
                case 402:
                    return "Payment Required";
                case 403:
                    return "Forbidden";
                case 404:
                    return "Not Found";
                case 405:
                    return "Method Not Allowed";
                case 406:
                    return "Not Acceptable";
                case 407:
                    return "Proxy Authentication Required";
                case 408:
                    return "Request Time-out";
                case 409:
                    return "Conflict";
                case 410:
                    return "Gone";
                case 411:
                    return "Length Required";
                case 412:
                    return "Precondition Failed";
                case 413:
                    return "Request Entity Too Large";
                case 414:
                    return "Request-URI Too Large";
                case 415:
                    return "Unsupported Media Type";
                case 416:
                    return "Requested range not satisfiable";
                case 417:
                    return "Expectation Failed";
                //500+
                case 500:
                    return "Internal Server Error";
                case 501:
                    return "Not Implemented";
                case 502:
                    return "Bad Gateway";
                case 503:
                    return "Service Unavailable";
                case 504:
                    return "Gateway Time-out";
                case 505:
                    return "HTTP Version not supported";
                //Unknow
                default:
                    return "Unknow";
            }
        }
    }

    /**
     * 生成参数数据字符串
     *
     * @param paramMap 参数HashMap
     * @param charset 字符集
     * @return 参数数据字符串
     */
    private static String buildParamData(HashMap<String, String> paramMap, String charset) {
        StringBuilder dataBuilder = new StringBuilder();
        if (paramMap != null) {
            int paramCnt = 0;
            for (String key : paramMap.keySet()) {
                if (paramCnt > 0) {
                    dataBuilder.append("&");
                }
                String value = paramMap.get(key);
                dataBuilder.append(URLEncode(key, charset)).append("=").append(URLEncode(value, charset));
                paramCnt++;
            }
        }
        return dataBuilder.toString();
    }

    /**
     * URL编码函数
     *
     * @param str 需要编码字符串
     * @param charset 字符串字符集
     * @return 编码后字符串
     */
    public static String URLEncode(String str, String charset) {
        try {
            return (str == null) ? "" : java.net.URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * URL解码函数
     *
     * @param str 需要解码字符串
     * @param charset 字符串字符集
     * @return 解码后字符串
     */
    public static String URLDecode(String str, String charset) {
        try {
            return (str == null) ? "" : java.net.URLDecoder.decode(str, charset);
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * 获取HTTP的URL
     *
     * @param httpUrl 参照URL
     * @param url URL字符串
     * @return 获取到的URL，如果本身就是HTTP的URL或获取失败返回原URL参数
     */
    public static String getRefUrlStr(String httpUrl, String url) {
        httpUrl = httpUrl.trim().toLowerCase();
        url = url.trim().toLowerCase();
        if (url.startsWith("http://")) {
            return url;
        }
        if (httpUrl.startsWith("http://")) {
            if (url.startsWith("/")) {//绝对路径
                int endIndex = httpUrl.indexOf('/', "http://".length());
                String prefix = httpUrl.substring(0, endIndex);
                return prefix + url;
            } else {//相对路径
                int endIndex = httpUrl.lastIndexOf('/');
                String prefix = httpUrl.substring(0, endIndex + 1);
                return prefix + url;
            }
        }
        return url;
    }


}
