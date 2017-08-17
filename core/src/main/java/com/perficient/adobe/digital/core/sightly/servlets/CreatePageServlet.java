package com.perficient.adobe.digital.core.sightly.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.jcr.Repository;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SlingServlet(paths="/bin/myPostServlet", methods = "POST")
public class CreatePageServlet extends SlingAllMethodsServlet {
    private static final Logger LOGGER = Logger.getLogger( CreatePageServlet.class.getName() );

    @Reference
    private Repository repository;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        LOGGER.log( Level.FINE, "Start logging");
        response.setHeader("Content-Type", "application/json");

        String name = request.getParameter("name");
        String title = request.getParameter("title");
        System.out.println("name: " + name + " " + title);

        PageManager pageManager = request.getResource().getResourceResolver().adaptTo(PageManager.class);
        Page page = request.getResource().adaptTo(Page.class);
        System.out.println("Page: " + page);
        Page currentPage = pageManager.getPage("/content/digital/en");
        String parentPath = currentPage.getPath();
        String template = currentPage.getTemplate().getPath();
        JSONObject jsonObject = new JSONObject();

        try {
            Page newPage = pageManager.create(parentPath, name, template, title);

            jsonObject.put("title", newPage.getTitle());
            jsonObject.put("path", newPage.getPath() + ".html");
        } catch (WCMException | JSONException e) {
            e.printStackTrace();
        }

        response.getWriter().print(jsonObject.toString());


    }

}
