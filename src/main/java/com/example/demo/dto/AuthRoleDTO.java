package com.example.demo.dto;

import com.example.demo.entity.AuthRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "AuthRole对象", description = "权限-角色")
public class AuthRoleDTO {

    @ApiModelProperty("角色 ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色代码")
    private String code;

    @ApiModelProperty("角色描述")
    private String description;

    /**
     * 转换
     *
     * @param authRole 角色
     * @return {@link AuthRoleDTO}
     */
    public static AuthRoleDTO convertOf(AuthRole authRole) {
        AuthRoleDTO authRoleDTO = new AuthRoleDTO();
        BeanUtils.copyProperties(authRole, authRoleDTO);
        return authRoleDTO;
    }
}