//package com.hengpeng.util;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.openxml4j.opc.PackageAccess;
//import org.apache.poi.ss.usermodel.BuiltinFormats;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
//import org.apache.poi.xssf.eventusermodel.XSSFReader;
//import org.apache.poi.xssf.model.StylesTable;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.xml.sax.Attributes;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.XMLReader;
//import org.xml.sax.helpers.DefaultHandler;
//
///** 
// * @ClassName: ExcelUtil 
// * @Description: xlsx文件处理
// * @author: zhangwei
// * @date: 2017年9月29日 上午7:11:34  
// */
//public class ExcelUtil {
//
//    private OPCPackage xlsxPackage;
//    private int minColumns;
//    private PrintStream output;
//    private String sheetName;
//
//    public ExcelUtil(OPCPackage pkg, PrintStream output, String sheetName, int minColumns) {
//        this.xlsxPackage = pkg;
//        this.output = output;
//        this.minColumns = minColumns;
//        this.sheetName = sheetName;
//    }
//
//    public List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream)  
//            throws IOException, ParserConfigurationException, SAXException {  
//        InputSource sheetSource = new InputSource(sheetInputStream);  
//        SAXParserFactory saxFactory = SAXParserFactory.newInstance();  
//        SAXParser saxParser = saxFactory.newSAXParser();
//        XMLReader sheetParser = saxParser.getXMLReader();
//        MyXSSFSheetHandler handler = new MyXSSFSheetHandler(styles, strings, this.minColumns, this.output);  
//        sheetParser.setContentHandler(handler);
//        sheetParser.parse(sheetSource);
//
//        return handler.getRows();
//    }
//
//	public List<String[]> process() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
//		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
//		XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
//		List<String[]> list = null;
//		StylesTable styles = xssfReader.getStylesTable();
//		XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
//		// int index = 0;
//		while (iter.hasNext()) {
//			InputStream stream = iter.next();
//			String sheetNameTemp = iter.getSheetName();
//			if (this.sheetName.equals(sheetNameTemp)) {
//				list = processSheet(styles, strings, stream);
//				stream.close();
//				// ++index;
//			}
//		}
//		return list;
//	}
//
//    /*================================START 内部类=============================*/
//    class MyXSSFSheetHandler extends DefaultHandler {
//        private StylesTable stylesTable;  
//        private ReadOnlySharedStringsTable sharedStringsTable;  
//        private final PrintStream output;  
//        private final int minColumnCount;  
//        private boolean vIsOpen;  
//        private xssfDataType nextDataType;  
//        private short formatIndex;  
//        private String formatString;  
//        private final DataFormatter formatter;  
//        private int thisColumn = -1;  
//        private int lastColumnNumber = -1;  
//        private StringBuffer value;  
//        private String[] record;  
//        private List<String[]> rows = new ArrayList<String[]>();  
//        private boolean isCellNull = false;  
//
//        public MyXSSFSheetHandler(StylesTable styles, ReadOnlySharedStringsTable strings, int cols, PrintStream target) {
//            this.stylesTable = styles;
//            this.sharedStringsTable = strings;
//            this.minColumnCount = cols;
//            this.output = target;
//            this.value = new StringBuffer();
//            this.nextDataType = xssfDataType.NUMBER;
//            this.formatter = new DataFormatter();
//            record = new String[this.minColumnCount];
//            rows.clear();// 每次读取都清空行集合
//        }
//
//        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
//            if ("inlineStr".equals(name) || "v".equals(name)) {
//                vIsOpen = true;
//                // Clear contents cache
//                value.setLength(0);
//            }
//            // c => cell
//            else if ("c".equals(name)) {
//                // Get the cell reference
//                String r = attributes.getValue("r");
//                int firstDigit = -1;
//                for (int c = 0; c < r.length(); ++c) {
//                    if (Character.isDigit(r.charAt(c))) {
//                        firstDigit = c;
//                        break;
//                    }
//                }
//                thisColumn = nameToColumn(r.substring(0, firstDigit));
//
//                // Set up defaults.
//                this.nextDataType = xssfDataType.NUMBER;
//                this.formatIndex = -1;
//                this.formatString = null;
//                String cellType = attributes.getValue("t");
//                String cellStyleStr = attributes.getValue("s");
//                if ("b".equals(cellType))
//                    nextDataType = xssfDataType.BOOL;
//                else if ("e".equals(cellType))
//                    nextDataType = xssfDataType.ERROR;
//                else if ("inlineStr".equals(cellType))
//                    nextDataType = xssfDataType.INLINESTR;
//                else if ("s".equals(cellType))
//                    nextDataType = xssfDataType.SSTINDEX;
//                else if ("str".equals(cellType))
//                    nextDataType = xssfDataType.FORMULA;
//                else if (cellStyleStr != null) {
//                    // It's a number, but almost certainly one
//                    // with a special style or format
//                    int styleIndex = Integer.parseInt(cellStyleStr);
//                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
//                    this.formatIndex = style.getDataFormat();
//                    this.formatString = style.getDataFormatString();
//                    if (this.formatString == null) this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
//                }
//            }
//        }
//
//        public void endElement(String uri, String localName, String name) throws SAXException {
//            String thisStr = null;
//            // v => contents of a cell
//            if ("v".equals(name)) {
//                // Process the value contents as required.
//                // Do now, as characters() may be called more than once
//                switch (nextDataType) {
//                case BOOL:
//                    char first = value.charAt(0);
//                    thisStr = first == '0' ? "FALSE" : "TRUE";
//                    break;
//                case ERROR:
//                    thisStr = "\"ERROR:" + value.toString() + '"';
//                    break;
//                case FORMULA:
//                    // A formula could result in a string value,
//                    // so always add double-quote characters.
//                    thisStr = '"' + value.toString() + '"';
//                    break;
//                case INLINESTR:
//                    // TODO: have seen an example of this, so it's untested.
//                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
//                    thisStr = '"' + rtsi.toString() + '"';
//                    break;
//                case SSTINDEX:
//                    String sstIndex = value.toString();
//                    try {
//                        int idx = Integer.parseInt(sstIndex);
//                        XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
//                        //////////zw/////thisStr = '"' + rtss.toString() + '"';
//                        thisStr = String.valueOf(rtss);
//                    }catch (NumberFormatException ex) {
//                        output.println("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
//                    }
//                    break;
//                case NUMBER:
//                    String n = value.toString();
//                    // 判断是否是日期格式
//                    if(HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
//                        Double d = Double.parseDouble(n);
//                        Date date=HSSFDateUtil.getJavaDate(d);
//                        thisStr=formateDateToString(date);
//                    }else if (this.formatString != null)
//                        thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
//                    else
//                        thisStr = n;
//                    break;
//                default:  
//                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
//                    break;
//                }
//
//                // Output after we've seen the string contents
//                // Emit commas for any fields that were missing on this row
//                if(lastColumnNumber == -1) {
//                    lastColumnNumber = 0;
//                }
//                //判断单元格的值是否为空
//                if (thisStr == null || "".equals(isCellNull)) {
//                    isCellNull = true;// 设置单元格是否为空值
//                }
//                record[thisColumn] = thisStr;
//                // Update column
//                if (thisColumn > -1) lastColumnNumber = thisColumn;
//            } else if ("row".equals(name)) {
//            	//读到一行末尾
//                // Print out any missing commas if needed
//                if (minColumns > 0) {
//                    // Columns are 0 based
//                    if (lastColumnNumber == -1) {
//                        lastColumnNumber = 0;
//                    }
//                    //START 空行忽略
//                    boolean isBlank = true;
//                    for (String f : record) {
//						if(StringUtils.isNotBlank(f)){
//							isBlank = false;
//							break;
//						}
//					}
//                    if(!isCellNull && !isBlank){
//                        rows.add(record.clone());
//                        isCellNull = false;
//                        for (int i = 0; i < record.length; i++) {
//                            record[i] = null;
//                        }                    	
//                    }
//                    //END 空行忽略
//                }
//                lastColumnNumber = -1;
//            }
//        }
//
//        public List<String[]> getRows() {
//            return rows;
//        }
//
//        public void setRows(List<String[]> rows) {
//            this.rows = rows;
//        }
//
//        public void characters(char[] ch, int start, int length) throws SAXException {
//            if (vIsOpen) value.append(ch, start, length);
//        }
//
//        private int nameToColumn(String name) {
//            int column = -1;
//            for (int i = 0; i < name.length(); ++i) {
//                int c = name.charAt(i);
//                column = (column + 1) * 26 + c - 'A';
//            }
//            return column;
//        }
//
//        private String formateDateToString(Date date) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化日期
//            return sdf.format(date);
//        }
//    }
//
//    enum xssfDataType {  
//        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,  
//    }
//
//    /*================================END 内部类=============================*/
//
//
//	public static List<String[]> readerExcel(String filePath, String sheetName, int minColumns) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
//        OPCPackage p = OPCPackage.open(filePath, PackageAccess.READ);
//        ExcelUtil excelUtil = new ExcelUtil(p, System.out, sheetName, minColumns);  
//        List<String[]> list = excelUtil.process();
//        p.close();
//
//        return list;
//	}
//
//	public static void writeExcel(String path, String fileName, List<String[]> ssList) throws Exception {
//		if(ssList==null || ssList.size()<1) {
//			return;
//		}
//
//		FileUtils.forceMkdir(new File(path));
//
//		String[] titleArray = ssList.get(0);
//		int columeCount = titleArray.length;
//
//		Workbook workbook = null;
//		try {
//			workbook = new XSSFWorkbook();
//			Sheet sheet = workbook.createSheet("Sheet1");
//
//			for(int i=0; i<ssList.size(); i++) {
//				String[] ss = ssList.get(i);
//				if(ss==null || ss.length==0){
//					continue;
//				}
//
//				Row row = sheet.createRow(i);
//				for (int j = 0; j < columeCount; j++) {
//					row.createCell(j);
//				}
//
//			    for(int j=0;j<ss.length;j++) {
//			    	row.getCell(j).setCellValue(ss[j]);
//			    }
//			}
//
//			//写到磁盘上
//			FileOutputStream fileOutputStream = null;
//			try {
//			    fileOutputStream = new FileOutputStream(Paths.get(path, fileName).toFile());
//			    workbook.write(fileOutputStream);
//			    fileOutputStream.close();
//			} catch (FileNotFoundException e) {
//			    e.printStackTrace();
//			} catch (IOException e) {
//			    e.printStackTrace();
//			} finally {
//				if(fileOutputStream!=null){
//					try {
//						fileOutputStream.close();
//					} catch (IOException e) {
//					}
//				}
//			}
//		} finally {
//			if(workbook!=null){
//				try {
//					workbook.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//	}
//	
//	public static void main(String[] args) throws Exception {
//		List<String[]> list = ExcelUtil.readerExcel("D:\\file\\lianwa\\excel\\test.xlsx", "Sheet1", 10);
//
//		for (String[] s : list) {
//			System.out.println(String.format("%20s%20s%20s%20s%20s%20s", s[0], s[1], s[2], s[3], s[4], s[5]));
//		}
//	}
//}  