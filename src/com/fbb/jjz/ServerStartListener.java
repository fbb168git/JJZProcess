package com.fbb.jjz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.task.AutoHandleTask;
import com.fbb.jjz.task.MainTask;

public class ServerStartListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(ServerStartListener.class); 
    AutoHandleTask mHandleTask= null;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	    if(mHandleTask != null){
            mHandleTask.stop();
            mHandleTask = null;
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	    if(mHandleTask != null){
	        mHandleTask.stop();
	        mHandleTask = null;
	    }
	    mHandleTask = new AutoHandleTask();
	    mHandleTask.start();
	 }
}
