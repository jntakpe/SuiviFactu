package com.github.jntakpe.sf.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe utilitaire manipulant les URLs
 *
 * @author jntakpe
 */
public final class UrlUtils {

    private UrlUtils() {
    }

    public static String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
