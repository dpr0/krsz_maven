package main

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import servlets.AllRequestsServlet

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val allRequestsServlet = AllRequestsServlet()

        val context = ServletContextHandler(ServletContextHandler.SESSIONS)
        context.addServlet(ServletHolder(allRequestsServlet), "/*")

        val server = Server(8080)
        server.handler = context

        server.start()
        println("Server started")
        server.join()
    }
}
