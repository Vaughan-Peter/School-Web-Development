package com.example.springbootmvc.runner;

import com.example.springbootmvc.Util.AdminCreation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {
    private final AdminCreation adminCreation;

    public AppStartupRunner(AdminCreation adminCreation){
        this.adminCreation = adminCreation;
    }

    @Override
    public void run(String...arg) throws Exception{
        adminCreation.initAdmin();
    }
}
