package lndaily.com.cn.json;

import lndaily.com.cn.bean.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUpload {
    private Integer code;
    private String msg;
    private Image data;
}
