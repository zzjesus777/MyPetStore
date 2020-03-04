<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog" style="height: 644px">
<form action="editAccount" method="post">


	<table>
		<tr>
			<td colspan="2">
				<h3 align="center">User Information</h3>
			</td>
		</tr>
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="new_password"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repassword" id="re-password"/></td>
		</tr>
	</table>
	<div id="re-password_info"><label style="color: red">Two password are different!!</label></div>
	<%@ include file="IncludeAccountFields.jsp"%>
	<table>
		<tr>
			<td>
	<input type="submit" name="editAccount" value="Save Account Information" />
			</td>
		</tr>
		<tr>
			<td>
		<a href="listOrder">My Orders</a>
			</td>
		</tr>
	</table>
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
