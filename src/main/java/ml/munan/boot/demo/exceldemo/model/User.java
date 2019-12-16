package ml.munan.boot.demo.exceldemo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: munan
 * @Date: 2019/12/16 7:30 下午
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6532031563901309705L;

    private Long id;

    private String name;

    private String address;


}
