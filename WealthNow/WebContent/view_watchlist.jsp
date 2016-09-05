<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.util.List,
			java.util.Date,
			java.text.SimpleDateFormat,
			com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.common.Stock,
			com.fdm.wealthnow.common.Watchlist,
			com.fdm.wealthnow.service.UserAccountService,
			com.fdm.wealthnow.service.WatchlistService"%>
<html>
<head>
<style type="text/css">
.button {
	text-decoration: none;
	background-color: #EEEEEE;
	color: #333333;
	padding: 2px 6px 2px 6px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Watchlist</title>
</head>
<body>
<div style="float: right">
	<a href="login.jsp">Logout</a>
</div>
	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		WatchlistService ws = new WatchlistService();
		int userId = currentUser.getUser().getUserId();
		List<Watchlist> watchlists = ws.getUserWatchlists(userId);
	%>
	You have <b><%=watchlists.size()%></b> watchlists
	<div>
		<form id="watchlist_form" action="ViewWatchlist" method="post">
			Select Watchlist: <select required name="watchlistID"
				onchange="this.form.submit()">
				<option
					<%=(request.getAttribute("watchlist_id") == null) ? "selected" : ""%>
					disabled hidden style='display: none' value=''></option>
				<%
					for (Watchlist list : watchlists) {
				%>
				<option value="<%=list.getWatchlistId()%>"
					<%=(new Integer(list.getWatchlistId()).equals(request.getAttribute("watchlist_id"))) ? "selected"
						: ""%>><%=list.getWatchlistName()%></option>
				<%
					}
				%>

			</select> <input type="submit" id="submit-form" style="visibility: hidden" />
		</form>
	</div>
	<br>
	<div>
		<%
			if (request.getAttribute("watchlist_id") != null) {
				Integer id = (Integer) request.getAttribute("watchlist_id");
				Watchlist selectedWl = ws.viewWatchlist(id);
		%>
		<h2><%=selectedWl.getWatchlistName()%></h2>
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
				List<Stock> stocks = ws.listStocksFromWatchlist(id);

					for (Stock stock : stocks) {
						Date dateMod = stock.getModifiedDate();
						SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy hh:mm:ssa z");
			%>
			<tr>
				<td><%=stock.getCompany()%></td>
				<td><%=stock.getBidSize()%></td>
				<td><%=stock.getBid()%></td>
				<td><%=stock.getAsk()%></td>
				<td><%=stock.getAskSize()%></td>
				<td><%=stock.getDayHigh()%></td>
				<td><%=stock.getDayLow()%></td>
				<td><%=stock.getDaysValueChange()%></td>
				<td><%=sdf.format(dateMod)%></td>
			</tr>
			<%
				}
					if (stocks.size() == 0) {
			%>
			<tr>
				<td colspan="9" align="center">You have no stocks in this
					watchlist</td>
			</tr>
			<%
				}
			%>

		</table>
	</div>
	<div style="padding-top: 1em">
		<label for="submit-form" class="button">Refresh</label>
	</div>
	<%
		}
	%>
</body>
</html>