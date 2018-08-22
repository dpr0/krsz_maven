package servlets

import templater.PageGenerator

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.util.HashMap

class AllRequestsServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest,
                              response: HttpServletResponse) {

        val pageVariables = createPageVariablesMap(request)
        pageVariables["message"] = ""

        //response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        response.writer.println(request.getParameter("key"))
        response.contentType = "text/html;charset=utf-8"
        response.status = HttpServletResponse.SC_OK

    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest,
                               response: HttpServletResponse) {
        val pageVariables = createPageVariablesMap(request)

        val message = request.getParameter("message")

        response.contentType = "text/html;charset=utf-8"

        if (message == null || message.isEmpty()) {
            response.status = HttpServletResponse.SC_FORBIDDEN
        } else {
            response.status = HttpServletResponse.SC_OK
        }
        pageVariables["message"] = message ?: ""

        response.writer.println(PageGenerator.instance().getPage("page.html", pageVariables))
    }

    private fun createPageVariablesMap(request: HttpServletRequest): MutableMap<String, Any> {
        val pageVariables = HashMap<String, Any>()
        pageVariables["method"] = request.method
        pageVariables["URL"] = request.requestURL.toString()
        pageVariables["pathInfo"] = request.pathInfo
        pageVariables["sessionId"] = request.session.id
        pageVariables["parameters"] = request.parameterMap.toString()
        return pageVariables
    }
}
