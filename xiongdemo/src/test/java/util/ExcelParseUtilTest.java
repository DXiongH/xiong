package util;

import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * ExcelParseUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/08/2018</pre>
 */
public class ExcelParseUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: read(InputStream inputStream, RowParser<T> rowParser)
     */
    @Test
    public void testRead() throws Exception {
//TODO: Test goes here...
        InputStream inputStream = new FileInputStream("src/main/resources/test.xlsx");
        List<String> stringList = ExcelParseUtil.read(inputStream, new ExcelParseUtil.RowParser<String>() {
            public void checkHeaderRow(Row row) throws Exception {
                if (row.getPhysicalNumberOfCells() != 1) {
                    throw new Exception();
                }
                if (!"测试".equals(getCellValue(row.getCell(0)))) {
                    throw new Exception();
                }
            }

            public String parseRow(Row row) {
                return getCellValue(row.getCell(0));
            }
        });
        System.out.println(stringList);
    }

    /**
     * Method: checkHeaderRow(Row row)
     */
    @Test
    public void testCheckHeaderRow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: parseRow(Row row)
     */
    @Test
    public void testParseRow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getCellValue(Cell cell)
     */
    @Test
    public void testGetCellValue() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: readSheet(Sheet sheet, RowParser<T> rowParser)
     */
    @Test
    public void testReadSheet() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ExcelParseUtil.getClass().getMethod("readSheet", Sheet.class, RowParser<T>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: checkSheet(Sheet sheet)
     */
    @Test
    public void testCheckSheet() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ExcelParseUtil.getClass().getMethod("checkSheet", Sheet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
