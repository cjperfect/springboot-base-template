package com.cj.snippets.model.vo;

import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "分类请求体")
public class CodeTypeVO {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    // 分类名称
    @ApiModelProperty(value = "分类名称", required = true)
    @NotBlank(message = "分类名称不能为空", groups = {Add.class, Update.class})
    private String typeName;

    // 父级分类
    @ApiModelProperty(value = "父级分类")
    private Long pId;

    // 分类ID
    @ApiModelProperty(value = "创建用户的id")
    private Long userId;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 所有子分类
    private List<CodeTypeVO> children;

}
