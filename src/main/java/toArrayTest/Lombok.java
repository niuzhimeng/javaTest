package toArrayTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * lambok测试
 * Created by nzm on 2017/3/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Lombok implements Serializable {
    private String id;
    private String name;


    static String OutStr(@NonNull String string) {
        return "返回值：" + string;
    }

}
