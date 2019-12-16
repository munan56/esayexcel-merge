package ml.munan.boot.demo.exceldemo;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.ListMultimap;
import ml.munan.boot.demo.exceldemo.model.ExportDto;
import ml.munan.boot.demo.exceldemo.model.School;
import ml.munan.boot.demo.exceldemo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: munan
 * @Date: 2019/12/16 7:34 下午
 */
@RestController
public class DownLoadController {

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<ExportDto> data = data();
        Map<Long, List<ExportDto>> collect1 = data.stream().collect(Collectors.groupingBy(ExportDto::getId));

        EasyExcel.write(response.getOutputStream(), ExportDto.class).registerWriteHandler(new MyMerger(0,1,collect1)).sheet("模板").doWrite(data);
    }


    public List<ExportDto> data(){
        List<ExportDto> data = new ArrayList<>();
        List<School> test = test();

        test.forEach(s->{

            if (s.getUsers() == null){
                ExportDto exportDto = new ExportDto();
                exportDto.setId(s.getId());
                exportDto.setSchoolName(s.getName());
                data.add(exportDto);
                return;
            }
            s.getUsers().forEach(user -> {
                ExportDto exportDto1 = new ExportDto();
                exportDto1.setId(s.getId());
                exportDto1.setSchoolName(s.getName());
                exportDto1.setId(s.getId());
                exportDto1.setUserName(user.getName());
                exportDto1.setUserAddress(user.getAddress());
                data.add(exportDto1);
            });
        });




        return data;
    }




    public static List<School>  test(){

        List<School> schools = new ArrayList<>();
        Map<Integer,List<User>> map = new HashMap<>();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0){

                users = new ArrayList<>();
            }
            User user = new User();
            user.setId((long) i);
            user.setName("用户名" + i);
            user.setAddress("地址" + i);
            users.add(user);
            map.put(i,users);
        }

        for (int i = 0; i < 5; i++) {
            School school = new School();
            school.setId((long) i);
            school.setName("学校" + i);
            school.setUsers(map.get(i));
            schools.add(school);
        }
        School school = new School();
        school.setId(5L);
        school.setName("学校" + 5);
        school.setUsers(null);
        schools.add(school);
    return schools;

    }

    public static void main(String[] args) {
        List<School> test = test();
        System.out.println(test);
    }


}
