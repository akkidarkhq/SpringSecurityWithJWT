package com.unoveo.springjwt.pages;


import com.google.gson.Gson;
import com.unoveo.calcUtils.CalcUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.unoveo.calcUtils.Expression;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


@WebServlet("/calc")
public class Servlet extends HttpServlet {
    Gson gson = new Gson();
    CalcUtils  calcUtils = new CalcUtils();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter pw = resp.getWriter();
        pw.print("get method called");
        System.out.print("get method called");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter pw = resp.getWriter();
        System.out.println(" do post called of calc/");
        resp.setContentType("application/json");
//      resp.setHeader("Access-Control-Allow-Origin","*");
        StringBuilder sb = new StringBuilder();
        String line ;
        while ((line=req.getReader().readLine())!=null){
            sb.append(line);
        }
        String jsonString = String.valueOf(sb);
        CalcUtils  calcUtils = new  CalcUtils();
        float result = calcUtils.calculate(jsonString);
        System.out.println(result);
        pw.print(result);
    }
}
