$(document).ready(
		function() {
			// $("#id_itemProductDate") idの場合
			$("input[name='itemProductDate']").datepicker(
					{
						dateFormat : "yy/mm/dd",
						dayNamesMin : [ "日", "月", "火", "水", "木", "金", "土" ],
						monthNamesShort : [ "一月", "二月", "三月", "四月", "五月", "六月",
								"七月", "八月", "九月", "十月", "十一月", "十二月" ],
						monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月",
								"七月", "八月", "九月", "十月", "十一月", "十二月" ]
					// onClose:function(dateText, inst){
					// if(dateText != null && dateText !== ""){
					// $( "#id_To" ).datepicker("option", "minDate", new
					// Date(dateText));
					// }
					// }
					});
			$("#id_picker_productDate").on("click", function() {
				$("input[name='itemProductDate']").trigger("focus");
			});
			$('#id_delete_productDate').on("click", function() {
				$("input[name='itemProductDate']").val("");
				// $("input[name='itemProductDate']").datepicker("option",
				// "minDate", "");
				// $("input[name='itemProductDate']").datepicker("option",
				// "maxDate", "");
			});

			// //現在日でセット
			// if(!$("input[name='itemProductDate']").val()) {
			// $("input[name='itemProductDate']").val($.datepicker.formatDate('yy/mm/dd',
			// new Date()));
			// }

		});

function fun_editItemsRetMV() {
	document.getElementById('itemForm').action = "editItemsRetMV.action";
	document.getElementById("itemForm").submit();
}

function fun_editItemsRetV() {
	document.getElementById('itemForm').action = "editItemsRetV.action";
	document.getElementById("itemForm").submit();
}

function fun_editItemsRetRedirect() {
	// alert(id);
	// var id_itemname = document.getElementById('id_itemname').value;
	// alert(id_itemname);
	document.getElementById('itemForm').action = "editItemsRetRedirect.action";
	document.getElementById("itemForm").submit();
}
function fun_editItemsRetforward() {
	document.getElementById('itemForm').action = "editItemsRetForward.action";
	document.getElementById("itemForm").submit();
}

function returnToSearch() {

//	var dbVal = $("textarea[name='itemDetail']").attr("dbVal");
//	var currentVal = $("textarea[name='itemDetail']").val();
//	if(dbVal == currentVal) {
//		alert("==");
//	} else {
//		alert("<>");
//	}
}



