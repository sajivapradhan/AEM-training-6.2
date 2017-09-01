<%@page session="false"%><%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Top Navigation component

  Draws the top navigation

--%><%@include file="/libs/foundation/global.jsp"%><%
%>
<%@ page import="java.util.Iterator,
        com.day.text.Text,
        com.day.cq.wcm.api.PageFilter,
        com.day.cq.wcm.api.Page,
        com.day.cq.commons.Doctype" %><%

    // get starting point of navigation
    long absParent = currentStyle.get("absParent", 2L);
    String navstart = Text.getAbsoluteParent(currentPage.getPath(), (int) absParent);

    //if not deep enough take current node
    if (navstart.equals("")) navstart=currentPage.getPath();

    Resource rootRes = slingRequest.getResourceResolver().getResource(navstart);
    Page rootPage = rootRes == null ? null : rootRes.adaptTo(Page.class);
    String xs = Doctype.isXHTML(request) ? "/" : "";
    if (rootPage != null) {
        Iterator<Page> children = rootPage.listChildren(new PageFilter(request));
        %><div id="topnav"><ul><%
        while (children.hasNext()) {
            Page child = children.next();
            %><li><a href="<%= xssAPI.getValidHref(child.getPath()) %>.html"><%= xssAPI.encodeForHTMLAttr(child.getTitle()) %></a><span> | </span</li><%
        }
        %></ul></div><%
    }
%>

<style type="text/css">

#topnav ul {
    list-style: none;
    display: block;
    margin: 0;
    padding: 0;
    background-color: lavenderblush;
}
#topnav ul li {
    display: inline-block;
    font-size: small;
}
#topnav ul li a {
    color: gray;
}
}
#topnav ul li span {
    color: gray;
}
</style>

