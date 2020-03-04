<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form action="newAccount" method="post">
	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><input type="text" name="username" id="register_username"/></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="register_password"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repassword" id="register_re_password"/></td>
		</tr>
	</table>
	<div id="re-password_info"><label style="color: red">Two password are different!!</label></div>
	<span id="warning" style="color: red"></span>

	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="newAccount" value="Save Account Information"/>
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>