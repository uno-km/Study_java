window.addEventListener('load', function(){
	document.getElementById('btn').addEventListener('click',function(){
		_tablesToExcel(['tbl1','tbl2', 'tbl3'], ['시트명1','시트명2', '시트명3'], '통계.xls');
	})
})
var tmplWorkbookXML = `
	<?xml version="1.0"?>
		<?mso-application progid="Excel.Sheet"?>
			<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">
				<DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
					<Author>serpiko</Author>
					<Created>{created}</Created>
				</DocumentProperties>
				<Styles>
					<Style ss:ID="Currency">
						<NumberFormat ss:Format="Currency">
						</NumberFormat>
					</Style>
					<Style ss:ID="Date">
						<NumberFormat ss:Format="Medium Date">
						</NumberFormat>
					</Style>
					<Style ss:ID="Number">
						<NumberFormat ss:Format="#,##0_ ">
						</NumberFormat>
					</Style>
					</Styles>
					{worksheets}
				</Workbook>`;
var tmplWorksheetXML = `
				<Worksheet ss:Name="{nameWS}">
					<Table ss:DefaultColumnWidth="100">
						{rows}
					</Table>
				</Worksheet>`;
var tmplCellXML = `
				<Cell{attributeStyleID}{attributeFormula}>
					<Data ss:Type="{nameType}">{data}</Data>
				</Cell>`;
function base64(sheetXML) {
	return window.btoa(unescape(encodeURIComponent(sheetXML)))
}
function format(s, c) {
	return s.replace(/{(\w+)}/g, function(m, p) {
		return c[p];
	})
}

function _tablesToExcel(tables, wsnames, wbname) {
	let ctx = "";
	let workbookXML = "";
	let worksheetsXML = "";
	let rowsXML = "";
	for (let i = 0; i < tables.length; i++) {
		if (!tables[i].nodeType) 
		{
			tables[i] = document.getElementById(tables[i]);
		}
		for (let j = 0; j < tables[i].rows.length; j++) 
		{
			rowsXML += '<Row>'
			for (let k = 0; k < tables[i].rows[j].cells.length; k++) 
			{
				let dataType = tables[i].rows[j].cells[k].getAttribute("data-type");
				let dataStyle = tables[i].rows[j].cells[k].getAttribute("data-style");
				let dataValue = tables[i].rows[j].cells[k].getAttribute("<em></em>data-value");
				dataValue = (dataValue) ? dataValue
						: tables[i].rows[j].cells[k].innerHTML;
				let dataFormula = tables[i].rows[j].cells[k].getAttribute("data-formula");
				dataFormula = (dataFormula) ? dataFormula 
						: (dataType == 'DateTime') ? dataValue
								: null;
				ctx = {
					attributeStyleID : (dataStyle == 'Currency'
							|| dataStyle == 'Date' || dataStyle == 'Number') ? ' ss:StyleID="'
							+ dataStyle + '"'
							: '',
					nameType : (dataType == 'Number'
							|| dataType == 'DateTime'
							|| dataType == 'Boolean' || dataType == 'Error') ? dataType
							: 'String',
					data : (dataFormula) ? '' : dataValue,
					attributeFormula : (dataFormula) ? ' ss:Formula="'
							+ dataFormula + '"'
							: ''
				};
				rowsXML += format(tmplCellXML, ctx);
			}
			rowsXML += '</Row>'
		}
		ctx = {
			rows : rowsXML,
			nameWS : wsnames[i] || 'Sheet' + i
		};
		worksheetsXML += format(tmplWorksheetXML, ctx);
		rowsXML = "";
	}
	ctx = {
		created : (new Date()).getTime(),
		worksheets : worksheetsXML
	};
	workbookXML = format(tmplWorkbookXML, ctx);

	let link = document.createElement("a");
	link.href = 'data:application/vnd.ms-excel;base64,' + base64(workbookXML);
	link.download = wbname;
	document.body.appendChild(link);
	link.click();
	document.body.removeChild(link);
};