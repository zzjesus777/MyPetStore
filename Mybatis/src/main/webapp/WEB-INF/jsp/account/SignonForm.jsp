<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog" style="height: 225px">
	<c:if test="${requestScope.massage != null}">
		<ul>
			<li>${requestScope.massage}</li>
		</ul>
	</c:if>
	<form action="signon" method="post">
		<table>

			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" value="${requestScope.username}" id="sign_on_username"/> </td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="${requestScope.password}"/> </td>
			</tr>
			<tr>
				<td>Check  code:</td>
				<td><input type="text" name="check_code" id="check_code_input"/> </td>
			</tr>
			<tr>
				<td colspan="2">
				<span style="font-size: small">Click to refresh!!!</span>
				<img alt="click to refresh" src="JavaScript/CheckCode.jsp" border="0" id="checkcode_img"  />
				</td>
			</tr>

		<tr>
				<td colspan="2"><div align="center"><input type="submit" value="login"/> </div> </td>
        </tr>
        <tr>
            <td colspan="2"><span id="warning" style="color: red"></span></td>
        </tr>


    </table>
    </form>
    Need a user name and password?<a href="newAccountForm">Register Now!</a>

</div>


<%@ include file="../common/IncludeBottom.jsp"%>

