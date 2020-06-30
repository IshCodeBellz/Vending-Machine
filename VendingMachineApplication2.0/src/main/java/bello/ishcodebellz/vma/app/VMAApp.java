package bello.ishcodebellz.vma.app;

import bello.ishcodebellz.vma.controller.VMAController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VMAApp {
    public static void main(String[] args) {
        // original main method has been replaced in order for the XML setup to work
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // the controller is being called
        VMAController controller = ctx.getBean("controller", VMAController.class);
        // runs the application
        controller.runVendingMachineApplication();
    }
}
