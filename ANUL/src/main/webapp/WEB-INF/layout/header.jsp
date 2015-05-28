<%@include file="../taglib.jsp"%>
<div id='cssmenu'>
	<ul>
		<li class="${current == 'users' ? 'active' : '' }"><a href='#'>Master Data</a>
			<ul>
				<security:authorize access="isAuthenticated()">
					<li>
						<a href='${pageContext.request.contextPath}/pro/projectEntry.html'>Project Master</a>
					</li>
					
					<li>
						<a href='${pageContext.request.contextPath}/pro/flatMstEntry.html'>Flat Master</a>
					</li>
					
					<li>
						<a href='${pageContext.request.contextPath}/pro/customerMstEntry.html'>Customer Master</a>
					</li>
					
					<li>
						<a href='${pageContext.request.contextPath}/pro/flatNegotiation.html'>Flat Negotiation</a>
					</li>
					<li>
						<a href='${pageContext.request.contextPath}/pro/userMstEntry.html'>User Master</a>
					</li>
					
				</security:authorize>
			</ul>
		</li>
		
		<li><a href='#'>Project Entry</a>
			<ul>
				<security:authorize access="isAuthenticated()">
					<li>
						<a href='${pageContext.request.contextPath}/pro/customerBooking.html'>Customer Booking</a>
					</li>
					<li>
						<a href='${pageContext.request.contextPath}/pro/paymentEntry.html'>Payment Entry</a>
					</li>
				</security:authorize>
			</ul>
		</li>
		
		<li>
			<a href='#'>Help</a>
		</li>
		
		<li style="float: right;">
			<a href='${pageContext.request.contextPath}/logout'>Logout</a>
		</li>
	</ul>
</div>