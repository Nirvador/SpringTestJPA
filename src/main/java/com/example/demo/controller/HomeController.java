package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Home controller for redirects purposes.
 */
@RequestMapping("/")
public interface HomeController {

    /**
     * Redirect to Swagger UI
     *
     * @return "redirect:/swagger-ui.html"
     */
    @GetMapping("${swagger-ui.redirect:}")
    @ApiOperation(value = "Redirect to swagger-ui.html")
    String redirectToSwaggerUi();
}
