<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST PAGE</title>
</head>
<body>
	<h1>Hello world!!</h1>
	<div id='downTbl'>
		<table class="" border="1" id='tbl1'>
			<colgroup>
				<col width="3%">
				<col width="130">
				<col width="130">
				<col width="130">
			</colgroup>
			<thead>
				<tr>
					<th>no</th>
					<th>title</th>
					<th>count1</th>
					<th>count2</th>
					<th>total amount</th>
					<th>percent</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<button class="btn_txt_green" type="button">1</button>
					</td>
					<td>book</td>
					<td data-type="Number" data-style='Number'>5,944,728</td>
					<td data-type="Number">32</td>
					<td data-type="Number">5,944,760</td>
					<td data-type="Number">0.99</td>
				</tr>
				<tr>
					<td>
						<button class="btn_txt_green" type="button">2</button>
					</td>
					<td>coffee</td>
					<td>5,918,064</td>
					<td>45</td>
					<td>5,918,109</td>
					<td>0.99</td>
				</tr>
			</tbody>
		</table>
	</div>
	<button id='btn'>download</button>
</body>
</html>
<!-- <script src='testJS.js'></script> -->
<script>
window.addEventListener("load",function(){
	document.getElementById('btn').addEventListener('click', function(){
		_excelDown('엑셀테스트.xls','시트1', 'downTbl')
	})
})
/**
 * 엑셀 다운로드
 * @param fileName 엑셀파일명 (ex. excel.xls)
 * @param sheetName 시트명
 * @param sheetId 	불러올 테이블의 Id String으로
 */
function _excelDown(fileName, sheetName, sheetId) {
	let sheetHtml = document.getElementById(`${sheetId}`).innerHTML;
	let innerExcel = '';
	innerExcel+=`
	<html xmlns:x="urn:schemas-microsoft-com:office:excel">
	    <head>
	        <meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
	        <xml>
	            <x:ExcelWorkbook>
	                <x:ExcelWorksheets>
	                    <x:ExcelWorksheet>
	                        <x:Name>${sheetName}</x:Name>
	                        <x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions>
	                    </x:ExcelWorksheet>
	                </x:ExcelWorksheets>
	            </x:ExcelWorkbook>
	        </xml>
	    </head>
		<body>
		${sheetHtml}
		</body>
	</html>
	`;
	/* 데이터 타입 지정 : 엑셀로 지정한다. */
	let data_type = 'data:application/vnd.ms-excel';
	/* blob 객체를 생성한다. 이때 XML 형식으로 되어있는  innerExcel을 배열로 넣어준다.
	 * 주로 배열형태의 DOMString 형태로 처음에 인자를 받는다.
	 * 두번째로 옵션을 지정해주는것인다.  MIME로 지정해준다. 
	 *  */
	let blob = new Blob(
						[innerExcel]
						,	{
								type : "application/csv;charset=utf-8;"
							}
						);
	/* blob이 다운로드 이벤트가 일어나지기 위한 임시 객체 a를 만든다. */
	let tmpEle = window.document.createElement('a');
	/* blob을 다운로드 시킬 URL을 임시로 만든 a태그 안에 href를 셋팅해준다. */
	tmpEle.href = window.URL.createObjectURL(blob);
	/* 임시태그받는 파일이름을 파라미터로 받아온 파일이름을 넣어준다. */
	tmpEle.download = fileName;
	/* 지금껏 설정한 a태그를 document의 바디 안에 임시로 삽입한다. */
	document.body.appendChild(tmpEle);
	/* 그리고 일부러 클릭이 되어지는 이벤트를 넣어서 자동으로 다운로드되어지는것 처럼 연출한다. */
	tmpEle.click();
	/* 클릭이벤트 종료 후 임시로 만들어진 a태그는 삭제한다.  */
	document.body.removeChild(tmpEle);
}
</script>