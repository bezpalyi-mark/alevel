package com.alevel.hibernate.iptracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ip-tracker", urlPatterns = "/trackMe")
public class BrowserIpTracker extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(BrowserIpTracker.class);

    private final Map<String, String> visitors = new HashMap<>();

    @Override
    public void init() {
        log.info("Sample Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        visitors.putIfAbsent(req.getHeader("User-Agent"), req.getRemoteHost());
        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">IP Tracker</h1>");
        responseBody.println("<h3 align=\"center\">Your IP address: " + req.getRemoteHost() + "</h3>");

        String client = req.getParameter("client");
        if (client == null) {
            client = "anonymous user";
        }
        responseBody.println("<h3 align=\"center\">Hi, " + client + " </h3>");
        responseBody.println("<h3 align=\"center\">Our visitors: </h3>");

        StringBuilder stringBuilder = new StringBuilder();

        for(var entry : visitors.entrySet()) {
            stringBuilder
                    .append("<b>")
                    .append(entry.getValue())
                    .append("::")
                    .append(entry.getKey())
                    .append("</b>")
                    .append("<br><br>");
        }
        responseBody.print(stringBuilder.toString());
    }

    @Override
    public void destroy() {
        log.info("Sample Servlet destroyed");
    }
}
