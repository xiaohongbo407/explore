package com.explore.web.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: explore
 * @description: jsontest
 * @author: XiaoHongBo
 * @create: 2018-03-28 17:23
 **/
public class JsonTest {

    public static void main(String[] args) throws IOException, ParseException {
       /* ObjectMapper mapper = new ObjectMapper();

        ResponseData<List<Thingblock>> responseData = new ResponseData<>();

        List<Thingblock> thingblocks = new ArrayList<>();
        Thingblock th1 = new Thingblock();
        th1.setBlockId("1");
        th1.setBlockName("1name");
        thingblocks.add(th1);
        responseData.setResult(thingblocks);

        String json = mapper.writeValueAsString(responseData);
        System.out.println(json);

        ResponseData<List<Thingblock>> responseResult = mapper.readValue(json,new TypeReference<ResponseData<List<Thingblock>>>(){});
        System.out.println(responseResult);*/
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
        System.out.println(d.getTime());
    }


}
