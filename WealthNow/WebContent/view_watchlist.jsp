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
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Watchlist</title>
</head>
<body>
	<div class="container">
		<div class="col-lg-12">
			<div style="float: right">

				<a href="adding_watchlist.jsp"><button type="button"
						class="btn btn-primary btn-block">Add New WatchList</button></a> <br>
				<form action="#" method="POST">
					<button type="submit" value="delete_watchlist"
						class="btn btn-danger btn-block">Delete This Watchlist</button>
				</form>
				<br> <br>
			</div>
			<%
				UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
				WatchlistService ws = new WatchlistService();
				int userId = currentUser.getUser().getUserId();
				List<Watchlist> watchlists = ws.getUserWatchlists(userId);
			%>
			You have <b><%=(watchlists == null) ? 0 : watchlists.size()%></b>
			watchlists
			<%
				if (watchlists != null) {
			%>
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
							<%=(new Integer(list.getWatchlistId()).equals(request.getAttribute("watchlist_id")))
							? "selected" : ""%>><%=list.getWatchlistName()%></option>
						<%
							}
						%>

					</select> <input type="submit" id="submit-form" style="visibility: hidden" />
				</form>
			</div>
			<%
				}
			%>
			<br>

			<!-- title of the form and the page -->
			<div class="col-lg-12">

				<%
					if (request.getAttribute("watchlist_id") != null) {
						Integer id = (Integer) request.getAttribute("watchlist_id");
						Watchlist selectedWl = ws.viewWatchlist(id);
						SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy hh:mm:ssa z");
				%>
				<h2 style="text-align: center"><%=selectedWl.getWatchlistName()%></h2>
				<div style="float: right">
					<i>Updated: <%=sdf.format(new Date())%></i>
				</div>


				<%
					session.setAttribute("add_stock_watchlist_id", id);
				%>
				<a href="add_stock_watchlist.jsp"><button type="button"
						class="btn btn-primary">Add New Stocks</button></a><br>

				<form action="DeleteStockWatchlistController" method="post">
					<table class="table table-striped">
						<tr>
							<th>Instrument</th>
							<th>Bid</th>
							<th>Ask</th>
							<th>Opening</th>
							<th>Prvious Close</th>
							<th>High</th>
							<th>Low</th>
							<th>Net Change</th>
							<th>% Change</th>
						</tr>
						<%
							List<Stock> stocks = ws.listStocksFromWatchlist(id);

								for (Stock stock : stocks) {
									Date dateMod = stock.getModifiedDate();
						%>
						<tr>
							<td><%=stock.getStockSymbol() + ": " + stock.getCompany()%>
								<button class="btn btn-warning" type="submit"
									value="<%=stock.getStockSymbol()%>" name="delete" id="delete"
									style="float: right;">Delete</button></td>
							<td><%=stock.getBid()%></td>
							<td><%=stock.getAsk()%></td>
							<td><%=stock.getOpen()%></td>
							<td><%=stock.getClose()%></td>
							<td><%=stock.getDayHigh()%></td>
							<td><%=stock.getDayLow()%></td>
							<td><%=stock.getDaysValueChange()%></td>
							<td><%=stock.getPercentChange()%></td>
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
				</form>


				<div style="padding-top: 1em">
					<label for="submit-form" class="btn btn-primary">Refresh</label>
				</div>
			</div>
			<%
				}
			%>
		</div>
</body>
</html>