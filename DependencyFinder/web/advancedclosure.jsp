<%@ page import="java.io.*, java.util.*, com.jeantessier.dependency.*" %>
<%@ page errorPage="errorpage.jsp" %>

<!--
    Copyright (c) 2001-2005, Jean Tessier
    All rights reserved.
    
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:
    
    	* Redistributions of source code must retain the above copyright
    	  notice, this list of conditions and the following disclaimer.
    
    	* Redistributions in binary form must reproduce the above copyright
    	  notice, this list of conditions and the following disclaimer in the
    	  documentation and/or other materials provided with the distribution.
    
    	* Neither the name of Jean Tessier nor the names of his contributors
    	  may be used to endorse or promote products derived from this software
    	  without specific prior written permission.
    
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
    "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
    LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
    A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR
    CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
    EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
    PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
    PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
    NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<jsp:useBean id="version" class="com.jeantessier.dependencyfinder.Version" scope="application"/>

<html>

<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Closure in <%= application.getInitParameter("name") %></title>
</head>

<!-- Reading the parameters and setting up the forms -->

<%
    String startIncludes = request.getParameter("start-includes");
    if (startIncludes == null) {
	startIncludes = "//";
    }

    String startExcludes = request.getParameter("start-excludes");
    if (startExcludes == null) {
	startExcludes = "";
    }

    String packageStartIncludes = request.getParameter("package-start-includes");
    if (packageStartIncludes == null) {
	packageStartIncludes = "";
    }

    String packageStartExcludes = request.getParameter("package-start-excludes");
    if (packageStartExcludes == null) {
	packageStartExcludes = "";
    }

    String classStartIncludes = request.getParameter("class-start-includes");
    if (classStartIncludes == null) {
	classStartIncludes = "";
    }

    String classStartExcludes = request.getParameter("class-start-excludes");
    if (classStartExcludes == null) {
	classStartExcludes = "";
    }

    String featureStartIncludes = request.getParameter("feature-start-includes");
    if (featureStartIncludes == null) {
	featureStartIncludes = "";
    }

    String featureStartExcludes = request.getParameter("feature-start-excludes");
    if (featureStartExcludes == null) {
	featureStartExcludes = "";
    }

    String stopIncludes = request.getParameter("stop-includes");
    if (stopIncludes == null) {
	stopIncludes = "";
    }

    String stopExcludes = request.getParameter("stop-excludes");
    if (stopExcludes == null) {
	stopExcludes = "";
    }

    String packageStopIncludes = request.getParameter("package-stop-includes");
    if (packageStopIncludes == null) {
	packageStopIncludes = "";
    }

    String packageStopExcludes = request.getParameter("package-stop-excludes");
    if (packageStopExcludes == null) {
	packageStopExcludes = "";
    }

    String classStopIncludes = request.getParameter("class-stop-includes");
    if (classStopIncludes == null) {
	classStopIncludes = "";
    }

    String classStopExcludes = request.getParameter("class-stop-excludes");
    if (classStopExcludes == null) {
	classStopExcludes = "";
    }

    String featureStopIncludes = request.getParameter("feature-stop-includes");
    if (featureStopIncludes == null) {
	featureStopIncludes = "";
    }

    String featureStopExcludes = request.getParameter("feature-stop-excludes");
    if (featureStopExcludes == null) {
	featureStopExcludes = "";
    }

    String maximumInboundDepth = request.getParameter("maximum-inbound-depth");
    if (maximumInboundDepth == null) {
	maximumInboundDepth = "0";
    }

    String maximumOutboundDepth = request.getParameter("maximum-outbound-depth");
    if (maximumOutboundDepth == null) {
	maximumOutboundDepth = "";
    }

    String scope = request.getParameter("scope");
    if (scope == null) {
	scope = "feature";
    }

    String filter = request.getParameter("filter");
    if (filter == null) {
	filter = "feature";
    }
%>

<body>

<form action="<%= request.getRequestURI() %>" method="post">

<table border="0" cellpadding="5"><tr><td colspan="2">

<p class="title"><code><%= application.getInitParameter("name") %></code></p>

</td></tr><tr><td colspan="2" align="center">

<table frame="border" rules="cols" class="controls" width="100%"><tr>

<th class="navigation"><a href="advancedquery.jsp">Dependency graph</a></th>
<th class="currentnavigation">Transitive closure</th>
<th class="navigation"><a href="advancedmetrics.jsp">Dependency metrics</a></th>

</tr></table>

</td></tr><tr><td colspan="2" align="center">

