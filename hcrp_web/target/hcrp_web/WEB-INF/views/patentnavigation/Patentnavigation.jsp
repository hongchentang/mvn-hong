<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<%@page import="com.alipay.api.AlipayClient" %>
<%--<link rel="stylesheet" href="${ctx}/themes/orgApply.less">--%>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<c:set value="${type.type}" var="reportType"/>


<div id="reportStep1${reportType}" class="bg"  >

	<div id="titleDiv" class="title_div">
		<div class="title_div_text">
			新建报告——${type.typeName}：
		</div>
	</div>

	<div id="addTempStep1Right" class="step1_left_div">
		<div id="addStep" class="add_step_div">
			<div name="addTempStep"><strong class="strong" >录入信息</strong></div>
			<div name="addTempStep">选择模板</div>
			<div name="addTempStep">导入数据</div>
			<div name="addTempStep">修改报告</div>

		</div>
	</div>

	<div id="addTempStep1Left" class="step1_right_div">
		<div id="tipDiv" class="tip_div">
			请填写以下基础信息：
		</div>
		<div id="headline" class="tip_diheadlinev">
			标题：<input class="input_title" type="text" id="title${reportType}" name="title2"   />
		</div>
		<div id="tempTypeListDiv" style="height: 400px;overflow:auto" >
			<div id="tempTypeTableDiv">
				<form id="form" name="form1" action="/patent/navigation2.do">
					<table id="table${reportType}" class="temp_type_list">
						<tr>
							<td>章节：</td>
							<td>
								<input class="inputtype" type="text" id="section0" name="section${reportType}"   />
							</td>
						</tr>
						<tr>
							<td>章节：</td>
							<td>
								<input class="inputtype" type="text" id="section1" name="section${reportType}"   />
							</td>
						</tr>
						<tr>
							<td>章节：</td>
							<td>
								<input class="inputtype" type="text" id="section2" name="section${reportType}"   />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="opBottom" class="op_bottom">
			<input class="addinput" type="button" name="add" value="添加章节" onclick="addTr${reportType}()">
			<input class="deleinput" type="button" name="dele" value="删除章节" onclick="delrow${reportType}()">
			<input class="next" type="button" name="next" value="下一步" onclick="next${reportType}()">
		</div>
	</div>
</div>
<div id="reportStep2${reportType}" class="bg" style="display:none">

	<div id="titleDiv2" class="title_div">
		<div class="title_div_text">
			新建报告——${type.typeName}：
		</div>
	</div>

	<div id="addTempStep1Right2" class="step1_left_div">
		<div id="addStep2" class="add_step_div">
			<div name="addTempStep"><strong class="strong">录入信息</strong></div>
			<div name="addTempStep">选择模板</div>
			<div name="addTempStep">导入数据</div>
			<div name="addTempStep">修改报告</div>

		</div>
	</div>

	<div id="addTempStep1Left2" class="step1_right_div">
		<div id="tipDiv2" class="tip_div">
			请输入重要申请人名单：：
		</div>
		<div id="headline2" class="tip_diheadlinev">
			标题：<input class="input_title" type="text" id="title2${reportType}" name="title2" readonly="readonly"   />
		</div>
		<div id="tempTypeListDiv2" style="height: 400px;overflow:auto" >
			<div id="tempTypeTableDiv2">
				<form id="form2" name="form1" action="/patent/navigation2.do">
					<table id="table2${reportType}" class="temp_type_list">
						<tr>
							<td>申请人：</td>
							<td>
								<input class="inputtype" type="text" id="applyer0" name="applyer${reportType}"   />
							</td>
						</tr>
						<tr>
							<td>申请人：</td>
							<td>
								<input class="inputtype" type="text" id="applyer1" name="applyer${reportType}"   />
							</td>
						</tr>
						<tr>
							<td>申请人：</td>
							<td>
								<input class="inputtype" type="text" id="applyer2" name="applyer${reportType}"   />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="opBottom2" class="op_bottom">
			　<input class="addinput" type="button" name="add" value="添加申请人" onclick="addTrSqr${reportType}()">
			<input class="deleinput" type="button" name="dele" value="删除申请人" onclick="delrowSqr${reportType}()">
			<input class="next"  type="button" name="back" value="上一步" onclick="back${reportType}()">
			<input class="next" type="button" name="nexttemplate" value="下一步" onclick="nexttemplate${reportType}()">
		</div>
	</div>


</div>

