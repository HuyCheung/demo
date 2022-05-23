package com.example.demo.dto;

import com.example.demo.entity.AuthResource;
import com.example.demo.entity.enums.AuthResourceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 权限-资源
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "AuthResource对象", description = "权限-资源")
public class AuthResourceDTO {

    @ApiModelProperty("资源 ID")
    private Long id;

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("资源代码")
    private String code;

    @ApiModelProperty("资源类型")
    private String type;

    @ApiModelProperty("状态 1:正常 normal 9：过滤 filtering（排除保护-都可以访问这个api）")
    private AuthResourceStatus status;

    @ApiModelProperty("资源描述")
    private String description;

    /**
     * 转换
     *
     * @param AuthResource 资源
     * @return {@link AuthResourceDTO}
     */
    public static AuthResourceDTO convertOf(AuthResource AuthResource) {
        AuthResourceDTO authResourceDTO = new AuthResourceDTO();
        BeanUtils.copyProperties(AuthResource, authResourceDTO);
        return authResourceDTO;
    }
}