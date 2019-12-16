package ml.munan.boot.demo.exceldemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: munan
 * @Date: 2019/12/16 7:35 下午
 */
@Data
public class ExportDto implements Serializable {
    private static final long serialVersionUID = 5366032235065066720L;

    @ExcelProperty("学校Id")
    private Long id;

    @ExcelProperty("学校名字")
    private String schoolName;

    @ExcelProperty("用户名")
    private String userName;

    @ExcelProperty("用户主旨")
    private String userAddress;




}
