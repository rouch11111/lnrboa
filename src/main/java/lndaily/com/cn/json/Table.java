package lndaily.com.cn.json;

import lndaily.com.cn.bean.Work;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table<T> {
    private Integer code;
    private String msg;
    private int count;
    private List<T> data;
}
