package com.DigitalDesign;

import java.io.IOException;
import java.util.Stack;

class One {
    private static class Item {
        public final int factor;
        public final StringBuilder sb;
        public Item(int factor) {
            this.factor = factor;
            sb = new StringBuilder();
        }
    }

    private static final Stack<Item> stack = new Stack<>();

    public static void main(String[] args) {
        int a = -1;
        int factor = 0;
        while (true) {
            try {
                a = System.in.read();
            } catch (IOException e) {
                System.exit(1);
            }
            if (a == -1) {
                break;
            }
            if (Character.isDigit(a)) {
                factor = 10 * factor + Character.getNumericValue(a);
            } else if (a == '[') {
                stack.push(new Item(factor));
                factor = 0;
            } else if (a == ']') {
                Item item = stack.pop();
                String s = item.sb.toString();
                append(item.factor, s);
                factor = 0;
            } else {
                append(factor, Character.toString((char) a));
                factor = 0;
            }
        }
    }

    private static void append(int factor, String s) {
        int n = (factor == 0) ? 1 : factor;
        for (int i = 0; i < n; ++i) {
            if (stack.empty()) {
                System.out.print(s);
            } else {
                stack.peek().sb.append(s);
            }
        }
    }
}