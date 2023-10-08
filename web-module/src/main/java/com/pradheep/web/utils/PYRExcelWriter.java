/**
 * 
 */
package com.pradheep.web.utils;

/**
 * This is a utility class to write a excel file for the given array of string.
 * 
 * @author Pradheep
 *
 */
public class PYRExcelWriter {

	/*public File writeToExcel(String sheetName, String fileName, List<List<String>> dataToWrite) {
		File outputFile = new File(sheetName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		AtomicInteger rownum = new AtomicInteger(0);
		dataToWrite.forEach(rowData -> {
			XSSFRow row = sheet.createRow(rownum.getAndAdd(1));
			List<String> rowDataItems = (List<String>) rowData;
			AtomicInteger cellnum = new AtomicInteger(0);
			rowDataItems.forEach(value -> {
				XSSFCell cell = row.createCell(cellnum.getAndAdd(1));
				cell.setCellValue(value);
			});
		});
		try {
			FileOutputStream out = new FileOutputStream(outputFile);
			workbook.write(out);
			out.close();
			System.out.println("File Written successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFile;
	}
	
	public static void main(String args[]) {
		PYRExcelWriter obj = new PYRExcelWriter();
		List<String> data = Arrays.asList("Test","Hello");
		List<List<String>> x = new ArrayList();
		x.add(data);
		obj.writeToExcel("Daily Report", "c://1.xls", x);
	}*/

}
