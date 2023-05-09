package providers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Data provider.
 */
public class DataProvider {
    /**
     * параметры запроса для создания новой доски трелло.
     *
     * @return the object [ ]
     */
    @org.testng.annotations.DataProvider
    public static Object[] boardQueryParams() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "KanbanTool");

        Object[] objData = new Object[1];
        objData[0] = data;

        return objData;
    }

    /**
     * Параметры для создания трелло колонки.
     *
     * @return the object [ ]
     */
    @org.testng.annotations.DataProvider
    public static Object[] setListQueryParams(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "Backlog");

        Object[] objData = new Object[1];
        objData[0] = queryParams;

        return objData;
    }

    /**
     * Параметры для отправки запроса.
     * Подразумевается, что параметры будут добавлены в тесте
     *
     * @return the object [ ]
     */
    @org.testng.annotations.DataProvider
    public static Object[] params(){
        Map<String, String> queryParams = new HashMap<>();

        Object[] objData = new Object[1];
        objData[0] = queryParams;

        return objData;
    }



    /**
     * Параметры для отправки запроса установки срока выполнения карточки.
     *
     * @return the object [ ]
     */
    @org.testng.annotations.DataProvider
    public static Object[] getNextDay(){
        Map<String, String> queryParams = new HashMap<>();
        LocalDate nextDay = LocalDate.now().plusDays(1);

        queryParams.put("due", nextDay.toString());

        Object[] objData = new Object[1];
        objData[0] = queryParams;

        return objData;
    }

    /**
     * Параметры для отправки запроса для изменения карточки.
     *
     * @return the object [ ]
     */
    @org.testng.annotations.DataProvider
    public static Object[] setUpdateCardParams(){
        Map<String, String> queryParams = new HashMap<>();

        queryParams.put("desc", "Тут будет отмечаться прогресс обучения");

        Object[] objData = new Object[1];
        objData[0] = queryParams;

        return objData;
    }

}
