package com.cj.snippets.model.dto;

import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "分类请求体")
public class CodeTypeDTO {
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
}
