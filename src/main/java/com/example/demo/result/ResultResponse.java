package com.example.demo.result;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * 接口响应模板类
 *
 * <br><br><br>
 *
 * <p>
 * 属性介绍：
 * </p>
 *
 * <ul>
 *     <li>{@code code}     ：返回码</li>
 *     <li>{@code message}  ：描述信息</li>
 *     <li>{@code success}  ：成功标志</li>
 *     <li>{@code header}   ：响应头</li>
 *     <li>{@code body}     ：响应体</li>
 * </ul>
 *
 * <br>
 *
 * <p>
 *     当 code（返回码）为 0 时，表示成功。此时 success（成功标志）为 {@code true}，表示成功。
 * </p>
 *
 * <p>
 *     反之，当 code（返回码）不为 0 时，表示失败。此时 success（成功标志）为 {@code false}，表示失败。
 * </p>
 *
 * <br><br><br>
 *
 * <p>
 *     如果属性没有值，则属性的值为null，<br>
 *     那么在返回json格式的数据中，将不会出现改属性。<br>
 *     这是因为这个实体类加了
 *     {@code @JsonInclude(JsonInclude.Include.NON_NULL)}
 * </p>
 *
 * <p> since 2.5.3 版本开始，交由业务服务自行处理</p>
 *
 * <br>
 *
 * <p>
 *     特注需要提醒的是，{@code success} 属性，默认是false，<br>
 *     如果你在返回的时候没有使用给定的 {@code success(...)} 方法。<br>
 *     那么，你需要手动修改值。<br>
 *     否则返回的值可能依旧是 {@code false}.<br>
 *     那么在做出判断时，使用该属性，可能不准确。
 * </p>
 *
 * <br>
 *
 * <p>
 *     关于设计的说明：
 * </p>
 *
 * <p>
 *     这是顶层，不能再添加其他属性进行返回。<br>
 *     如果需要，解决方案：<br>
 *     如果是请求相关的，可以添加到 {@code header} 里面，这是一个 {@link Map}<br>
 *     如果是数据，必须保存到body里<br>
 * </p>
 *
 * <br>
 *
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2.5.0
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "接口响应封装", description = "接口响应模板")
public class ResultResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -4206473602305400988L;

    /**
     * 默认操作成功
     */
    private static final ReturnCode SUCCESS = ReturnCode.Default.SUCCESS;

    /**
     * 默认操作失败
     */
    private static final ReturnCode ERROR = ReturnCode.Default.ERROR;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应结果状态，{@code true} 表示成功；{@code false} 表示失败
     */
    private Boolean success = Boolean.FALSE;

    /**
     * 响应体
     */
    private T data;

    /**
     * 构造方法：无参数
     */
    public ResultResponse() {
    }

    /**
     * 操作成功
     *
     * @param <T> {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> success() {
        return new ResultResponse<T>()
                .setSuccess(Boolean.TRUE)
                .setCode(SUCCESS.getCode())
                .setMessage(SUCCESS.getMessage())
                ;
    }

    /**
     * 操作成功
     *
     * @param <T> {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> success(String message) {
        return new ResultResponse<T>()
                .setSuccess(Boolean.TRUE)
                .setCode(SUCCESS.getCode())
                .setMessage(message)
                ;
    }

    /**
     * 操作成功
     *
     * @param body 响应体
     * @param <T>  响应体类型
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> success(T body) {
        return new ResultResponse<T>()
                .setSuccess(Boolean.TRUE)
                .setCode(SUCCESS.getCode())
                .setMessage(SUCCESS.getMessage())
                .setData(body)
                ;
    }

    /**
     * 操作失败
     *
     * @param <T> {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> fail() {
        return new ResultResponse<T>()
                .setCode(ERROR.getCode())
                .setMessage(ERROR.getMessage())
                ;
    }


    /**
     * 操作失败
     *
     * @param message 描述信息
     * @param <T>     {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> fail(String message) {
        message = StringUtils.hasLength(message) ? message : ERROR.getMessage();
        return new ResultResponse<T>()
                .setCode(ERROR.getCode())
                .setMessage(message)
                ;
    }


    /**
     * 操作失败
     *
     * @param returnCode {@link ReturnCode}
     * @param <T>        {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> fail(ReturnCode returnCode) {
        return new ResultResponse<T>()
                .setCode(returnCode.getCode())
                .setMessage(returnCode.getMessage())
                ;
    }

    /**
     * 操作失败
     *
     * @param returnCode {@link ReturnCode}
     * @param message    描述信息
     * @param <T>        {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> fail(ReturnCode returnCode, String message) {
        message = StringUtils.hasLength(message) ? message : returnCode.getMessage();
        return new ResultResponse<T>()
                .setCode(returnCode.getCode())
                .setMessage(message)
                ;
    }


    /**
     * 操作失败
     *
     * @param code    返回码
     * @param message 描述信息
     * @param <T>     {@link Void}
     * @return 响应封装类 {@link ResultResponse}
     */
    public static <T> ResultResponse<T> fail(int code, String message) {
        return new ResultResponse<T>()
                .setCode(code)
                .setMessage(message)
                ;
    }
}