<table frame="border" rules="groups" class="controls">

    <colgroup span="3" />
    <colgroup span="3" />

    <tbody>
    <tr>
	<td colspan="3" width="50%">
	    <b>Start with programming elements</b>
	</td>
	<td colspan="3" width="50%">
	    <b>Stop with programming elements</b>
	</td>
    </tr>
    <tr>
	<td>
	</td>
	<td>
	    including:
	</td>
	<td>
	    excluding:
	</td>
	<td>
	</td>
	<td>
	    including:
	</td>
	<td>
	    excluding:
	</td>
    </tr>
    <tr>
	<td>
	</td>
	<td>
	    <input type="text" name="start-includes" value="<%= startIncludes %>" onMouseOver="window.status='Start with packages, classes, methods, or fields matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="start-excludes" value="<%= startExcludes %>" onMouseOver="window.status='Do NOT start with packages, classes, methods, or fields matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
	<td>
	</td>
	<td>
	    <input type="text" name="stop-includes" value="<%= stopIncludes %>" onMouseOver="window.status='Stop at packages, classes, methods, or fields matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="stop-excludes" value="<%= stopExcludes %>" onMouseOver="window.status='Do NOT stop at packages, classes, methods, or fields matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
    </tr>
    <tr>
	<td>
	    package
	</td>
	<td>
	    <input type="text" name="package-start-includes" value="<%= packageStartIncludes %>" onMouseOver="window.status='Start with packages matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="package-start-excludes" value="<%= packageStartExcludes %>" onMouseOver="window.status='Do NOT start with packages matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
	<td>
	    package
	</td>
	<td>
	    <input type="text" name="package-stop-includes" value="<%= packageStopIncludes %>" onMouseOver="window.status='Stop at packages matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="package-stop-excludes" value="<%= packageStopExcludes %>" onMouseOver="window.status='Do NOT stop at packages matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
    </tr>
    <tr>
	<td>
	    class
	</td>
	<td>
	    <input type="text" name="class-start-includes" value="<%= classStartIncludes %>" onMouseOver="window.status='Start with classes matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="class-start-excludes" value="<%= classStartExcludes %>" onMouseOver="window.status='Do NOT start with classes matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
	<td>
	    class
	</td>
	<td>
	    <input type="text" name="class-stop-includes" value="<%= classStopIncludes %>" onMouseOver="window.status='Stop at classes matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="class-stop-excludes" value="<%= classStopExcludes %>" onMouseOver="window.status='Do not stop at classes matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
    </tr>
    <tr>
	<td>
	    feature
	</td>
	<td>
	    <input type="text" name="feature-start-includes" value="<%= featureStartIncludes %>" onMouseOver="window.status='Start with methods or fields matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="feature-start-excludes" value="<%= featureStartExcludes %>" onMouseOver="window.status='Do NOT start with methods or fields matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
	<td>
	    feature
	</td>
	<td>
	    <input type="text" name="feature-stop-includes" value="<%= featureStopIncludes %>" onMouseOver="window.status='Stop at methods or fields matching any these expressions. E.g., /^com.mycompany/, /\\.get\\w+\\(/'" onMouseOut="window.status=''">
	</td>
	<td>
	    <input type="text" name="feature-stop-excludes" value="<%= featureStopExcludes %>" onMouseOver="window.status='Do NOT stop at method or fields matching any of these expressions. E.g., /Test/'" onMouseOut="window.status=''">
	</td>
    </tr>
    </tbody>

    <tbody>
    <tr>
        <td colspan="6" align="center">

Follow inbounds:
<input type="text" name="maximum-inbound-depth" value="<%= maximumInboundDepth %>" size="2" onMouseOver="window.status='Maximum hops against the direction dependencies.  Empty field means no limit.'" onMouseOut="window.status=''">
Follow outbounds:
<input type="text" name="maximum-outbound-depth" value="<%= maximumOutboundDepth %>" size="2" onMouseOver="window.status='Maximum hops in the direction of dependencies.  Empty field means no limit.'" onMouseOut="window.status=''">

        </td>
    </tr>
    </tbody>
</table>

</td></tr><tr><td colspan="2" align="center">

<table frame="border" rules="groups" class="controls" width="100%">

    <colgroup span="2" />
    <colgroup span="2" />

    <tbody>
    <tr>
	<td colspan="2" width="50%">
	    <b>Summarize programming elements</b>
	</td>
	<td colspan="2" width="50%">
	    <b>Summarize dependencies</b>
	</td>
    </tr>
    <tr>
	<td align="center" colspan="2">
	    <input type="radio" name="scope" value="package" <%= "package".equals(scope) ? "checked" : "" %> onMouseOver="window.status='Start with packages'" onMouseOut="window.status=''">&nbsp;package
	    <input type="radio" name="scope" value="class" <%= "class".equals(scope) ? "checked" : "" %> onMouseOver="window.status='Start with classes (with their package)'" onMouseOut="window.status=''">&nbsp;class
	    <input type="radio" name="scope" value="feature" <%= "feature".equals(scope) ? "checked" : "" %> onMouseOver="window.status='Start with methods and fields (with their class and package)'" onMouseOut="window.status=''">&nbsp;feature
	</td>
	<td align="center" colspan="2">
	    <input type="radio" name="filter" value="package" <%= "package".equals(filter) ? "checked" : "" %> onMouseOver="window.status='Stop with packages'" onMouseOut="window.status=''">&nbsp;package
	    <input type="radio" name="filter" value="class" <%= "class".equals(filter) ? "checked" : "" %> onMouseOver="window.status='Stop with classes (with their package)'" onMouseOut="window.status=''">&nbsp;class
	    <input type="radio" name="filter" value="feature" <%= "feature".equals(filter) ? "checked" : "" %> onMouseOver="window.status='Stop with methods and fields (with their class and package)'" onMouseOut="window.status=''">&nbsp;feature
	</td>
    </tr>
    </tbody>
