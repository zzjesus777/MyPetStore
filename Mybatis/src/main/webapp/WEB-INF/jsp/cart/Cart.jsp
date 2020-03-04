<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<div id="Cart">

<h2>Shopping Cart</h2>
<form action="updateCart" method="post">
	<table id="cart_table">
		<tr>
			<th><b>Item ID</b></th>
			<th><b>Product ID</b></th>
			<th><b>Description</b></th>
			<th><b>In Stock?</b></th>
			<th><b>Quantity</b></th>
			<th><b>List Price</b></th>
			<th><b>Total Cost</b></th>
			<th>&nbsp;</th>
		</tr>

		<c:if test="${sessionScope.cart.numberOfItems == 0}">
			<tr>
				<td colspan="8"><b>Your cart is empty.</b></td>
			</tr>
		</c:if>
		<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
			<tr id="tr_${cartItem.item.itemId}">
				<td>
				<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
				</td>
				<td>
						${cartItem.item.product.productId}
				</td>
				<td>
						${cartItem.item.attribute1} ${cartItem.item.attribute2}
						${cartItem.item.attribute3} ${cartItem.item.attribute4}
						${cartItem.item.attribute5} ${cartItem.item.product.name}
				</td>
				<td>
						${cartItem.inStock}
				</td>
				<td>
					<input type="text" name=${cartItem.item.itemId} value="${cartItem.quantity}" class="spinner" oninput="value=value.replace(/^(0+)|[^\d]+/g,'')" id="${cartItem.item.itemId}"/>
				</td>
				<td class="single_price">
					<fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
				</td>
				<td class="total_price">
					<fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
				</td>
				<td>
					<button class="Button remove_button" id="${cartItem.item.itemId }">Remove</button>
				</td>
			</tr>
		</c:forEach>
<p id="console"></p>
		<tr>
			<td colspan="5">Sub Total:
				<span  id="sub_total">
				<fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
				</span>
			</td>
			<td colspan="3">
				<%--<input type="submit" value="Update Cart" id="submit"/>--%>
			</td>
		</tr>
	</table>

</form>
	<c:if test="${sessionScope.cart.numberOfItems > 0}">
		<a class="Button" href="newOrderForm" >Proceed to Checkout</a>
	</c:if></div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>