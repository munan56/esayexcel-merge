package ml.munan.boot.demo.exceldemo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: munan
 * @Date: 2019/12/16 7:28 下午
 */
@Data
public class School implements Serializable {
    private static final long serialVersionUID = -1480142916883821481L;
    private Long id;

    private String name;

    private List<User> users;


}