<div id="reportStep3${reportType}" class="bg" style="display:none" >


	<div id="titleDiv3" class="title_div">
		<div class="title_div_text">
			新建报告——${type.typeName}：
		</div>
	</div>
	<div id="titleDiv3_01" class="title_div">
		<div class="title_div_texts">
			标题：<input class="input_title" type="text"  id="title3${reportType}" name="title2"   />
		</div>
	</div>

	<div id="addTempStep1Right3" class="conter_step1_left_div">
		<div id="addStep3" class="add_step_div">
			<div name="addTempStep">录入信息</div>
			<div name="addTempStep"><strong class="strong">选择模板</strong></div>
			<div name="addTempStep">导入数据</div>
			<div name="addTempStep">修改报告</div>

		</div>
	</div>
	<div class="step1_conter_div">
		<div id="step1_conter_div3${reportType}" class="add_step_div_conter" style="overflow:auto;">
			<c:forEach items="${reportTemplate}" var="template" begin = "0" varStatus="s">

				<c:choose>

					<c:when test="${s.first}">
						<div id="${template.id}${reportType}" class="type_name_conter" name="type_name_conter" style="width: 90px;margin-left: 8%;background-color:#b2d9ff "
							 onclick="template${reportType}('${template.id}','${template.reportSynopsis}','${template.reportAnalysisDimension}','${template.reportTableType}')"  >${template.reportName}
						</div>
					</c:when>
					<c:otherwise>
						<div id="${template.id}${reportType}" class="type_name_conter" name="type_name_conters" style="width: 90px;margin-left: 8%"
							 onclick="template${reportType}('${template.id}','${template.reportSynopsis}','${template.reportAnalysisDimension}','${template.reportTableType}')"  >${template.reportName}
						</div>
					</c:otherwise>

				</c:choose>

			</c:forEach>

		</div>
		　



	</div>
	<div>
		<div id="step1_rigth_div" class="tip_div">
			<div style="margin-top: 10px" >
				<div class="textareatitle">模板简历：</div>
				<div>
				<textarea id="synopsis${reportType}" class="textareatext" >${reportTemplate.get(0).reportSynopsis}
	              </textarea>
				</div>
			</div>
			<div style="margin-top: 10px"  >
				<div class="textareatitle">分析维度：</div>
				<div>
				<textarea id="dimension${reportType}" class="textareatext" >${reportTemplate.get(0).reportAnalysisDimension}
	              </textarea>
				</div>
			</div>
			<div style="margin-top: 10px"  >
				<div class="textareatitle">图表类型：</div>
				<div>
				<textarea id="tabletype${reportType}"  class="textareatext" >${reportTemplate.get(0).reportTableType}
	              </textarea>
				</div>
			</div>
			<div style="margin-top: 10px"  >
				<div class="textareatitle">模板预览：</div>
				<div>
				<textarea  class="textareatext_max" >

	              </textarea>
				</div>
			</div>
			<input class="next"   type="button" name="back" value="上一步" onclick="backtemplate${reportType}()">
			<input class="next"   type="button" name="next" value="下一步" onclick="nextdiv4${reportType}()">
		</div>
	</div>


</div>


<div id="reportStep4${reportType}" class="bg" style="display:none">

	<div id="titleDiv4" class="title_div">
		<div class="title_div_text">
			新建报告——${type.typeName}：
		</div>
	</div>
	<div id="addTempStep1Right4" class="step1_left_div">
		<div id="addStep4" class="add_step_div">
			<div name="addTempStep">录入信息</div>
			<div name="addTempStep">选择模板</div>
			<div name="addTempStep"><strong class="strong">导入数据</strong></div>
			<div name="addTempStep">修改报告</div>

		</div>
	</div>
	<div id="addTempStep1Left4" class="step1_right_div">
		<div id="tipDiv4" class="tip_div">
			请填写以下基础信息：
		</div>
		<div id="headline4" class="tip_diheadlinev">
			标题：<input class="input_title" type="text" id="title4${reportType}" name="title2"   />
		</div>
		<div id="tempTypeListDiv4" >
			<div id="tempTypeTableDiv4">

				<form id="form4${reportType}" name="form4"  action="" method="post"  enctype="multipart/form-data">

					<input type="hidden" id="reportTmeplateId${reportType}" name="reportTmeplateId${reportType}" value="${reportTemplate.get(0).id}"  />
					<input type="hidden" name="type" value="${type.typeName}"/>
					<input type="hidden" name="reporttype" value="${reportType}"/>
					<input type="hidden" id="reporttitle${reportType}" name="reporttitle${reportType}" value=""/>
					<table id="table4${reportType}"  class="temp_type_list">

					</table>
					<table id="table5${reportType}"  class="temp_type_list" style="display: none">


					</table>
				</form>
			</div>
		</div>
		<div id="opBottom4${reportType}" class="op_bottom">
			<input  class="next" type="button" name="back" value="上一步" onclick="gobacktemplate${reportType}()">
			<input class="next" type="button" name="next" value="下一步" onclick="nextdiv5${reportType}()">
		</div>
		<div id="opBottom4s${reportType}" class="op_bottom" style="display: none">
			<input  class="next" type="button" name="back" value="上一步" onclick="gotitle${reportType}()">
			<input class="next" type="button" name="sub" value="提交" onclick="importUser${reportType}()">
		</div>
	</div>
