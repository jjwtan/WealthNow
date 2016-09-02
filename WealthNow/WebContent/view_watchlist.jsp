<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.util.List,
			com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.common.Stock,
			com.fdm.wealthnow.common.Watchlist,
			com.fdm.wealthnow.service.UserAccountService,
			com.fdm.wealthnow.service.WatchlistService"%>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Watchlist</title>
</head>
<body>
	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		WatchlistService ws = new WatchlistService();
		List<Watchlist> watchlists = ws.getUserWatchlists(currentUser.getUser().getUserId());
	%>
	You have <b><%=watchlists.size()%></b> watchlists
	<div>
		<form action="ViewWatchlist" method="post">
			Select Watchlist: <select required name="watchlistID" onchange="this.form.submit()">
				<option selected disabled hidden style='display: none' value=''></option>
				<%
					for (Watchlist list : watchlists) {
				%>
				<option value="<%=list.getWatchlistId()%>"><%=list.getWatchlistName()%></option>
				<%
					}
				%>
	
			</select>
		</form>
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
			<%
				if(request.getAttribute("watchlist_id")!=null) {
					Integer id = (Integer)request.getAttribute("watchlist_id");
					List<Stock> stocks = ws.listStocksFromWatchlist(id);
			%>
				<tr>
					<td>
					<%= stocks.get(0).getCompany() %>
					</td>
				</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		
	%>
</body>
</html>