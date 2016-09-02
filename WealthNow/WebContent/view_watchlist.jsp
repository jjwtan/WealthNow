<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ page 
	import="java.util.List,
			com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.common.Watchlist,
			com.fdm.wealthnow.service.UserAccountService,
			com.fdm.wealthnow.service.WatchlistService" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Watchlist</title>
</head>
<body>
<%
	UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
	WatchlistService ws = new WatchlistService();
	List<Watchlist> watchlists = ws.getUserWatchlists(currentUser.getUser().getUserId());
%>
Number of watchlists: <%= watchlists.size() %>
<div>
	<select required name="term">
	<option selected disabled hidden style='display: none' value=''></option>
	<%
		for(Watchlist list: watchlists) {
	%>
		 <option value="<%=list.getWatchlistId() %>"><%=list.getWatchlistName() %></option> 
	<%
		}
	%>

	</select>
</div>
<br>
<div>
	<table width="100%" border="1">
		<tr>
			<th>Instrument</th>
			<th>Bid Size</th>
			<th>Bid</th>
			<th>Ask</th>
			<th>Ask Size</th>
			<th>High</th>
			<th>Low</th>
			<th>Net Change</th>
			<th>Last Updated</th>
		</tr>
	
	</table>
</div>
<%
	
%>
</body>
</html>