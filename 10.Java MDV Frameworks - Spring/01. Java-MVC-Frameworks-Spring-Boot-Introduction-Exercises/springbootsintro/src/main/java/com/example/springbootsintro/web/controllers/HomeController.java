package com.example.springbootsintro.web.controllers;

import com.example.springbootsintro.domain.models.view.HomeViewModel;
import com.example.springbootsintro.service.OfferService;
import com.example.springbootsintro.utils.HtmlReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final String INDEX_HTML_PATH = "C:\\Users\\Vlad\\Desktop\\springbootsintro\\src\\main\\resources\\static\\index.html";

    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return getHtml();
    }

    private String getHtml() throws IOException {

        List<HomeViewModel> models = offerService.getAllOffers()
                .stream()
                .map(o -> modelMapper.map(o, HomeViewModel.class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        models.forEach(m -> {
            sb.append("<div class=\"apartment\">")
                    .append("<p>Rent: ").append(m.getApartmentRent()).append("</p")
                    .append(String.format("<p>Type: %s</p>", m.getApartmentType()))
                    .append("<p>Commission: ").append(m.getAgencyCommission()).append("</p>")
                    .append("</div>");
        });

        String html = htmlReader.readHtmlFile(INDEX_HTML_PATH);

        if(models.isEmpty()){
           html = html.replace("{{placeholder}}", "<div class=\"apartment\" style=\"border: red solid 1px\">There aren't any offers!</div>");
        }else {
           html = html.replace("{{placeholder}}", sb.toString());
        }

        return html;

    }



//        <div class="apartment">
//<p>Rent: 700.00</p>
//<p>Type: Two Room apartment</p>
//<p>Commission: 50.00</p>
//</div>

}
