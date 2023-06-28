package com.unoveo.springjwt.pages;


import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.unoveo.springjwt.pojos.Expression;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/calc")
public class Servlet extends HttpServlet {
    Gson gson = new Gson();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter pw = resp.getWriter();
        pw.print("get method called");
        System.out.print("get method called");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
//      resp.setHeader("Access-Control-Allow-Origin","*");
        StringBuilder sb = new StringBuilder();
        String line ;
        while ((line=req.getReader().readLine())!=null){
            sb.append(line);
        }

        String jsonString = String.valueOf(sb);
        ObjectMapper objectMapper = new ObjectMapper();
//        ExpressionObject[] n1 = objectMapper.readValue(jsonString, ExpressionObject[].class);
        List<Expression> ppl2 = Arrays.asList(objectMapper.readValue(jsonString, Expression[].class));
//        List<ExpressionObject> pp3 = objectMapper.readValue(jsonString, new TypeReference<List<ExpressionObject>>() {});


        Float firstNo=null;
        String op="";

        for (Expression x :ppl2) {
            if(x.getType().equals("NUMBER")){
                if(firstNo==null){
                    firstNo= Float.parseFloat(x.getValue());
                    System.out.println(firstNo);
                }else {
                    firstNo=Expression.calculate(firstNo,Float.parseFloat(x.getValue()),op);
                    System.out.println(firstNo+" from third if block");
                }
            } else if (x.getType().equals("OPERATOR")) {
                op=x.getValue();
                System.out.println(op);
            }
        }
        pw.print(firstNo);
    }
}
