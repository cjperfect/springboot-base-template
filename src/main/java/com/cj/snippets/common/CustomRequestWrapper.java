package com.cj.snippets.common;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

// 为了解决request.getInputStream()只能获取一次的问题, sysLogAnnotation里面需要获取body内容, 和controller里面获取body, 就会获取两次
// 因此需要这样处理一下, 就能够获取到两次body
public class CustomRequestWrapper extends HttpServletRequestWrapper {
    private final String body;

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder sb = new StringBuilder(128);
        BufferedReader br = null;
        try {
            InputStream is = request.getInputStream();
            if (is != null) {
                //br = new BufferedReader(new InputStreamReader(is));
                /**
                 * 当中文乱码时
                 * tomcat配置中加
                 * -Dfile.encoding=UTF-8
                 */
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                char[] charBuffer = new char[128];
                int byteRead = -1;
                while ((byteRead = br.read(charBuffer)) > 0) {
                    sb.append(charBuffer, 0, byteRead);
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }
        body = sb.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return body;
    }
}
