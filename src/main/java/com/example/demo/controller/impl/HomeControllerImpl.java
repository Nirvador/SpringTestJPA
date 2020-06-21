package com.example.demo.controller.impl;

import com.example.demo.controller.HomeController;
import org.springframework.stereotype.Controller;

/**
 * Default implementation of the {@link HomeController}
 */
@Controller
public class HomeControllerImpl implements HomeController {

    private static final String REDIRECT_SWAGGER_UI_HTML = "redirect:/swagger-ui.html";

    /**
     * {@inheritDoc}
     */
    @Override
    public String redirectToSwaggerUi() {
        return REDIRECT_SWAGGER_UI_HTML;
    }
}
