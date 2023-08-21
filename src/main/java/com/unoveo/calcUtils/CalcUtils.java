package com.unoveo.calcUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Arrays;
import java.util.List;

public class CalcUtils {

    public float calculate(String jsonString) throws JsonProcessingException,NumberFormatException {

        System.out.println("calculate() called of calcutils class");

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
                    firstNo= CalcUtils.doCalculate(firstNo,Float.parseFloat(x.getValue()),op);
                    System.out.println(firstNo+" from third if block");
                }
            } else if (x.getType().equals("OPERATOR")) {
                op=x.getValue();
                System.out.println(op);
            }
        }
        System.out.println(firstNo);
        return firstNo;
    }

    public static float doCalculate(float firstNo,float secondNo, String op){
        float result = 0;
        switch (op){
            case "ADDITION":
                result = firstNo + secondNo;
                break;
            case "SUBTRACT":
                result = firstNo - secondNo;
                break;
            case "MULTIPLY":
                result = firstNo * secondNo;
                break;
            case "DIVIDE":
                result = firstNo / secondNo;
                break;
        }
        return result;
    }
}