</div>

<div id="reportStep6" class="bgs" style="display: none"  >
	<div id="titleDiv6" class="title_div">
		<div class="title_div_text">
			新建报告——${type.typeName}：
		</div>
	</div>
	<div id="addTempStep1Right6" class="step1_left_div">
		<div id="addStep6" class="add_step_div">
			<div name="addTempStep">选择报告类型</div>
			<div name="addTempStep">选择现有模板</div>
			<div name="addTempStep"><strong>修改分析维度</strong></div>
			<div name="addTempStep">修改模板内容</div>

		</div>
	</div>
	<div id="addTempStep1Left6" class="step1_right_div"   >
		<div id="tipDiv6" class="tip_div" >
			请修改分析维度
		</div>
		<div id="tempTypeListDiv6"  >
			<div id="tempTypeTableDiv6"  >

				<form id="form66" name="form4"  action="${ctx}/patent/import.do" method="post"  enctype="multipart/form-data">
					<table id="test"   class="temp_type_list">
						<tr id="1">
							<th colspan="2">一级维度</th>
							<th colspan="2">二级维度</th>
						</tr>
						<tr id="2">
						</tr>

					</table>
				</form>
			</div>
		</div>
		<div id="opBottom6" class="op_bottom">
			<input class="next" type="button" name="button" value="增加" onclick="addTRS('test');">
			<input class="next" type="button" name="button" value="删除" onclick="delTRS('test');">
			<input class="next" type="button" name="button" value="提交" onclick="add();">
		</div>
	</div>
</div>




<div id="insertHtmlserviceCase" style="display: none;">

</div>

<script type="text/javascript">
	$(document).ready(function () {
		debugger
		//选择类型
		var type = '${paramMap.type}';
		if (type == ''){
			selectType(0, 0);
		}else {
			selectType(type, 0);
		}

	});
	function selectType(type, model) {
		var selectId = 'quan' + type;
		var table = $('#listTableType');

		var typeList = table.find('div[class^="circle_null"]');

		$.each(typeList, function (idx, val) {

			if (selectId === val.id){
				$(val).css('background-color', 'black');
				$(val).attr('selectedType', '1');
				$('#type').val(type);
			}else{
				$(val).css('background-color', 'white');
				$(val).attr('selectedType', '0');
			}
		});

		if(model == 1){
			refreshPage();
		}
	}

