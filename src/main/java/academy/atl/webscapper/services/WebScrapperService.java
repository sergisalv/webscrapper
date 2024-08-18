package academy.atl.webscapper.services;

import academy.atl.webscapper.models.WebPage;
import academy.atl.webscapper.repository.WebPageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class WebScrapperService {

    @Autowired
    private WebPageRepository repository;

    public void scrapeAndSave(String url)  {


        try {
            Document document = Jsoup.connect(url).get();

            String title = document.title();
            String description = document.select("meta[name=description]").attr("content");
            String picture = document.select("meta[property=og:image]").attr("content");
            String domain = getDomainFromUrl(url);

            WebPage webPage = new WebPage();
            webPage.setTitle(title);
            webPage.setDescription(description);
            webPage.setPicture(picture);
            webPage.setDomain(domain);
            webPage.setUrl(url);

            repository.save(webPage);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String getDomainFromUrl(String url) {
        String domain = url.replaceFirst("^(https?://)?(www\\.)?", "");
        int index = domain.indexOf('/');
        if (index != -1) {
            domain = domain.substring(0, index);
        }
        return domain;
    }


    public List<String> getAllLinks(String url) {
        WebPage findWebPage = repository.findByUrl(url);
        if (findWebPage != null){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String linkhref = link.attr("href");
                if (linkhref.startsWith("/")){
                    linkhref = "https://" + getDomainFromUrl(url) + linkhref;
                }
                if (!result.contains(linkhref)) {
                    result.add(linkhref);
                }

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

            return result;

        }


    }

