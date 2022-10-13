package com.org.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wujia {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT, value = "create_time")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time")
    private LocalDateTime updateTime;
    @Version //乐观锁注解
    private int version;
    //逻辑删除
    private Integer deletede;

}
