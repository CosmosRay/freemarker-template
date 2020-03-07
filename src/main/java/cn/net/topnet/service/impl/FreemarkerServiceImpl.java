package cn.net.topnet.service.impl;

import cn.net.topnet.service.FreemarkerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with CosmosRay
 *
 * @author CosmosRay
 * @date 2020/03/06
 * Funciton:
 */
@Service
public class FreemarkerServiceImpl implements FreemarkerService {
    /**
     * word文件导出
     */
    @Override
    public Map<String, Object> exportWordFile(HttpServletResponse response) {
        /** 用于组装word页面需要的数据 */
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
        dataMap.put("title", "省直机关效能季报");
        dataMap.put("TSyear", "2020");
        dataMap.put("TSnum", "1");
        /*List<Map<String, Object>> listInfo = new ArrayList<Map<String, Object>>();
        BigDecimal num = new BigDecimal ("0");
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if(list.size() -1 < i) {
                map.put("index","");
                map.put("sbtitle","");
                map.put("add", "");
                map.put("reduce","");
            }else {
                map.put("index","");
                map.put("sbtitle","");
                map.put("add", "");
                map.put("reduce", "");
            }
            listInfo.add(map);
        }*/
        //计算总共分数
        dataMap.put("TStotal", "5");
        dataMap.put("TSsum", "6");
        return dataMap;
    }
}
