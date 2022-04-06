package com.example.demo.result;

/**
 * 构造者模式接口
 *
 * @author Huy Cheung
 * @date 2022/04/06
 */
public interface IBuilder<T> {

    /**
     * 构建一个对象
     *
     * @return 构建对象
     */
    T build();

}