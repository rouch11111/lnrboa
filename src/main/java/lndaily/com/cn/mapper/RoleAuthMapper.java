package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.RoleAuth;

import java.util.List;
import java.util.Map;

public interface RoleAuthMapper {

    public void saveroleauth(RoleAuth roleAuth);

    public RoleAuth findischeck(Map map);

    public List<RoleAuth> findauths(Integer roleid);



    public void updateauth(Map map);
}