/*	$(function(){
		var erro="${erro}";

		if(erro==""){

		}else{
			alert(erro);
		}

		debugger

		$.fn.jPicker.defaults.images.clientPath='${ctx}/js/jquery/plugins/jpicker/images/';
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用		UE.getEditor('editor')就能拿到相关的实例


		var editor=UE.getEditor('serviceCase');
		editor.addListener('contentchange',function(){
			this.sync();
		});
		editor.ready( function( editor ) {
			var insertHtml=$("#insertHtmlserviceCase").html();
			if(insertHtml!=""){
				UE.getEditor('serviceCase').setContent(insertHtml,false);
			}else{

				console.log("insertHtml is null");
			}
		});

	});*/



	function openListNoticeTab() {
		layout_center_addTabFun({
			id: 'listNoticeIndex',
			title: '通知公告',
			href: '${ctx}/common/notice/listNotice.do?tabId=listNoticeIndex',
			closable: true
		});
	}


	function patentList(url, title, id) {
		layout_center_addTabFun({
			id: id,
			title: title,
			closable: true,
			iconCls: "",
			//cache : false,
			href: url,
			queryParams: {},
			//content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
		});
	}

	//跳转到统计数字对应的页面
	function goStatistics(id, title, url) {
		title = title.trim();
		layout_center_addTabFun({
			id: id,
			title: title,
			href: url,
			closable: true,
			queryParams: {},
		});
	}

	function changeUserInfo() {


		openWindow('editUserInfoWindow', '个人信息修改', 900, 370, '${ctx }/common/user/goChangeUserInfo.do', true);
	}
	function addTr${reportType}(){
		var i=document.getElementById('table${reportType}').rows.length;
		$("#table${reportType}").append("<tr><td>章节：</td> <td> <input class=\"inputtype\" type=\"text\" id=\"section"+i+"\"  name=\"section${reportType}\"   /></td></tr>");


	}
	function delrow${reportType}(){

		var i=document.getElementById('table${reportType}').rows.length;
		document.getElementById('table${reportType}').deleteRow(i-1);
	}

	function addTrSqr${reportType}(){
		debugger
/*		var section=document.getElementById('table${reportType}').rows.length;	*/
		var i=document.getElementById('table2${reportType}').rows.length;

		$("#table2${reportType}").append("<tr> <td>申请人：</td> <td> <input id=\"applyer"+i+"\" class=\"inputtype\" type=\"text\" name=\"applyer${reportType}\"   /> </td> </tr>");

	}
	function delrowSqr${reportType}(){

		var i=document.getElementById('table2${reportType}').rows.length;
		document.getElementById('table2${reportType}').deleteRow(i-1);
	}

	function next${reportType}(){
		debugger


		var titletext=$("#title${reportType}").val();
		if(titletext==""){
			$.messager.alert('提示','请填写标题');

		}else{
			var isnull="1";
			$('input[name="section${reportType}"]').each(function(){
				var section=$(this).val();
				if (section==""){
					isnull="0";
				}
			});
			if(isnull=="1"){

				$("#title2${reportType}").val(titletext);
				$("#title3${reportType}").val(titletext);
				$("#title4${reportType}").val(titletext);
				$("#reporttitle${reportType}").val(titletext);
				$("#reportStep1${reportType}").css("display","none");
				$("#reportStep2${reportType}").css("display","");

			}else{
				$.messager.alert("提示","章节不能为空");
			}

		}
	}
	function back${reportType}(){
		$("#reportStep1${reportType}").css("display","");
		$("#reportStep2${reportType}").css("display","none");
	}
	function nexttemplate${reportType}(){

		var isnull="1";
		$('input[name="applyer${reportType}"]').each(function(){
			var applyers=$(this).val();
			if (applyers==""){
				isnull="0";
			}
		});
		if(isnull=="1"){
			$("#reportStep2${reportType}").css("display","none");
			$("#reportStep3${reportType}").css("display","");
		}else{
			$.messager.alert("提示","申请人不能为空");
		}
	}
	function nextdiv4${reportType}() {
		debugger
		$("#reportStep3${reportType}").css("display","none");
		$("#reportStep4${reportType}").css("display","");

		var mycars = new Array();
		$("#table4${reportType}").find("tr").remove();
		var size = document.getElementsByName("section${reportType}").length;
		mycars = document.getElementsByName("section${reportType}");

		debugger
		var names = new Array();
		var namesize=document.getElementsByName("names${reportType}").length;
		if(namesize==0){
			for (var j = 0; j < size; j++) {
				var val=document.getElementsByName("section${reportType}")[j].value;
				var id=document.getElementsByName("section${reportType}")[j].id;
				var sections=$('input[name="section${reportType}"]');

				var index=parseInt(j)+parseInt(1);
				$("#table4${reportType}").append("<input type='hidden'  name='sections${reportType}' value='"+val+"'  />");
				$("#table4${reportType}").append("<input type='hidden'  name='sectionsFile${reportType}' value='"+id+"' />");
				var fileType=${reportType};
				if(fileType=="0"){
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"'  id='file"+id+"' type='file' multiple='multipl' /></td></tr>");

				}else if (fileType=="1"){
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"'  id='file"+id+"' type='file' multiple='multipl' /></td></tr>");


				}else if (fileType=="2"){
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"' id='file"+id+"' type='file' multiple='multipl' /></td></tr>");

				}else if (fileType=="3"){
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"'  id='file"+id+"' type='file' multiple='multipl' /></td></tr>");

				}else if (fileType=="4"){
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"'   id='file"+id+"' type='file' multiple='multipl' /></td></tr>");

				}else {
					$("#table4${reportType}").append("<tr><td><div class='td_div'>章节:"+val+"</div></td><td ><input class=\"input_inputtype\" name='"+id+"'   id='file"+id+"' type='file' multiple='multipl' /></td></tr>");
				}
			}
		}
	}

	function nextdiv5${reportType}() {
		debugger
		var size = document.getElementsByName("section${reportType}").length;
		var isnull="1";
		for (var i=0;i<size;i++){
			var id=document.getElementsByName("section${reportType}")[i].id;
			var name="file"+id;
			var fileName=$("#"+name).val();
			if(fileName==""){
				isnull="0";
			}


		}
		if(isnull=="0"){
			$.messager.alert("提示","请上传章节文件")
		}else{
			$("#table4${reportType}").css("display","none");
			$("#table5${reportType}").css("display","");
			$("#opBottom4s${reportType}").css("display","");
			$("#opBottom4${reportType}").css("display","none");

			var mycars = new Array();
			var size = document.getElementsByName("applyer${reportType}").length;
			var namesize=document.getElementsByName("applyers${reportType}").length;
			if(namesize==0){
				for (var j = 0; j < size; j++) {
					var val=document.getElementsByName("applyer${reportType}")[j].value;
					var id=document.getElementsByName("applyer${reportType}")[j].id;
					var index=parseInt(j)+parseInt(1);
					$("#table5${reportType}").append("<input type='hidden' name='applyers${reportType}' value='"+val+"'  />");
					$("#table5${reportType}").append("<input type='hidden'  name='applyersFile${reportType}' value='"+id+"' />");
					var fileType=${reportType};
					if(fileType=="0"){
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\"  name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}else if(fileType=="1"){
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\" name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}else if(fileType=="2"){
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\"  name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}else if(fileType=="3"){
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\" name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}else if(fileType=="4"){
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\"  name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}else{
						$("#table5${reportType}").append("<tr><td><div class='td_div'>申请人:"+val+"</div></td><td><input class=\"input_inputtype\" name='"+id+"' id='applyer"+id+"' type='file' multiple='multipl' /></td></tr>");

					}
				}
			}
		}
	}
	function backtemplate${reportType}(){

		$("#reportStep2${reportType}").css("display","");
		$("#reportStep3${reportType}").css("display","none");

	}
	function gobacktemplate${reportType}(){

		$("#reportStep3${reportType}").css("display","");
		$("#reportStep4${reportType}").css("display","none");

	}

	function template${reportType}(id,synopsis,dimension,tabletype){
		$("#step1_conter_div3${reportType}  div").css("background","#ffffff");
		$("#"+id+${reportType}).css("background-color","#b2d9ff");
		$("#synopsis${reportType}").val(synopsis);
		$("#dimension${reportType}").val(dimension);
		$("#tabletype${reportType}").val(tabletype);
		$("#reportTmeplateId${reportType}").val(id);

	}

	function gotitle${reportType}(){
		$("#table4${reportType}").css("display","");
		$("#opBottom4${reportType}").css("display","");
		$("#opBottom4s${reportType}").css("display","none");
		$("#table5${reportType}").css("display","none");


	}




	function patentList(url, title, id) {
		layout_center_addTabFun({
			id: id,
			title: title,
			closable: true,
			iconCls: "",
			//cache : false,
			href: url,
			queryParams: {},
			//content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
		});
	}

	function importUser${reportType}(){
		debugger

		var reportType=${reportType};
		var size = document.getElementsByName("section${reportType}").length;
		var isnull="1";
		for (var i=0;i<size;i++){
			var id=document.getElementsByName("section${reportType}")[i].id;
			var name="applyer"+id;
			var fileName=$("#"+name).val();
			if(fileName==""){
				isnull="0";
			}


		}
		if(isnull=="0"){
			$.messager.alert("提示","请上传申请人文件");
			return false;
		}else{
	/*		$.messager.progress({
				title:'请稍后',
				msg:'正在生产报告...'
			});*/
			debugger
			if(reportType=="0"){
				$("#form4${reportType}").attr("action","${ctx}/patent/importNavigation.do");
			}else if(reportType=="1"){
				$("#form4${reportType}").attr("action","${ctx}/patent/importWarning.do");
			}else if(reportType=="2"){
				$("#form4${reportType}").attr("action","${ctx}/patent/importPush.do");
			}else if(reportType=="3"){
				$("#form4${reportType}").attr("action","${ctx}/patent/importSearch.do");
			}else if(reportType=="4"){
				$("#form4${reportType}").attr("action","${ctx}/patent/importQuery.do");
			}else {
				$("#form4${reportType}").attr("action","${ctx}/patent/importAnalysis.do");
			}
            $.messager.progress({
                title:'请稍候...',
                text : "正在处理，"});
			jQuery('#form4${reportType}').form('submit',{
				async:false,
				success: function(data){
					var  strToObj = JSON.parse(data);
					if(strToObj.code == 200){
						$.messager.progress('close');
						$.messager.alert("提示","保存成功");
					}else{
						$.messager.progress('close');
						$.messager.alert("错误",strToObj.msg);
					}


				},
			});

		}
	}

	function fileSelect(obj) {
		var files = obj.files;
		var fileId = obj.id;
		var fileUl = $('#' + fileId + 'SelectUl');
		fileUl.empty();
		$.each(files, function (idx, file) {
			addFileNameLi(fileUl, file.name, idx, fileId);
		});
	}

	//在id为test的table的最后增加一行
	function addTRS(id){
		debugger
		tr_id = $("#test>tbody>tr:last").attr("id");
		tr_id++;//alert(tr_id);
		str = "<tr name='"+tr_id+"' id = '"+tr_id+"'><td  width='30%'><input class=\"input_dimension\" name='a' value='' /></td> <td width='30%'><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='增加'  onclick=\"addTRS('test');\"><input class=\"input_dimension\" class='dimension_button' type=\"button\" value='删除'  onclick=\"delTRS('test');\"></td><td width='30%'><input class=\"input_dimension\" name='name' value='' /></td><td width='30%'><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='增加'  onclick=\"addSECOND('test');\"><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='删除'  onclick=\"delSECOND('"+tr_id+"');\"></td></tr>";

		$('#'+id).append(str);
	}


	//删除id为test的table的最后一行
	function delTRS(id){
		tr_id = $("#test>tbody>tr:last").attr("id");

		$('#'+tr_id).remove();
	}
	//二级增加在id为test的table的最后增加一行
	function addSECOND(id){
		debugger
		tr_id = $("#test>tbody>tr:last").attr("id");
		var inputs = $("#"+tr_id).find("input");
		var inpname=inputs.val();
		if(typeof(inpname) == "undefined"){
			inpname="";
		}
		tr_id++;
		str = "<tr id = '"+tr_id+"'><td width='30%'><input class='input_dimension' name='a' value=' "+inpname+" ' /></td><td width='30%'><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='增加'  onclick=\"addTRS('test');\"><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='删除'  onclick=\"delTRS('test');\"></td><td width='30%'><input class='input_dimension'  name='name' value='' /></td><td width='30%'><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='增加'  onclick=\"addSECOND('test');\"><input class=\"input_dimension\"  class='dimension_button' type=\"button\" value='删除'  onclick=\"delSECOND('"+tr_id+"');\"></td></tr>";
		$('#'+id).append(str);
	}
	function delSECOND(id){
		tr_id = $("#test>tbody>tr:last").attr("id");

		$('#'+id).remove();
	}

	function add(){
		debugger
		var tr = $("#test tr"); // 获取table中每一行内容

		var result = []; // 数组
		for (var i = 2; i < tr.length; i++) {// 遍历表格中每一行的内容
			var tds = $(tr[i]).find("td");
			var inputs = $(tr[i]).find("input");
			if (tds.length > 0) {
				result.push({
					"dimensionStair" : $(tds[0]).find("input").val(),
					"dimensionSecond" : $(tds[2]).find("input").val(),
				})
				/*		if(inputs.length>5){
                        var stair=$(tds[0]).find("input").val();
                        result.push({
                            "dimensionStair" : $(tds[0]).find("input").val(),
                            "dimensionSecond" : $(tds[2]).find("input").val(),

                        })
                    }else {
                    result.push({
                        "dimensionStair" : stair,
                        "dimensionSecond" : $(tds[0]).find("input").val(),

                    })
                    }*/
			}
		}
		var jsonData = { // json数据
			"dimensionList" : result
		}
		$.ajax({
			type : "POST",
			url : "${ctx}/dimension/getTableData.do",
			dataType: "json",
			contentType : "application/json",
			data :JSON.stringify(jsonData),// 将json数据转化为字符串
			success : function(data) {
				alert("sss");

			}
		})
	}

	function goReportReview() {
		easyuiUtils.ajaxPostFormForPanel('reportStep4From' , getCurrentTabId());
	}


