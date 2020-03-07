package cn.net.topnet.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with CosmosRay
 *
 * @author CosmosRay
 * @date 2020/03/06
 * Funciton:
 */
public interface FreemarkerService {
    Map<String, Object> exportWordFile(HttpServletResponse response);
}
