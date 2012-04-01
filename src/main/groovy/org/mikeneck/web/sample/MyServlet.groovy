package org.mikeneck.web.sample

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import groovy.xml.MarkupBuilder

/**
 * 
 * @author: mike
 * @since: 12/04/02
 */
class MyServlet extends HttpServlet {
    private static final String CONTENT_TYPE = 'text/html'
    private static final String ENCODING = 'UTF-8'

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding(ENCODING)
        resp.setContentType(CONTENT_TYPE)
        def writer = resp.getWriter()
        writer << '<!DOCTYPE html>'
        def doc = new MarkupBuilder(writer)
        doc.html(lang : 'ja') {
            head {
                meta ("http-equiv" : 'Content-Type', content : "$CONTENT_TYPE; charset=$ENCODING")
                title ('sample page')
            }
            body {
                h1 ('Hello, World!')
            }
        }
        resp.flushBuffer()
    }
}
