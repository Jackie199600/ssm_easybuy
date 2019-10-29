<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		var array = $(".qprice");
		var totalCost = 0;
		for(var i = 0;i < array.length;i++){
			var val = parseInt($(".qprice").eq(i).html().substring(1));
			totalCost += val;
		}
		$("#totleprice").html("￥"+totalCost);
	});
	function addQuantity(obj,stock,price){
		var index = $(".car_btn_2").index(obj);
		var inputObj = $(".car_ipt").eq(index);
		var quantity = inputObj.val();
		var id = $(".id").eq(index).val();
		++quantity;
		if(quantity > stock){
			quantity = stock;
			alert("库存不足！");
		}
		var cost = quantity*price;
		$.ajax({
			url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
			type:"POST",
			dataType:"text",
			success:function (data) {
				if(data == "success"){
					//更新toSettlement的数据
					$(".qprice").eq(index).html("￥"+cost);
					inputObj.val(quantity);
					if(quantity < stock){
						var totalCost = parseInt($("#totleprice").html().substring(1));
						totalCost += price;
						$("#totleprice").html("￥"+totalCost);
					}
					//更新购物车超链接数据
					$(".quantity").eq(index).text(quantity);
					$(".cost").eq(index).text(cost);
					var costArray = $(".cost");
					var totalCost = 0;
					for(var i=0;i<costArray.length;i++){
						var obj = costArray[i];
						obj = $(obj);
						var val = parseInt(obj.text());
						totalCost += val;
					}
					$("#totalCost").text(totalCost);
				}
			}
		});
	}
	function subQuantity(obj,price){
		var index = $(".car_btn_1").index(obj);
		var inputObj = $(".car_ipt").eq(index);
		var quantity = inputObj.val();
		var id = $(".id").eq(index).val();
		if(quantity == 1){
			return;
		}
		--quantity;
		var cost = quantity*price;
		$.ajax({
			url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
			type:"POST",
			dataType:"text",
			success:function(data){
				if(data == "success"){
					$(".qprice").eq(index).html("￥"+cost);
					inputObj.val(quantity);
					if(quantity>=1){
						var totalCost = parseInt($("#totleprice").html().substring(1));
						totalCost -= price;
						$("#totleprice").html("￥"+totalCost);
					}
					//更新购物车超链接数据
					$(".quantity").eq(index).text(quantity);
					$(".cost").eq(index).text(cost);
					var costArray = $(".cost");
					var totalCost = 0;
					for(var i=0;i<costArray.length;i++){
						var obj = costArray[i];
						obj = $(obj);
						var val = parseInt(obj.text());
						totalCost += val;
					}
					$("#totalCost").text(totalCost);
				}
			}
		});
	}
	function removeCart(id){
		if(confirm("是否确定要删除？")){
			window.location.href = "/product/removeCart/"+id;
		}
	}
</script>
<title>易买网</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="searchBar">
		<%@ include file="searchBar.jsp"%>
	</div>
	<div class="i_bg">
		<div id="settlement">

			<div class="content mar_20">
				<img src="/images/img1.jpg" />
			</div>
			<div class="content mar_20">
				<table border="0" class="car_tab"
					style="width: 1200px; margin-bottom: 50px;" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="car_th" width="200">商品名称</td>
						<td class="car_th" width="150">单价</td>
						<td class="car_th" width="150">购买数量</td>
						<td class="car_th" width="130">小计</td>
						<td class="car_th" width="150">操作</td>
					</tr>

					<c:forEach items="${list}" var="cart">
						<tr class="goods">
							<td>
								<div class="c_s_img">
									<a href="goodsList_getProductById?id=${product.value.id}"><img
											src="/images/${cart.product.filename}" width="73" height="73" /></a>
								</div> ${cart.product.name}
							</td>
							<td align="center" style="color: #ff4e00;" id="price">￥${cart.product.price}</td>
							<td align="center">
								<div class="c_num">
									<input type="button" value="" onclick="subQuantity(this,${cart.product.price});" class="car_btn_1" />
									<input type="text" value="${cart.quantity}" name="quantit" class="car_ipt" readonly="readonly" />
									<input type="button" value="" onclick="addQuantity(this,${cart.product.stock},${cart.product.price});" class="car_btn_2" />
								</div>
							</td>
							<input type="hidden" value="${cart.id}" class="id"/>
							<td align="center" style="color: #ff4e00;" class="qprice">￥${cart.cost}</td>
							<td align="center"><a href="javascript:void(0);"
												  onclick="removeCart(${cart.id});" class="delete">删除</a></td>
						</tr>
					</c:forEach>

					<tr height="70">
						<td colspan="6"
							style="font-family: 'Microsoft YaHei'; border-bottom: 0;"><span
							class="fr">商品总价：<b
								style="font-size: 22px; color: #ff4e00;" id="totleprice"></b></span></td>
					</tr>
					<tr valign="top" height="150">
						<td colspan="6" align="right">
						<!-- 继续购物 -->
						<a href="/user/index"><img src="/images/buy1.gif" /></a> 
						<!-- 确认结算 -->
						<a href="/product/settlement2"><img src="/images/buy2.gif" /></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>
