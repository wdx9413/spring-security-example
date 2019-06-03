package cn.dox.jwt.service;
/**
 * @author: weidx
 * @date: 2019/6/4
 */
public interface AuthService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
}
