import com.unoveo.calcUtils.CalcUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;


public class TestExcel {

//    @BeforeAll
//    public static void beforeTestCaseTwoClass() {
//        System.out.println();
//        System.out.println("Executing Test methods of TestExcel class…");
//    }

    @Test
    public void testA(){
        System.out.println("Hello");
    }

    @Test
    public void excelTest2() throws IOException {

        CalcUtils calcUtils = new CalcUtils();

            //create file input string will take input from file.
            FileInputStream fis = new FileInputStream(new File("testing2.xlsx"));

            //create new workbook object to register the fis object.
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //get the first sheet from workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // attaching the iterator to sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //iterating all rows of sheet


            while (rowIterator.hasNext()){
                XSSFRow row = (XSSFRow) rowIterator.next();

                Iterator <Cell> cellIterator = row.cellIterator();

                String input = null;
                String expected = null;
                String testNo =null;

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (cell.getRowIndex() > 0) {
                        if (cell.getColumnIndex() == 0) {
                            testNo = cell.getStringCellValue();
                        } else if (cell.getColumnIndex() == 1) {
                            input = cell.getStringCellValue();
                        } else if (cell.getColumnIndex() == 2) {
                            expected = cell.getStringCellValue();
                        }
                    }

//                switch (cell.getCellType()){
//                    case NUMERIC :
//                        System.out.print(cell.getNumericCellValue()+ "\t\t");
//                        break;
//                    case STRING :
//                        System.out.print(cell.getStringCellValue()+ "\t\t");
//                }

                }
                if(testNo!=null){
                    String str = String.valueOf(calcUtils.calculate(input));
                    assertEquals(expected,str);
                    System.out.println("|| testNo > " +testNo+" || expected > " +expected+" || result >> "+ str);
                }
                System.out.println();
            }
            fis.close();
        }

    @Test
    public void excelTest1() throws IOException {

        //create file input string will take input from file.
        FileInputStream fis = new FileInputStream(new File("testing1.xlsx"));

        //create new workbook object to register the fis object.
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //get the first sheet from workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // attaching the iterator to sheet
        Iterator<Row> rowIterator = sheet.iterator();

        //iterating all rows of sheet
        while (rowIterator.hasNext()){
            XSSFRow row = (XSSFRow) rowIterator.next();

            Iterator <Cell> cellIterator = row.cellIterator();

            String input1 = null;
            String input2 = null;
            String operator = null;
            String testNo = null;
            String expected = null;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getRowIndex() > 0) {
                    if (cell.getColumnIndex() == 0) {
                        testNo = cell.getStringCellValue();
                    } else if (cell.getColumnIndex() == 1) {
                        input1 = cell.getStringCellValue();
                    } else if (cell.getColumnIndex() == 2) {
                        input2 = cell.getStringCellValue();
                    } else if (cell.getColumnIndex()==3){
                        operator = cell.getStringCellValue();
                    } else if (cell.getColumnIndex()==4){
                        expected = cell.getStringCellValue();
                    }
                }
            }
            if(testNo!=null){
               String result = String.valueOf(CalcUtils.doCalculate(Float.parseFloat(input1), Float.parseFloat(input2),operator));
                assertEquals(expected,result);
                System.out.println("|| testNo > " +testNo+" || input1 > " +input1+" || input2 >> "+ input2+" operator >>"+operator +"result :"+result);
            }
            System.out.println();
        }
        fis.close();
    }



}
