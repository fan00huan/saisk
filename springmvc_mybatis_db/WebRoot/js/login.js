// JQUERY
$(document).ready(function() {
	$("#id_btn_clear").click(function() {
		$("#id_username").val("");
		$("#id_password").val("");
	});

});

function fnclear() {
	document.getElementsByName("username")[0].value="";
	document.getElementById('id_password').value="";
}

$(document).ready(
		function() {
			// $("#id_itemProductDate") idの場合
			$("input[name='birthday']").datepicker(
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
				$("input[name='birthday']").trigger("focus");
			});
			$('#id_delete_productDate').on("click", function() {
				$("input[name='birthday']").val("");
			});


		});

