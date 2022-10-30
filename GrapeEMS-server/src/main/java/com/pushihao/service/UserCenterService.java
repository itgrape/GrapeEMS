package com.pushihao.service;

import com.pushihao.pojo.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserCenterService {
    public List<UserCenterUsers> getAllUserCenterUsers();

    public Integer getTotalNum();

    public List<UserCenterUsers> queryUserCenterUsers(QueryUser queryUser);

    public Boolean addNewUser(AddUser addUser);

    public UserCenterUsers getOneUserById(Long id);

    public Boolean editOneUser(UserCenterUsers userCenterUsers);

    public Boolean deleteOneUserByUserId(Long id);

    public Integer getDeptPersonNumber(String deptName);

    public UserSexInfo getUserSexInfo();

    public UserAgeInfo getUserAgeInfo();

    public void downloadUser(HttpServletResponse response) throws IOException;

    public Boolean saveBatchUser(List<UserUploadInfo> userList);
}
