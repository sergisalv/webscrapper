package academy.atl.webscapper.jobs;

import academy.atl.webscapper.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScrapperJob {

    @Autowired
    private SpiderService spiderService;

    //Hace que el servicio se inicie a las 4 de la mañana.
    @Scheduled (cron = "0 0 4 * * *")
    public void executeJob(){
        spiderService.start();
    }
}
