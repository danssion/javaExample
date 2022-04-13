package com.danssion.java.serialize.protobuf.com.danssion.java;

import com.danssion.java.StudentProtos;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/3/20 下午9:47
 * @desc protobuf 序列化的使用
 * 任何字段包含三部分
 * tag
 * length（可选）
 * value
 *
 * tag 值 =  field_number << 3 | wire_type
 * 示例中 ：
 * name 的 tag =  1 << 3 | 2 = 10
 * age 的 tag =  2 << 3 | 0 = 16
 *
 * wire_type:
 * 0  varint ->  T-V  ->  int32  int64  bool  enum sint32 (负数)
 * 1  64-bit -> T-V -> fixed32 sfixed32  double
 * 2  length-delimit  ->  T-L-V (tag-length-value) ->  string  bytes embedded  messages packed ,,,,
 * 3 / 4  废弃
 * 5  32-bit  ->  T-V ->  fixed32 sfixed32  float
 *
 * protobuf 设计思想
 * 任何字段，包含三属性：
 * 类型、名字、值
 *
 * varint - protobuf 中针对正整数的压缩
 *
 * zigzag - 针对负整数的压缩
 *
 */
public class ProtobufSerializer {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        StudentProtos.Student student = StudentProtos.Student.newBuilder().setAge(19).setName("Dx").build();
        byte[] by = student.toByteArray();
        System.out.println(by.length); //数据压缩后  6 字符

        //10(tag)  2(length)  [68 120](Dx) 16 18
        for (int i =0; i < by.length ; i++) {
            System.out.print(by[i]+" ");
        }
        System.out.println();
//        int a = (2 << 3) | 2;
//        System.out.println(a);
        StudentProtos.Student student1 = StudentProtos.Student.parseFrom(by);
        System.out.println(student1);

    }
}
