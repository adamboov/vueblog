package com.adam.vueblog.util;

import com.adam.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author VAIO-adam
 */
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
