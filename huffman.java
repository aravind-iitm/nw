package EXAM;
import java.util.*;

class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char c, int f) { 
        ch = c; 
        freq = f; 
    }
    Node(Node l, Node r) { 
        freq = l.freq + r.freq; 
        left = l; 
        right = r; 
    }

    public int compareTo(Node n) { return this.freq - n.freq; }
}

public class huffman {

    static void buildCodes(Node root, String code, Map<Character,String> map) {
        if (root.left == null && root.right == null) {
            map.put(root.ch, code.length() > 0 ? code : "1");
            return;
        }
        buildCodes(root.left, code + "0", map);
        buildCodes(root.right, code + "1", map);
    }

    static int decode(Node root, String enc, int i) {
        if (root.left == null && root.right == null) {
            System.out.print(root.ch);
            return i;
        }
        return decode(enc.charAt(++i) == '0' ? root.left : root.right, enc, i);
    }

    // ---------- SIMPLE TREE PRINT  ----------
    static void printTree(Node root, String indent, String branch) {
        if (root == null) return;

        if (root.left == null && root.right == null)
            System.out.println(indent + branch + root.ch + ":" + root.freq);
        else
            System.out.println(indent + branch + "(" + root.freq + ")");

        printTree(root.left,  indent + "   ", "0 -> ");
        printTree(root.right, indent + "   ", "1 -> ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        // frequency table
        Map<Character,Integer> freq = new HashMap<>();
        for (char c : text.toCharArray())
            freq.put(c, freq.getOrDefault(c,0) + 1);

        // min-heap
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (var e : freq.entrySet())
            pq.add(new Node(e.getKey(), e.getValue()));

        // build Huffman tree
        while (pq.size() > 1)
            pq.add(new Node(pq.poll(), pq.poll()));
        Node root = pq.peek();

        // generate codes
        Map<Character,String> codes = new HashMap<>();
        buildCodes(root, "", codes);

        // encode text
        StringBuilder enc = new StringBuilder();
        for (char c : text.toCharArray())
            enc.append(codes.get(c));

        System.out.println("Codes:   " + codes);
        System.out.println("Encoded: " + enc);
        System.out.print("Decoded: ");

        for (int i = -1; i < enc.length() - 1; )
            i = decode(root, enc.toString(), i);

        System.out.println();
        System.out.println("\nHuffman Tree:");
        printTree(root, "", "");
    }
}
