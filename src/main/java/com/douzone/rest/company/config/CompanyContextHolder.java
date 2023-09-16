package com.douzone.rest.company.config;

public class CompanyContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setCompanyCode(String companyCode) {
        CONTEXT.set(companyCode);
    }

    public static String getCompanyCode() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}