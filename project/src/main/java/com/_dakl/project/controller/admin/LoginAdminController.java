/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luong
 */
@Controller
public class LoginAdminController {
    @RequestMapping("/logon")
    public String logon()
    {
        return "admin/pages/login";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "admin/pages/403"; // Trả về trang 403.html
    }
}
