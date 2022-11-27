package controller.driver;

import javax.servlet.http.HttpSession;

public class DriverSessionUtils {
    public static final String DRIVER_SESSION_KEY = "driverId";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginDriverId(HttpSession session) {
        String driverId = (String)session.getAttribute(DRIVER_SESSION_KEY);
        return driverId;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginDriverId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 driver의 ID가 driverStrId인지 검사 */
    public static boolean isLoginDriver(String driverStrId, HttpSession session) {
        String loginDriver = getLoginDriverId(session);
        if (loginDriver == null) {
            return false;
        }
        return loginDriver.equals(driverStrId);
    }
}