</script>
<style>
	/*初始化*/
	.bg{
		width: 700px;
		height: 715px;
		margin-top: 10px;
		border: solid 1px black;
	}
	.bgs{
		width: 1400px;
		height: 715px;
		border: solid 1px black;
	}
	.input_title{
		height: 25px;
		font-size: 16px;
		outline:none;
		BORDER-TOP-STYLE: none;
		BORDER-RIGHT-STYLE: none;
		BORDER-LEFT-STYLE: none;
		BORDER-BOTTOM-STYLE:solid
	}
	.strong{
		font-size: 20px;
		color: #0BB20C;
	}
	.title_div{
		width: 100%;
		height: 50px;
		border-bottom: solid 1px;
	}

	.title_div_text{
		height: 100%;
		width: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
		font-size: 17px;
	}
	.title_div_texts{
		height: 100%;
		width: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
		font-size: 17px;
		margin-left: 40%;
	}
	.step1_left_div{
		width: 25%;
		height: 665px;
		float: left;
		border-right: solid 1px;
	}
	.conter_step1_left_div{
		width: 25%;
		height: 613px;
		float: left;
		border-right: solid 1px;
	}
	.step1_conter_div{
		width: 25%;
		height: 613px;
		float: left;
		border-right: solid 0px;
	}
	.add_step_div{
		text-align: center;
		width: 63%;
		height: 150px;
		margin: 0 auto;
		margin-top: 205px;
		border: solid 2px;
		border-radius: 20px;
	}
	.add_step_div_conter{
		text-align: center;
		width: 80%;
		height: 350px;
		margin: 0 auto;
		margin-top: 100px;

	}
	div[name='addTempStep']{
		height: 25%;
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
	}
	.step1_right_div{
		width: 75%;
		height: 200px;
		margin-left: 25%;
	}
	.tip_div{
		font-size: 15px;
		margin-top: 25px;
		margin-left: 20px;
	}
	.tip_diheadlinev{
		font-size: 15px;
		margin-top: 10%;
		margin-left: 5%;
	}
	.temp_type_list{
		border: solid 1px;
		width: 50%;
		margin: 0 5% 5%;
		border-collapse:collapse;
		margin-top: 5%;
	}
	.type_cb{
		width: 10%;
		float: left;
		margin-left: 28%;
	}
	.temp_type_list tr{
		height: 33px;
	}
	.temp_type_list th {

		border-top: 1px solid black;
		border-bottom: 1px solid black;
		border-right: 1px solid black;
		text-align: center;
		font-size: 10px;
	}
	.temp_type_list td {

		border-top: 1px solid black;
		border-bottom: 1px solid black;
		border-right: 1px solid black;
		text-align: center;
		font-size: 10px;
	}
	.td_div {
		text-align: left;
		font-size: 16px;
		width: 250px
	}
	.textareatitle{
		height:80px;
		float: left;
		text-align: center;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.textareatext{
		width:38%;
		height: 80px;
		background-color: #ffffff;
		font-family: sans-serif;
		font-size:16px;
		resize:none;

	}
	.textareatext_max{
		width:38%;
		height: 200px;
		background-color: #ffffff;
		font-family: sans-serif;
		font-size:13px;
		resize:none;

	}
	.type_box{
		width: 90%;
		height: 20px;
		margin: 5% 5% 30px;
	}

	.x1{
		float: left;
		width: 180px;
		margin: 4% 2% 2%;
		height: 600px;
		overflow: scroll;
	}
	.x2{
		float: left;
		width: 450px;
		margin: 5% 2% 2%;
		height: 600px;
	}
	.circle_null{
		width: 10px;
		height: 10px;
		background-color: white;
		border: 1px solid black;
		border-radius: 10px;
		cursor: pointer;
	}

	.type_name{
		width: 58%;
		margin-left: 18px;
		border: solid 2px black;
		height: 30px;
		text-align: center;
		display: flex;
		align-items: center;
		justify-content:center;
		cursor: pointer;
	}
	.type_name_conter{
		width: 58%;
		margin-left: 25%;
		border: solid 2px black;
		height: 30px;
		text-align: center;
		display: flex;
		align-items: center;
		justify-content:center;
		cursor: pointer;
		margin-top: 20px;
	}
	.listTable{
		border: solid 1px;
		width: 90%;
		margin: 0 5% 5%;
		border-collapse:collapse;
	}

	.listTable tr{
		height: 33px;
	}

	.listTable td {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		text-align: center;
		font-size: 15px;
	}
	.page_div{
		height: 100px;
		text-align: center;
		display: flex;
		align-items: center;
		justify-content: center;
		border-top: solid 1px #ccc
	}

	.addinput{
		width: 15%;
		height: 30px;
		border-radius: 10px;
		background: #f2f6fc;
		cursor: pointer;
		margin: 0 1%;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 14px;
		color: #0a1833;
		border: #f2f6fc solid 1px;
		float: left;

	}
	.deleinput{
		width: 15%;
		height: 30px;
		border-radius: 10px;
		background:#eb6565;
		cursor: pointer;
		margin: 0 1%;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 14px;
		color: #009688;
		border: #f2f6fc solid 1px;
		float: left;
	}
	.next{
		width: 15%;
		height: 30px;
		border-radius: 10px;
		background:#63A7E8;
		cursor: pointer;
		margin: 0 5%;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 14px;
		color: #000000;
		border: #f2f6fc solid 1px;
		float: left
	}
	.dimension_button{
		width: 40%;
		height: 30px;
		border-radius: 10px;
		background:#63A7E8;
		cursor: pointer;
		margin: 0 5%;
		display: flex;
		justify-content: center;
		align-items: flex-start;
		font-size: 14px;
		color: #000000;
		border: #f2f6fc solid 1px;
		float: left
	}
	.inputtype{
		height: 35px;
		width: 200px;
		font-size: 16px;
		background: #eee;
		border-radius: 10px;
		border: none;
		padding-left: 10px;
	}
	.input_dimension{
		outline:none ;
		height: 35px;
		width: 200px;
		font-size: 16px;
		background: #ffffff;
		border: none;
		padding-left: 10px;
	}
	.input_inputtype{
		height: 35px;
		width: 200px;
		font-size: 16px;
		background: #ffffee;
		border-radius: 10px;
		border: none;
		padding-left: 10px;
	}
	.font{
		font-size: 17px;font-weight:bold;
	}
	* {margin: 0;padding: 0;list-style: none;}
	/*HI*/
	.top-hello {width: 100%;padding-top: 15px;}
	.top-helloIn {display: flex;justify-content: stretch;align-content: center;width: 10%;height: 180px;border: #dcdfe6 solid 1px;border-radius: 10px;background: #fff;margin: 0 auto -200px;margin-left: -15%}
	.top-helloIn > img {display: block;width: 60px;height: 60px;margin: 5% 1%;}
	.top-helloIn > p {font-size: 16px;color: #909399;}
	.top-helloIns {display: flex;justify-content: stretch;align-content: center;width: 10%;height: 50%;border: #dcdfe6 solid 1px;border-radius: 10px;background: #fff;margin: 0 auto 15px;margin-left: 50%}
	.top-helloInss {display: flex;justify-content: stretch;align-content: center;width: 100%;height: 50px;border: #ffffff solid 1px;border-radius: 10px;background: #fff;margin-left: 0%}

	/*常用模块*/
	.center-common-h3 {width: 100%;align-content: center;margin-left: 4%;margin-top: 4%}
	.center-common-ul {width: 100%;height:20%;display: flex;justify-items: center;align-items: center;margin: 0 5%;}
	.center-common-ul > li {width: 15%;height: 68px;border-radius: 10px;background: #ffffff;cursor: pointer;margin: 0 1%;display: flex;justify-content: center;align-items: center;font-size: 14px;color: #606266;border: #dcdfe6 solid 1px;}
	.center-common-ul li > img {margin-right: 5%;width: 50px;height: 50px}
	.center-common-ul li > span {font-weight: bold;}
	@media screen and (max-width: 800px) {
		.center-common-ul li > span {
			display: none;
		}
	}
	/*统计图*/

	.btn-canva-lr{width: 50%;height: 100%;margin-left: 4%;}
	.btn-canva-lr >h3{margin: 0 0 20px 0;}

	.btn-canvaIn{height:80%;width:88%;border:1px solid #dcdfe6;border-radius:10px;background:#ffffff}

	@media screen and (max-width: 1000px) {
		.btn-canva{
			flex-direction: column;
		}
		.btn-canva-lr{width: 100%;height: 100%;margin-left: 4%;}
	}
</style>
