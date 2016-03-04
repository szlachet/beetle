/*
 * Copyright 2016 Sebastian Szlachetka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szlachet.beetle.config.filters;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sebastian Szlachetka
 */
@WebFilter(filterName = "Html5ModeRedirector",
urlPatterns = {"/*"})
public class Html5ModeRedirector implements Filter {

    private static final Logger LOGGER = Logger.getLogger(Html5ModeRedirector.class.getName());
    private static final List<String> NOT_REDIRECTED = Lists.newArrayList("/beetle/api-docs", 
            "/beetle/css", "/beetle/img", "/beetle/js", "/beetle/lib", "/beetle/modules", "/beetle/index.html");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if(!NOT_REDIRECTED.stream().filter(p -> requestURI.startsWith(p)).findFirst().isPresent()) {
            httpRequest.getRequestDispatcher("/index.html").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
