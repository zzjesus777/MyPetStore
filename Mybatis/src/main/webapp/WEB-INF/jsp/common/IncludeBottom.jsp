</div>

<div id="Footer">

	<div id="PoweredBy">&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
	</div>

	<div id="Banner">
		<c:if test="${sessionScope.account != null }">
			<c:if test="${sessionScope.authenticated}">
				<c:if test="${sessionScope.account.bannerOption}">
					<img src="${sessionScope.account.bannerName}" id="banner_img" />
				</c:if>
				<c:if test="${!sessionScope.account.bannerOption}">
				<img src="${sessionScope.account.bannerName}" id="banner_img" style="display: none"/>
				</c:if>
			</c:if>
		</c:if>
	</div>

</div>
<script type="text/javascript" src="JavaScript/javascript.js"></script>

</body>
</html>