</table>

</td></tr><tr>

<td align="left"><font size="-1">Use Perl regular expressions, <a target="_blank" href="<jsp:getProperty name="version" property="ImplementationURL"/>Manual.html#PerlRegularExpressions">see the manual</a></font></td>
<td align="right"><a href="closure.jsp">&lt;&lt;&lt; simple</a></td>

</tr><tr><td align="center" colspan="2">

<input type="submit" name="submit" value="Run Query"/>

</td></tr></table>

</form>

<hr size="3" />

<%
    if (request.getParameter("submit") != null) {
	if (application.getAttribute("factory") != null) {
%>

<pre class="result">

<%
	    Date start = new Date();
	    
	    RegularExpressionSelectionCriteria startCriteria  = new RegularExpressionSelectionCriteria();
	    startCriteria.setGlobalIncludes(startIncludes);
	    startCriteria.setPackageIncludes(packageStartIncludes);
	    startCriteria.setClassIncludes(classStartIncludes);
	    startCriteria.setFeatureIncludes(featureStartIncludes);
	    startCriteria.setGlobalExcludes(startExcludes);
	    startCriteria.setPackageExcludes(packageStartExcludes);
	    startCriteria.setClassExcludes(classStartExcludes);
	    startCriteria.setFeatureExcludes(featureStartExcludes);
	
	    RegularExpressionSelectionCriteria stopCriteria = new RegularExpressionSelectionCriteria();
	    stopCriteria.setGlobalIncludes(stopIncludes);
	    stopCriteria.setPackageIncludes(packageStopIncludes);
	    stopCriteria.setClassIncludes(classStopIncludes);
	    stopCriteria.setFeatureIncludes(featureStopIncludes);
	    stopCriteria.setGlobalExcludes(stopExcludes);
	    stopCriteria.setPackageExcludes(packageStopExcludes);
	    stopCriteria.setClassExcludes(classStopExcludes);
	    stopCriteria.setFeatureExcludes(featureStopExcludes);

	    TransitiveClosure closure = new TransitiveClosure(startCriteria, stopCriteria);

	    try {
		closure.setMaximumInboundDepth(Long.parseLong(maximumInboundDepth));
	    } catch (NumberFormatException ex) {
		closure.setMaximumInboundDepth(TransitiveClosure.UNBOUNDED_DEPTH);
	    }

	    try {
		closure.setMaximumOutboundDepth(Long.parseLong(maximumOutboundDepth));
	    } catch (NumberFormatException ex) {
		closure.setMaximumOutboundDepth(TransitiveClosure.UNBOUNDED_DEPTH);
	    }

	    closure.traverseNodes(((NodeFactory) application.getAttribute("factory")).getPackages().values());

	    RegularExpressionSelectionCriteria scopeCriteria  = new RegularExpressionSelectionCriteria();
	    scopeCriteria.setMatchingPackages("package".equals(scope));
	    scopeCriteria.setMatchingClasses("class".equals(scope));
	    scopeCriteria.setMatchingFeatures("feature".equals(scope));

	    RegularExpressionSelectionCriteria filterCriteria = new RegularExpressionSelectionCriteria();
	    filterCriteria.setMatchingPackages("package".equals(filter));
	    filterCriteria.setMatchingClasses("class".equals(filter));
	    filterCriteria.setMatchingFeatures("feature".equals(filter));

	    GraphSummarizer summarizer = new GraphSummarizer(scopeCriteria, filterCriteria);
	    summarizer.traverseNodes(closure.getFactory().getPackages().values());

	    TextPrinter printer = new TextPrinter(new PrintWriter(out));

	    printer.traverseNodes(summarizer.getScopeFactory().getPackages().values());

	    Date stop = new Date();
%>

</pre>

<p><%= (stop.getTime() - start.getTime()) / (double) 1000 %> secs.</p>

<%
	} else {
%>

<h3>No dependency graph available</h3>

<p>Please ask the webmaster to extract a dependency graph before you start placing queries.</p>

<%
	}
    }
%>

<jsp:include page="footer.jsp"/>

</body>

</html>
