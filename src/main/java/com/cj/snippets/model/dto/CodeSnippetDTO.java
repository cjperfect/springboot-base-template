package com.cj.snippets.model.dto;

import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "代码片段请求体")
public class CodeSnippetDTO {
    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    // 标题
    @ApiModelProperty(value = "标题", required = true)
    @NotBlank(message = "标题不能为空", groups = {Add.class, Update.class})
    private String title;

    // 简要
    @ApiModelProperty(value = "简要")
    private String summary;

    // 内容
    @ApiModelProperty(value = "内容", required = true)
    @NotBlank(message = "内容不能为空", groups = {Add.class, Update.class})
    private String content;

    // 用户ID
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空", groups = {Add.class, Update.class})
    private Long userId;

    // 分类ID
    @ApiModelProperty(value = "分类ID")
    private Long TypeId;
}
