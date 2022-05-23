package com.example.demo.dto;

import com.example.demo.entity.AuthUser;
import com.example.demo.entity.enums.AuthUserCreateWhere;
import com.example.demo.entity.enums.AuthUserSex;
import com.example.demo.entity.enums.AuthUserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 权限-用户
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "AuthUser对象", description = "权限-用户")
public class AuthUserDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("用户名（昵称）")
    private String username;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("电话号码（唯一）")
    private String phone;

    @ApiModelProperty("电子邮件（唯一）")
    private String email;

    @ApiModelProperty("性别(1.男 man 2.女 woman )")
    private AuthUserSex sex;

    @ApiModelProperty("账户状态（1.正常 normal 2.锁定 locked 3.删除 deleted  4.非法 illegal）")
    private AuthUserStatus status;

    @ApiModelProperty("创建来源(1.web 2.android 3.ios 4.win 5.mac 6.linux)")
    private AuthUserCreateWhere createWhere;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("有效角色")
    private List<AuthRoleDTO> roles;

    @ApiModelProperty("有效权限")
    private List<AuthResourceDTO> resources;

    /**
     * 转换
     *
     * @param authUser 用户
     * @return {@link AuthUserDTO}
     */
    public static AuthUserDTO convertOf(AuthUser authUser) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        BeanUtils.copyProperties(authUser, authUserDTO);
        return authUserDTO;
    }
}