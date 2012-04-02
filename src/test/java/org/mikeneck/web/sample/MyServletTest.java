package org.mikeneck.web.sample;

import org.junit.Before;
import org.junit.Test;
import org.mikeneck.web.sample.mock.MockRequest;
import org.mikeneck.web.sample.mock.MockResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author: mike
 * @since: 12/04/02
 */
public class MyServletTest {

    private String contentType;

    private String encoding;

    private boolean isFlushBufferCalled;

    private StringWriter writer = null;

    @Before
    public void setUp () {
        this.contentType = null;
        this.encoding = null;
        this.isFlushBufferCalled = false;
        this.writer = new StringWriter();
    }

    @Test
    public void test() {
        MyServlet servlet = new MyServlet();
        MockRequest request = new MockRequest();
        Response response = new Response();

        servlet.doGet(request, response);

        assertThat(contentType, is("text/html"));
        assertThat(encoding, is("UTF-8"));
        assertThat(writer.toString().contains("Hello"), is(true));
        assertThat(isFlushBufferCalled, is(true));
    }

    class Response extends MockResponse {
        @Override
        public void setContentType(String type) {
            contentType = type;
        }

        @Override
        public void setCharacterEncoding(String charset) {
            encoding = charset;
        }

        @Override
        public void flushBuffer() throws IOException {
            writer.flush();
            isFlushBufferCalled = true;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(writer);
        }
    }
}
