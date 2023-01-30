//import org.testng.annotations.DataProvider;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DP {
//    @DataProvider
//    public static Object[][] setBoardQueryParams() {
//        Map<String, String> data = new HashMap<>();
//        data.put("name", "KanbanTool");
//
//        Object[][] objData = new Object[1][1];
//        objData[0][0] = data;
//
//        return objData;
//    }
//
//    @DataProvider
//    public static Object[][] setListQueryParams(){
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("name", "Backlog");
//
//        Object[][] objData = new Object[1][1];
//        objData[0][0] = queryParams;
//
//        return objData;
//    }
//
//    @DataProvider
//    public static Object[][] params(){
//        Map<String, String> queryParams = new HashMap<>();
//
//        Object[][] objData = new Object[1][1];
//        objData[0][0] = queryParams;
//
//        return objData;
//    }
//
//
//    @DataProvider
//    public static Object[][] getNextDay(){
//        Map<String, String> queryParams = new HashMap<>();
//        LocalDate nextDay = LocalDate.now().plusDays(1);
//
//        queryParams.put("due", nextDay.toString());
//
//        Object[][] objData = new Object[1][1];
//        objData[0][0] = queryParams;
//
//        return objData;
//    }
//
//    @DataProvider
//    public static Object[][] setUpdateCardParams(){
//        Map<String, String> queryParams = new HashMap<>();
//
//        queryParams.put("desc", "Тут будет отмечаться прогресс обучения");
//
//        Object[][] objData = new Object[1][1];
//        objData[0][0] = queryParams;
//
//        return objData;
//    }
//
//}
