package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.exception.ExpressionFormatException;
import org.firespoon.fsbotserver.model.DiceResult;
import org.firespoon.fsbotserver.utils.CalculateUtils;
import org.firespoon.fsbotserver.utils.ListUtils;
import org.firespoon.fsbotserver.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DiceService {
    public List<Integer> singleDice(String diceExp) {
        Pattern pattern = Pattern.compile("^([1-9][0-9]*)d([1-9][0-9]*)$");
        Matcher matcher = pattern.matcher(diceExp);
        if (!matcher.find()) {
            throw new RuntimeException();
        }

        int time = Integer.parseInt(matcher.group(1));
        int max = Integer.parseInt(matcher.group(2));
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < time; ++i) {
            res.add(RandomUtils.random(1, max));
        }
        return res;
    }

    public DiceResult dice(String diceExp) {
        StringBuilder sb = new StringBuilder(diceExp);

        List<String> tokens = new ArrayList<>(20);
        List<Integer> diceTokenIndices = new ArrayList<>();

        diceExp = diceExp + "+";
        int begin = 0, end = 0;
        for (int i = 0; i < diceExp.length(); ++i) {
            char c = diceExp.charAt(i);
            if ("+-*/()".indexOf(c) != -1) {
                if (begin < end) {
                    String singleDice = diceExp.substring(begin, end);
                    diceTokenIndices.add(tokens.size());
                    tokens.add(singleDice);
                }
                String op = diceExp.substring(i, i + 1);
                tokens.add(op);
                begin = end = i + 1;
            } else {
                ++end;
            }
        }
        tokens.remove(tokens.size() - 1);

        boolean step1 = false;
        List<List<Integer>> singleDiceResults = new LinkedList<>();
        for (int index: diceTokenIndices) {
            String singleDice = tokens.get(index);
            Pattern pattern = Pattern.compile("^([1-9][0-9]*)d([1-9][0-9]*)$");
            Matcher matcher = pattern.matcher(singleDice);
            List<Integer> singleDiceResult;
            if (matcher.find()) {
                singleDiceResult = singleDice(singleDice);
                if (singleDiceResult.size() > 1) {
                    step1 = true;
                }
            } else {
                singleDiceResult = new LinkedList<>();
                Integer value = Integer.valueOf(singleDice);
                singleDiceResult.add(value);
            }
            singleDiceResults.add(singleDiceResult);
        }

        if (step1) {
            sb.append("=");
            Iterator<List<Integer>> it = singleDiceResults.iterator();
            for (int i = 0; i < tokens.size(); ++i) {
                if (diceTokenIndices.contains(i)) {
                    List<Integer> singleDiceResult = it.next();
                    if (singleDiceResult.size() > 1) {
                        sb.append("(");
                    }
                    sb.append(ListUtils.join(singleDiceResult, "+"));
                    if (singleDiceResult.size() > 1) {
                        sb.append(")");
                    }
                } else {
                    sb.append(tokens.get(i));
                }
            }
        }

        Iterator<List<Integer>> it = singleDiceResults.iterator();
        for (int i = 0; i < tokens.size(); ++i) {
            if (diceTokenIndices.contains(i)) {
                List<Integer> singleDiceResult = it.next();
                Integer sum = ListUtils.sum(singleDiceResult);
                tokens.set(i, sum.toString());
            }
        }

        if (tokens.size() > 1) {
            sb.append("=");
            sb.append(String.join("", tokens));
        }
        sb.append("=");
        String expression = String.join("", tokens);

        Integer res = calculate(expression);

        sb.append(res);
        DiceResult diceResult = new DiceResult();
        diceResult.setProcess(sb.toString());
        diceResult.setRes(res);
        return diceResult;
    }

    public Integer calculate(String exp) {
        exp = exp.replace(" ", "") + ")";
        Stack<Character> opStack = new Stack<>();
        opStack.push('(');
        Stack<Integer> numStack = new Stack<>();

        try {
            int index = 0;
            while (index < exp.length()) {
                int opIndex = index;
                while (opIndex < exp.length()) {
                    char c = exp.charAt(opIndex);
                    if (CalculateUtils.isOperator(c)) {
                        break;
                    }
                    opIndex++;
                }

                char op = exp.charAt(opIndex);
                if (index < opIndex) {
                    String numString = exp.substring(index, opIndex);
                    Integer num = Integer.valueOf(numString);
                    numStack.push(num);
                }

                Integer opPriority = CalculateUtils.getOperatorPriority(op);
                while (true) {
                    Character topOp = opStack.peek();
                    if (topOp == '(' && op == ')') {
                        opStack.pop();
                        break;
                    }
                    Integer topOpPriority = CalculateUtils.getOperatorPriority(topOp);
                    if (topOpPriority < opPriority || op == '(') {
                        break;
                    }
                    opStack.pop();
                    Integer num2 = numStack.pop();
                    Integer num1 = numStack.pop();
                    Integer topNum = CalculateUtils.calculate(num1, topOp, num2);
                    numStack.push(topNum);
                }

                if (op != ')') {
                    opStack.push(op);
                }

                index = opIndex + 1;
            }
        } catch (EmptyStackException e) {
            throw new ExpressionFormatException();
        }

        if (numStack.size() != 1) {
            throw new ExpressionFormatException();
        }
        if (!opStack.isEmpty()) {
            throw new ExpressionFormatException();
        }

        return numStack.pop();
    }
}
