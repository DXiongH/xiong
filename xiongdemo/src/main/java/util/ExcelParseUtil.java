package util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格解析工具类
 *
 * @author XiongHuan
 */
public class ExcelParseUtil {

    /**
     * 需要实现表头检查和行解析
     *
     * @param <T>
     * @author XiongHuan
     */
    public abstract static class RowParser<T> {
        /**
         * 检查标题行
         *
         * @param row
         */
        public abstract void checkHeaderRow(Row row) throws Exception;

        /**
         * 解析行內容
         *
         * @param row
         * @return
         */
        public abstract T parseRow(Row row);

        protected String getCellValue(Cell cell) {
            String cellValue = "";
            if (null == cell) {
                return cellValue;
            }
            DecimalFormat df = new DecimalFormat("#");
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString().trim();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    cellValue = df.format(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                default:
                    cellValue = "";
                    break;
            }
            return cellValue;
        }
    }

    /**
     * 读Excel文件流
     *
     * @param inputStream
     * @param rowParser
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> read(InputStream inputStream, RowParser<T> rowParser) throws Exception {
        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw new Exception();
        }
        if (null == wb) {
            return null;
        }
        Sheet sheet = wb.getSheetAt(0);
        return readSheet(sheet, rowParser);
    }

    private static <T> List<T> readSheet(Sheet sheet, RowParser<T> rowParser) throws Exception {
        checkSheet(sheet);

        rowParser.checkHeaderRow(sheet.getRow(0));

        int rows = sheet.getPhysicalNumberOfRows();
        List<T> list = new ArrayList<T>(rows);
        for (int i = 1;
             i < rows;
             i++) {
            Row row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            T t = rowParser.parseRow(row);
            if (null == t) {
                continue;
            }
            list.add(t);
        }
        return list;
    }

    private static void checkSheet(Sheet sheet) throws Exception {
        if (null == sheet) {
            throw new Exception();
        }
        if (0 == sheet.getPhysicalNumberOfRows() || 1 == sheet.getPhysicalNumberOfRows()) {
            throw new Exception();
        }
        if (null == sheet.getRow(0)) {
            throw new Exception();
        }
    }

}
