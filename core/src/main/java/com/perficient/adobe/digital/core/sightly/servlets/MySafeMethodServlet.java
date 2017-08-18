package com.perficient.adobe.digital.core.sightly.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.Repository;
import javax.servlet.ServletException;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

//@SlingServlet(paths="/bin/company/repo");
@SlingServlet(resourceTypes = "digital/components/structure/page-default", selectors = "data")

public class MySafeMethodServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = -3960692666512058118L;

    @Reference

    private Repository repository;

    @Override

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

//       response.setHeader("Content-Type", "application/json");

        JSONArray jsonArray = new JSONArray();

        String[] pathInfo = request.getPathInfo().split("\\.");
        String path = pathInfo[0];
        //String path = request.getRequestPathInfo().getResourcePath();
        System.out.println("path:" + path);

        final PageManager pageManager = request.getResource().getResourceResolver().adaptTo(PageManager.class);
        Page currentPage = pageManager != null ? pageManager.getPage(path) : null;

        if (currentPage != null) {

            Iterator<Page> childPages = currentPage.listChildren();
            while (childPages.hasNext()) {
                Page childPage = childPages.next();

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", childPage.getTitle());
                    jsonObject.put("link", childPage.getPath() + ".html");

                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        response.getWriter().print(jsonArray.toString());

    }

}

