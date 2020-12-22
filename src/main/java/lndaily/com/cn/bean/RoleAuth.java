package lndaily.com.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuth {
    private Integer connid;
    private Integer roleid;
    private Integer authid;
    private short isauth;
}
